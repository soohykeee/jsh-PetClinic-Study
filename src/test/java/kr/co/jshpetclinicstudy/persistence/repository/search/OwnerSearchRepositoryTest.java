package kr.co.jshpetclinicstudy.persistence.repository.search;

import com.mysema.commons.lang.Assert;
import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import kr.co.jshpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.jshpetclinicstudy.service.model.request.OwnerRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OwnerSearchRepositoryTest {

    @Autowired
    private OwnerSearchRepository ownerSearchRepository;

    @Test
    public void queryDsl_OwnerSearchIds_Test() {
        //given
        List<Long> ownerIds = new ArrayList<>();
        ownerIds.add(1L);
        ownerIds.add(2L);
        ownerIds.add(3L);

        OwnerRequestDto.CONDITION dto = OwnerRequestDto.CONDITION
                .builder()
                .ownerIds(ownerIds)
                .build();

        //when
        List<Owner> result = ownerSearchRepository.find(dto);

        // then;
        assertEquals(result.size(), 3);
        assertEquals(result.get(0).getFirstName(), "수혁");

    }

    @Test
    public void queryDsl_OwnerSearchTelephone_Test() {
        //given
        OwnerRequestDto.CONDITION dto = OwnerRequestDto.CONDITION
                .builder()
                .telephone("01064564655")
                .build();

        //when
        List<Owner> result = ownerSearchRepository.find(dto);

        // then;
        assertEquals(result.get(0).getFirstName(), "수혁");

    }

    @Test
    public void queryDsl_OwnerSearchFirstNameAndTelephone_Test() {
        //given
        OwnerRequestDto.CONDITION dto = OwnerRequestDto.CONDITION
                .builder()
                .firstName("수혁")
                .telephone("01064564655")
                .build();

        //when
        List<Owner> result = ownerSearchRepository.find(dto);

        // then;
        assertEquals(result.get(0).getLastName(), "장");

    }
}