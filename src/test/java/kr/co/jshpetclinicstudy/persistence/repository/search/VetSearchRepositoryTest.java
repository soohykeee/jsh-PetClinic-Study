package kr.co.jshpetclinicstudy.persistence.repository.search;

import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import kr.co.jshpetclinicstudy.service.model.request.VetRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VetSearchRepositoryTest {

    @Autowired
    private VetSearchRepository vetSearchRepository;

    @Test
    public void querydsl_VetIds_Test() {
        //given
        List<Long> vetIds = new ArrayList<>();
        vetIds.add(1L);
        vetIds.add(2L);

        VetRequestDto.CONDITION dto = VetRequestDto.CONDITION
                .builder()
                .vetIds(vetIds)
                .build();

        //when
        List<Vet> result = vetSearchRepository.find(dto);

        // then;
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getFirstName(), "사부");
        assertEquals(result.get(1).getFirstName(), "국종");
    }

    @Test
    public void querydsl_findBySpecialty_Test() {
        //given

        VetRequestDto.CONDITION dto = VetRequestDto.CONDITION
                .builder()
                .specialtyName("이비인후과")
                .build();

        //when
//        List<Vet> result = vetSearchRepository.findBySpecialty(dto);
        List<Vet> result = vetSearchRepository.find(dto);

        // then;
        assertEquals(result.get(0).getFirstName(), "사부");
    }
}