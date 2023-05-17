package kr.co.jshpetclinicstudy.service;

import jakarta.transaction.Transactional;
import kr.co.jshpetclinicstudy.infra.exception.NotFoundException;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import kr.co.jshpetclinicstudy.persistence.entity.Type;
import kr.co.jshpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.jshpetclinicstudy.persistence.repository.PetRepository;
import kr.co.jshpetclinicstudy.persistence.repository.search.PetSearchRepository;
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

    private final OwnerRepository ownerRepository;

    private final PetSearchRepository petSearchRepository;

    private final PetMapper petMapper;

    @Transactional
    public void createPet(PetRequestDto.CREATE create) {
        Optional<Owner> owner = ownerRepository.findById(create.getOwnerId());
        isOwner(owner);
        final Pet pet = petMapper.toEntity(create, owner.get());
        petRepository.save(pet);
    }

    public List<PetResponseDto.READ> getPetsByCondition(PetRequestDto.CONDITION condition) {
        final List<Pet> pets = petSearchRepository.find(condition);

        return pets.stream()
                .map(petMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PetResponseDto.READ> getPetsByOwner(Long ownerId) {
        return petRepository
                .findPetsByOwnerId(ownerId)
                .stream()
                .map(petMapper::toReadDto)
                .collect(Collectors.toList());
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
            throw new NotFoundException(ResponseStatus.FAIL_NOT_FOUND);
        }
    }

    private void isOwner(Optional<Owner> owner) {
        if (owner.isEmpty()) {
            throw new NotFoundException(ResponseStatus.FAIL_NOT_FOUND);
        }
    }

}
