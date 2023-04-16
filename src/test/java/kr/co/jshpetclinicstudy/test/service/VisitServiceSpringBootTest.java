package kr.co.jshpetclinicstudy.test.service;

import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import kr.co.jshpetclinicstudy.persistence.entity.Visit;
import kr.co.jshpetclinicstudy.persistence.repository.PetRepository;
import kr.co.jshpetclinicstudy.persistence.repository.VisitRepository;
import kr.co.jshpetclinicstudy.service.VisitService;
import kr.co.jshpetclinicstudy.service.model.request.VisitRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VisitResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VisitServiceSpringBootTest {

    @Autowired
    private VisitService visitService;
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private PetRepository petRepository;

    @Test
    void createVisit() {
        Optional<Pet> pet = petRepository.findById(2L);

        VisitRequestDto.CREATE create = VisitRequestDto.CREATE.builder()
                .visitDate(LocalDate.of(2023, 10, 12))
                .description("건강검진 및 예방접종")
                .pet(pet.get())
                .build();

        visitService.createVisit(create);
        Optional<Visit> visits = visitRepository.findById(1L);
        assertThat(visits.get().getDescription()).startsWith("건강검진");
    }

    @Test
    void getVisitList() {
        List<VisitResponseDto.READ> readList = visitService.getVisitList();

        assertThat(readList.get(0).getDescription()).startsWith("건강검진");
        assertThat(visitRepository.findAll().size()).isEqualTo(readList.size());
    }

    @Test
    void getVisit() {
        VisitResponseDto.READ read = visitService.getVisit(1L);
        assertThat(read.getOwnerFirstName()).isEqualTo("수혁");
        assertThat(read.getPetName()).isEqualTo("멍멍이");
    }

    @Test
    void getVisitListOfPet() {
        List<VisitResponseDto.READ> visitListOfPet = visitService.getVisitsByPet(2L);

        assertThat(visitListOfPet.size()).isEqualTo(1);
    }

    @Test
    void updateVisit() {
        Optional<Pet> pet = petRepository.findById(2L);

        VisitRequestDto.UPDATE update = VisitRequestDto.UPDATE.builder()
                .visitId(1L)
                .visitDate(LocalDate.of(2023, 05, 01))
                .description("수정테스트")
                .pet(pet.get())
                .build();

        visitService.updateVisit(update);
        assertThat(visitRepository.findById(1L).get().getDescription()).isEqualTo("수정테스트");
    }

    @Test
    void deleteVisit() {
        visitService.deleteVisit(1L);
        assertThat(visitRepository.findById(1L)).isEmpty();
    }
}