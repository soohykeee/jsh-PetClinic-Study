package kr.co.jshpetclinicstudy.service;

import jakarta.transaction.Transactional;
import kr.co.jshpetclinicstudy.persistence.entity.Specialty;
import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import kr.co.jshpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.jshpetclinicstudy.persistence.repository.SpecialtyRepository;
import kr.co.jshpetclinicstudy.persistence.repository.VetRepository;
import kr.co.jshpetclinicstudy.service.model.mapper.SpecialtyMapper;
import kr.co.jshpetclinicstudy.service.model.mapper.VetMapper;
import kr.co.jshpetclinicstudy.service.model.mapper.VetSpecialtyMapper;
import kr.co.jshpetclinicstudy.service.model.request.VetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VetService {

    private final VetRepository vetRepository;

    private final SpecialtyRepository specialtyRepository;

    private final VetMapper vetMapper;

    private final SpecialtyMapper specialtyMapper;

    private final VetSpecialtyMapper vetSpecialtyMapper;

    @Transactional
    public void createVet(VetRequestDto.CREATE create) {
        //Vet Entity Create
        Vet vet = vetMapper.toEntity(create, Collections.emptyList());
        //create or get
        final List<VetSpecialty> vetSpecialties = getOrCreateVetSpecialties(create.getSpecialtiesName(), vet);
        vet.changeVetSpecialties(vetSpecialties);
        vetRepository.save(vet);
    }

    @Transactional
    public VetResponseDto.READ getVet(Long id) {
        final Optional<Vet> vet = vetRepository.findById(id);
        isVet(vet);
        final List<String> specialtiesName = getSpecialtiesNameByVet(vet.get());

        return vetMapper.toReadDto(vet.get(), specialtiesName);
    }

    // return type - List<Specialty> 로 하였다가, PostMan 을 통해 조회 시, 데이터를 확인하기엔 불편한 요소가 있기에
    // Set<String> 을 사용하여 모든 전문학위를 보기에 더 좋게 수정
    public Set<String> getSpecialties() {
        List<Specialty> specialties = specialtyRepository.findAll();

        final Set<String> specialtyNames = specialties.stream()
                .map(Specialty::getSpecialtyName)
                .collect(Collectors.toSet());

        return specialtyNames;
    }

    @Transactional
    public void updateVet(VetRequestDto.UPDATE update) {
        final Optional<Vet> vet = vetRepository.findById(update.getVetId());
        isVet(vet);
        final List<VetSpecialty> vetSpecialties = getOrCreateVetSpecialties(update.getSpecialtiesName(), vet.get());
        vet.get().changeVetSpecialties(vetSpecialties);

        vetRepository.save(vet.get());
    }

    @Transactional
    public void deleteVet(Long id) {
        final Optional<Vet> vet = vetRepository.findById(id);
        isVet(vet);
        vetRepository.delete(vet.get());
    }

    private List<String> getSpecialtiesNameByVet(Vet vet) {
        return vet
                .getVetSpecialties()
                .stream()
                .map(VetSpecialty::getSpecialty)
                .map(Specialty::getSpecialtyName)
                .collect(Collectors.toList());
    }


    private List<Specialty> getOrCreateVetSpecialtiesByNames(List<String> names) {

        //names = "n1, n2, n3"
        //entity = n1, n2
        List<Specialty> specialties = specialtyRepository.findAllBySpecialtyNameIn(names);

        //set = n1.name, n2.name
        final Set<String> existNames = specialties.stream()
                .map(Specialty::getSpecialtyName)
                .collect(Collectors.toSet());

        //없는 것들 여기서 필터로 걸러서 생성
        //list = n3
        final List<Specialty> createSpecialties = names.stream()
                .filter(name -> !existNames.contains(name))
                .map(specialtyMapper::toEntity)
                .collect(Collectors.toList());

        //list = n1, n2, n3
        specialties.addAll(createSpecialties);

        return specialties;
    }

    private List<VetSpecialty> getOrCreateVetSpecialties(List<String> names, Vet vet) {

        final List<Specialty> specialties = getOrCreateVetSpecialtiesByNames(names);

        //연관 엔티티 반
        return specialties.stream()
                .map(specialty -> vetSpecialtyMapper.toEntity(specialty, vet))
                .collect(Collectors.toList());
    }

    private void isVet(Optional<Vet> vet) {
        if (vet.isEmpty()) {
            throw new RuntimeException("This Vet is Not Exist");
        }
    }

}
