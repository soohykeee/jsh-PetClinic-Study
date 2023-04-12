package kr.co.jshpetclinicstudy.service;

import jakarta.transaction.Transactional;
import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import kr.co.jshpetclinicstudy.persistence.repository.VetRepository;
import kr.co.jshpetclinicstudy.service.model.mapper.VetMapper;
import kr.co.jshpetclinicstudy.service.model.request.VetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VetService {

    private final VetRepository vetRepository;

    private final VetMapper vetMapper;

    @Transactional
    public void createVet(VetRequestDto.CREATE create) {
        final Vet vet = vetMapper.toEntity(create);
        vetRepository.save(vet);
    }

    @Transactional
    public List<VetResponseDto.READ> getVetList() {
        return vetRepository.findAll().stream()
                .map(vetMapper::toReadDto).collect(Collectors.toList());
    }

    @Transactional
    public VetResponseDto.READ getVet(Long id) {
        final Optional<Vet> vet = vetRepository.findById(id);
        isVet(vet);
        return vetMapper.toReadDto(vet.get());
    }

    @Transactional
    public void updateVet(VetRequestDto.UPDATE update) {
        final Optional<Vet> vet = vetRepository.findById(update.getVetId());
        isVet(vet);

        vet.get().changeVetFirstName(update.getFirstName());
        vet.get().changeVetLastName(update.getLastName());
        vet.get().changeVetSpecialties(update.getSpecialties());

        vetRepository.save(vet.get());
    }

    @Transactional
    public void deleteVet(Long id) {
        final Optional<Vet> vet = vetRepository.findById(id);
        isVet(vet);
        vetRepository.deleteById(id);
    }

    private void isVet(Optional<Vet> vet) {
        if (vet.isEmpty()) {
            throw new RuntimeException("This Vet is Not Exist");
        }
    }

}
