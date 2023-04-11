package kr.co.jshpetclinicstudy.service;

import jakarta.transaction.Transactional;
import kr.co.jshpetclinicstudy.persistence.entity.Vets;
import kr.co.jshpetclinicstudy.persistence.repository.VetsRepository;
import kr.co.jshpetclinicstudy.service.model.mapper.VetsMappers;
import kr.co.jshpetclinicstudy.service.model.request.VetsRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VetsService {

    private final VetsRepository vetsRepository;

    private final VetsMappers vetsMappers;

    @Transactional
    public void createVet(VetsRequestDto.CREATE create) {
        final Vets vets = vetsMappers.toVetsEntity(create);
        vetsRepository.save(vets);
    }

    @Transactional
    public List<VetsResponseDto.READ> getVetList() {
        return vetsRepository.findAll().stream()
                .map(vetsMappers::toReadDto).collect(Collectors.toList());
    }

    @Transactional
    public VetsResponseDto.READ getVet(Long id) {
        final Optional<Vets> vets = vetsRepository.findById(id);
        isVets(vets);
        return vetsMappers.toReadDto(vets.get());
    }

    @Transactional
    public void updateVet(VetsRequestDto.UPDATE update) {
        final Optional<Vets> vets = vetsRepository.findById(update.getVetId());
        isVets(vets);

        vets.get().changeVetFirstName(update.getFirstName());
        vets.get().changeVetLastName(update.getLastName());
        vets.get().changeVetSpecialties(update.getSpecialties());

        vetsRepository.save(vets.get());
    }

    @Transactional
    public void deleteVet(Long id) {
        final Optional<Vets> vets = vetsRepository.findById(id);
        isVets(vets);
        vetsRepository.deleteById(id);
    }

    private void isVets(Optional<Vets> vets) {
        if (vets.isEmpty()) {
            throw new RuntimeException("This Vet is Not Exist");
        }
    }

}
