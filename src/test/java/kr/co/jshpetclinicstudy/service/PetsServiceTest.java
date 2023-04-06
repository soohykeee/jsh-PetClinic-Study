package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.entity.Owners;
import kr.co.jshpetclinicstudy.persistence.entity.Pets;
import kr.co.jshpetclinicstudy.persistence.repository.OwnersRepository;
import kr.co.jshpetclinicstudy.persistence.repository.PetsRepository;
import kr.co.jshpetclinicstudy.service.model.PetsRequestDto;
import kr.co.jshpetclinicstudy.service.model.PetsResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PetsServiceTest {

    @Autowired
    private PetsService petsService;
    @Autowired
    private PetsRepository petsRepository;
    @Autowired
    private OwnersRepository ownersRepository;

    @Test
    void createPet() {
        Optional<Owners> owners = ownersRepository.findOwnerByTelephone("01064564655");

        PetsRequestDto.CREATE create = PetsRequestDto.CREATE.builder()
                .name("멍멍이")
                .birthDate(LocalDate.of(2021, 1, 9))
                .owners(owners.get())
                .type("푸들")
                .build();

        petsService.createPet(create);
        Optional<Pets> pets = petsRepository.findById(1L);
        assertThat(pets.get().getName()).isEqualTo("멍멍이");
    }

    @Test
    void getPetList() {
        List<PetsResponseDto.READ> readList = petsService.getPetList();

        assertThat(readList.get(0).getName()).isEqualTo("멍멍이");
        assertThat(petsRepository.findAll().size()).isEqualTo(readList.size());
    }

    @Test
    void getPet() {
        PetsResponseDto.DETAIL_READ detailRead = petsService.getPet(2L);
        assertThat(detailRead.getName()).isEqualTo("뭉이");
        assertThat(detailRead.getOwnerFirstName()).isEqualTo("수혁");
    }

    @Test
    void getPetListOfOwner() {
        List<PetsResponseDto.READ> petListOfOwner = petsService.getPetListOfOwner(1L);

        assertThat(petListOfOwner.get(2).getName()).isEqualTo("초코");
        assertThat(petListOfOwner.size()).isEqualTo(3);
    }

    @Test
    void updatePet() {
        PetsRequestDto.UPDATE update = PetsRequestDto.UPDATE.builder()
                .petId(3L)
                .name("딸기")
                .birthDate(LocalDate.of(2011, 01, 01))
                .type("포메라니안")
                .build();

        petsService.updatePet(update);
        assertThat(petsRepository.findById(3L).get().getName()).isEqualTo("딸기");
    }

    @Test
    void deletePet() {
        petsService.deletePet(3L);
        assertThat(petsRepository.findById(3L)).isEmpty();
    }

}