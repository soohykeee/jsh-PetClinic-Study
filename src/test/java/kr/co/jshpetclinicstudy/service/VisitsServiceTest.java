package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.entity.Pets;
import kr.co.jshpetclinicstudy.persistence.entity.Visits;
import kr.co.jshpetclinicstudy.persistence.repository.PetsRepository;
import kr.co.jshpetclinicstudy.persistence.repository.VisitsRepository;
import kr.co.jshpetclinicstudy.service.model.VisitsRequestDto;
import kr.co.jshpetclinicstudy.service.model.VisitsResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VisitsServiceTest {

    @Autowired
    private VisitsService visitsService;
    @Autowired
    private VisitsRepository visitsRepository;
    @Autowired
    private PetsRepository petsRepository;

    @Test
    void createVisit() {
        Optional<Pets> pets = petsRepository.findById(1L);

        VisitsRequestDto.CREATE create = VisitsRequestDto.CREATE.builder()
                .visitDate(LocalDate.of(2023, 10, 12))
                .description("건강검진 및 예방접종")
                .pets(pets.get())
                .build();

        visitsService.createVisit(create);
        Optional<Visits> visits = visitsRepository.findById(1L);
        assertThat(visits.get().getDescription()).startsWith("건강검진");
    }

    @Test
    void getVisitList() {
        List<VisitsResponseDto.READ> readList = visitsService.getVisitList();

        assertThat(readList.get(0).getDescription()).startsWith("건강검진");
        assertThat(visitsRepository.findAll().size()).isEqualTo(readList.size());
    }

    @Test
    void getVisit() {
        VisitsResponseDto.DETAIL_READ detailRead = visitsService.getVisit(1L);
        assertThat(detailRead.getOwnerFirstName()).isEqualTo("수혁");
        assertThat(detailRead.getPetName()).isEqualTo("멍멍이");
    }

    @Test
    void getVisitListOfPet() {
        List<VisitsResponseDto.READ> visitListOfPet = visitsService.getVisitListOfPet(1L);

        assertThat(visitListOfPet.size()).isEqualTo(1);
    }

    @Test
    void updateVisit() {
        Optional<Pets> pets = petsRepository.findById(1L);

        VisitsRequestDto.UPDATE update = VisitsRequestDto.UPDATE.builder()
                .visitId(1L)
                .visitDate(LocalDate.of(2023, 05, 01))
                .description("수정테스트")
                .pets(pets.get())
                .build();

        visitsService.updateVisit(update);
        assertThat(visitsRepository.findById(1L).get().getDescription()).isEqualTo("수정테스트");
    }

    @Test
    void deleteVisit() {
        visitsService.deleteVisit(1L);
        assertThat(visitsRepository.findById(1L)).isEmpty();
    }
}