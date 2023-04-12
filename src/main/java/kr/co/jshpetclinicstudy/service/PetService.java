package kr.co.jshpetclinicstudy.service;

import jakarta.transaction.Transactional;
import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import kr.co.jshpetclinicstudy.persistence.entity.Type;
import kr.co.jshpetclinicstudy.persistence.repository.PetRepository;
import kr.co.jshpetclinicstudy.service.model.mapper.PetMapper;
import kr.co.jshpetclinicstudy.service.model.request.PetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.PetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    private final PetMapper petMapper;

    @Transactional
    public void createPet(PetRequestDto.CREATE create) {
        final Pet pet = petMapper.toEntity(create);
        petRepository.save(pet);
    }

    @Transactional
    public List<PetResponseDto.READ> getPetList() {
        return petRepository.findAll().stream()
                .map(petMapper::toReadDto).collect(Collectors.toList());
    }

    @Transactional
    public PetResponseDto.READ getPet(Long id) {
        final Optional<Pet> pet = petRepository.findById(id);
        isPet(pet);
        return petMapper.toReadDto(pet.get());
    }

    @Transactional
    public List<PetResponseDto.READ> getPetListOfOwner(Long ownerId) {
        return petRepository.findPetListByOwnerId(ownerId).stream()
                .map(petMapper::toReadDto).collect(Collectors.toList());
    }

    @Transactional
    public void updatePet(PetRequestDto.UPDATE update) {
        final Optional<Pet> pet = petRepository.findById(update.getPetId());
        isPet(pet);

        pet.get().changePetName(update.getName());
        pet.get().changePetBirtDate(update.getBirthDate());
        pet.get().changePetType(Type.valueOf(update.getType()));

        petRepository.save(pet.get());
    }

    @Transactional
    public void deletePet(Long id) {
        final Optional<Pet> pet = petRepository.findById(id);
        isPet(pet);
        petRepository.deleteById(id);
    }

    private void isPet(Optional<Pet> pet) {
        if (pet.isEmpty()) {
            throw new RuntimeException("This Pet is Not Exist");
        }
    }

}
