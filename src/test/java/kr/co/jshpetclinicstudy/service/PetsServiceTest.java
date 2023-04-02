package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.dto.PetsDto;
import kr.co.jshpetclinicstudy.persistence.repository.PetsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetsServiceTest {

    @Autowired
    private PetsService petsService;
    @Autowired
    private PetsRepository petsRepository;

    @Test
    void createPets() {
        PetsDto dto = PetsDto.builder()
                .name("멍멍이")
                .birthDate(LocalDate.ofYearDay(2022, 1))
                .ownerFirstName("수혁")
                .ownerTelephone("01064564655")
                .type("푸들")
                .build();

        petsService.createPets(dto);
    }

    @Test
    void getPets() {
        PetsDto petsDto = petsService.getPets(1L);

        Assertions.assertThat(petsDto.getName()).isEqualTo("멍멍이");
    }

    @Test
    void updatePets() {

    }

    @Test
    void deletePets() {
        petsService.deletePets(1L);

        Assertions.assertThat(petsRepository.findById(1L).isEmpty()).isTrue();
    }

}