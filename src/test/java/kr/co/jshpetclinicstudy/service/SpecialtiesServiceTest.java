package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.entity.Specialties;
import kr.co.jshpetclinicstudy.persistence.repository.SpecialtiesRepository;
import kr.co.jshpetclinicstudy.service.model.request.SpecialtiesRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.SpecialtiesResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpecialtiesServiceTest {

    @Autowired
    private SpecialtiesService specialtiesService;
    @Autowired
    private SpecialtiesRepository specialtiesRepository;

    @Test
    void createSpecialty() {
        SpecialtiesRequestDto.CREATE create = SpecialtiesRequestDto.CREATE.builder()
                .name("내과")
                .build();

        specialtiesService.createSpecialty(create);
        Optional<Specialties> specialties = specialtiesRepository.findById(1L);
        assertThat(specialties.get().getName()).isEqualTo("내과");
    }

    @Test
    void getSpecialtyList() {
        List<SpecialtiesResponseDto.READ> readList = specialtiesService.getSpecialtyList();

        assertThat(readList.get(0).getName()).isEqualTo("내과");
        assertThat(specialtiesRepository.findAll().size()).isEqualTo(readList.size());
    }

    @Test
    void getSpecialty() {
        SpecialtiesResponseDto.DETAIL_READ detailRead = specialtiesService.getSpecialty(1L);
        assertThat(detailRead.getName()).isEqualTo("내과");
    }

    @Test
    void updateSpecialty() {
        SpecialtiesRequestDto.UPDATE update = SpecialtiesRequestDto.UPDATE.builder()
                .specialtyId(1L)
                .name("외과")
                .build();

        specialtiesService.updateSpecialty(update);
        assertThat(specialtiesRepository.findById(1L).get().getName()).isEqualTo("외과");
    }

    @Test
    void deleteSpecialty() {
        specialtiesService.deleteSpecialty(1L);
        assertThat(specialtiesRepository.findById(1L)).isEmpty();
    }
}