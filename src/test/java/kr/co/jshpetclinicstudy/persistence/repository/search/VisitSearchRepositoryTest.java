package kr.co.jshpetclinicstudy.persistence.repository.search;

import kr.co.jshpetclinicstudy.persistence.entity.Visit;
import kr.co.jshpetclinicstudy.service.model.request.VisitRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VisitSearchRepositoryTest {

    @Autowired
    private VisitSearchRepository visitSearchRepository;

    @Test
    public void queryDsl_VisitSearchVisitDate_Test() {
        //given
        VisitRequestDto.CONDITION dto = VisitRequestDto.CONDITION
                .builder()
                .startDate(LocalDate.of(2023, 1, 1))
                .endDate(LocalDate.of(2023,10,1))
                .build();

        //when
        List<Visit> result = visitSearchRepository.find(dto);

        //then
        assertEquals(result.size(), 4);

    }

}