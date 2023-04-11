package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.entity.Vets;
import kr.co.jshpetclinicstudy.persistence.repository.VetsRepository;
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

    public void createVet(VetsRequestDto.CREATE create) {
        final Vets vets = Vets.dtoToEntity(create);
        vetsRepository.save(vets);
    }

    public List<VetsResponseDto.READ> getVetList() {
        return vetsRepository.findAll().stream().map(Vets::entityToDto).collect(Collectors.toList());
    }

    public VetsResponseDto.DETAIL_READ getVet(Long id) {
        return Vets.entityToDetailDto(vetsRepository.findById(id).get());
    }

    public void updateVet(VetsRequestDto.UPDATE update) {
        final Optional<Vets> vets = vetsRepository.findById(update.getVetId());
        isVets(vets);

        vets.get().changeVetFirstName(update.getFirstName());
        vets.get().changeVetLastName(update.getLastName());

        vetsRepository.save(vets.get());
    }

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
