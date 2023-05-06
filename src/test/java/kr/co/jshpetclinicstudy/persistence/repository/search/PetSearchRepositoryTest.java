package kr.co.jshpetclinicstudy.persistence.repository.search;

import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import kr.co.jshpetclinicstudy.service.model.request.PetRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetSearchRepositoryTest {

    @Autowired
    private PetSearchRepository petSearchRepository;

    @Test
    public void querydsl_PetIds_Test() {

        //given
        List<Long> petIds = new ArrayList<>();
        petIds.add(1L);
        petIds.add(2L);

        PetRequestDto.CONDITION dto = PetRequestDto.CONDITION
                .builder()
                .petIds(petIds)
                .build();

        //when
        List<Pet> result = petSearchRepository.find(dto);

        //then
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getName(), "멍멍이푸들");

    }

    @Test
    public void querydsl_PetType_Test() {

        //given
        PetRequestDto.CONDITION dto = PetRequestDto.CONDITION
                .builder()
                .type("시바")
                .build();

        //when
        List<Pet> result = petSearchRepository.find(dto);

        //then
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), "멍멍이시바");
    }

}