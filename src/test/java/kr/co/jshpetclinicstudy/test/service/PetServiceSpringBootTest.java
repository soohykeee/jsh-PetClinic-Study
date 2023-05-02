package kr.co.jshpetclinicstudy.test.service;

import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import kr.co.jshpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.jshpetclinicstudy.persistence.repository.PetRepository;
import kr.co.jshpetclinicstudy.service.PetService;
import kr.co.jshpetclinicstudy.service.model.request.PetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.PetResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PetServiceSpringBootTest {

    @Autowired
    private PetService petService;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private OwnerRepository ownerRepository;
/*
    @Test
    void createPet() {
        Optional<Owner> owner = ownerRepository.findOwnerByTelephone("01064564655");

        PetRequestDto.CREATE create = PetRequestDto.CREATE.builder()
                .name("멍멍이")
                .birthDate(LocalDate.of(2021, 1, 9))
                .owner(owner.get())
                .type("푸들")
                .build();

        petService.createPet(create);
        Optional<Pet> pets = petRepository.findById(2L);
        assertThat(pets.get().getName()).isEqualTo("멍멍이");
    }

    @Test
    void getPetList() {
        List<PetResponseDto.READ> readList = petService.getPetList();

        assertThat(readList.get(0).getName()).isEqualTo("멍멍이");
        assertThat(petRepository.findAll().size()).isEqualTo(readList.size());
    }

    @Test
    void getPet() {
        PetResponseDto.READ read = petService.getPet(1L);
        assertThat(read.getName()).isEqualTo("멍멍이");
        assertThat(read.getOwnerFirstName()).isEqualTo("수혁");
    }

    @Test
    void getPetListOfOwner() {
        List<PetResponseDto.READ> petListOfOwner = petService.getPetsByOwner(2L);

        assertThat(petListOfOwner.get(0).getName()).isEqualTo("멍멍이");
        assertThat(petListOfOwner.size()).isEqualTo(1);
    }

    @Test
    void updatePet() {
        PetRequestDto.UPDATE update = PetRequestDto.UPDATE.builder()
                .petId(1L)
                .name("딸기")
                .birthDate(LocalDate.of(2011, 01, 01))
                .type("포메라니안")
                .build();

        petService.updatePet(update);
        assertThat(petRepository.findById(1L).get().getName()).isEqualTo("딸기");
    }

    @Test
    void deletePet() {
        petService.deletePet(1L);
        assertThat(petRepository.findById(1L)).isEmpty();
    }*/

}