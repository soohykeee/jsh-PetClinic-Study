package kr.co.jshpetclinicstudy.service;

import jakarta.transaction.Transactional;
import kr.co.jshpetclinicstudy.persistence.entity.Pets;
import kr.co.jshpetclinicstudy.persistence.entity.Types;
import kr.co.jshpetclinicstudy.persistence.repository.PetsRepository;
import kr.co.jshpetclinicstudy.service.model.request.PetsRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.PetsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetsService {

    private final PetsRepository petsRepository;

    public void createPet(PetsRequestDto.CREATE create) {
        final Pets pets = Pets.dtoToEntity(create);
        petsRepository.save(pets);
    }

    @Transactional
    public List<PetsResponseDto.READ> getPetList() {
        return petsRepository.findAll().stream()
                .map(Pets::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    public PetsResponseDto.DETAIL_READ getPet(Long id) {
        return Pets.entityToDetailDto(petsRepository.findById(id).get());
    }

    @Transactional
    public List<PetsResponseDto.READ> getPetListOfOwner(Long ownerId) {
        return petsRepository.findPetListByOwnerId(ownerId).stream()
                .map(Pets::entityToDto).collect(Collectors.toList());
    }

    public void updatePet(PetsRequestDto.UPDATE update) {
        final Optional<Pets> pets = petsRepository.findById(update.getPetId());
        isPets(pets);

        pets.get().changePetName(update.getName());
        pets.get().changePetBirtDate(update.getBirthDate());
        pets.get().changePetType(Types.valueOf(update.getType()));

        petsRepository.save(pets.get());
    }

    public void deletePet(Long id) {
        final Optional<Pets> pets = petsRepository.findById(id);
        isPets(pets);
        petsRepository.deleteById(id);
    }

    private void isPets(Optional<Pets> pets) {
        if (pets.isEmpty()) {
            throw new RuntimeException("This Pet is Not Exist");
        }
    }

}
