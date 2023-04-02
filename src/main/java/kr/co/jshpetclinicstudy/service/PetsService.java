package kr.co.jshpetclinicstudy.service;

import jakarta.transaction.Transactional;
import kr.co.jshpetclinicstudy.persistence.dto.PetsDto;
import kr.co.jshpetclinicstudy.persistence.entity.Owners;
import kr.co.jshpetclinicstudy.persistence.entity.Pets;
import kr.co.jshpetclinicstudy.persistence.entity.Types;
import kr.co.jshpetclinicstudy.persistence.repository.OwnersRepository;
import kr.co.jshpetclinicstudy.persistence.repository.PetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetsService {

    private final PetsRepository petsRepository;
    private final OwnersRepository ownersRepository;

    private Pets dtoToEntity(PetsDto dto) {
        Optional<Owners> owners = ownersRepository.findOwnerByTelephone(dto.getOwnerTelephone());

        Pets pets = Pets.builder()
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .owners(owners.get())
                .types(Types.valueOf(dto.getType()))
                .build();

        return pets;
    }

    private PetsDto entityToDto(Pets pets) {
        PetsDto dto = PetsDto.builder()
                .petId(pets.getId())
                .name(pets.getName())
                .birthDate(pets.getBirthDate())
                .ownerFirstName(pets.getOwners().getFirstName())
                .ownerTelephone(pets.getOwners().getTelephone())
                .type(pets.getTypes().toString())
                .build();

        return dto;
    }

    public void createPets(PetsDto dto) {
        Pets pets = dtoToEntity(dto);

        petsRepository.save(pets);
    }

    @Transactional
    public PetsDto getPets(Long id) {
        Optional<Pets> result = petsRepository.findById(id);

        return entityToDto(result.get());
    }

    public void modifyPets(PetsDto dto) {
        Optional<Pets> pets = petsRepository.findById(dto.getPetId());

        if (pets.isPresent()) {
//            pets.changeName(dto.getName());
//            pets.changebirthDate(dto.getBirthDate());
//            pets.changeTypes(dto.getType().toString());
            petsRepository.save(pets.get());
        }
    }

    public void deletePets(Long id) {
        Optional<Pets> pets = petsRepository.findById(id);

        if (pets.isPresent()) {
            petsRepository.deleteById(id);
        }
    }
}
