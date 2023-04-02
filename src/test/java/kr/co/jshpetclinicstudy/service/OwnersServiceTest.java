package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.dto.OwnersDto;
import kr.co.jshpetclinicstudy.persistence.entity.Owners;
import kr.co.jshpetclinicstudy.persistence.repository.OwnersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OwnersServiceTest {

    @Autowired
    private OwnersService ownersService;
    @Autowired
    private OwnersRepository ownersRepository;

    @Test
    void createOwners() {
        OwnersDto dto = OwnersDto.builder()
                .firstName("수혁")
                .lastName("장")
                .address("도봉구 창동")
                .city("서울시")
                .telephone("01064564655")
                .build();

        ownersService.createOwners(dto);
    }

    @Test
    void getOwners() {
        OwnersDto ownersDto = ownersService.getOwners(1L);

        Assertions.assertThat(ownersDto.getFirstName()).isEqualTo("수혁");
    }

    @Test
    void modifyOwners() {
        /*OwnersDto dto = OwnersDto.builder()
                .ownerId(1L)
                .address("성북구 평창")
                .city("서울특별시")
                .telephone("01012341234")
                .build();

        ownersService.modifyOwners(dto);

        String phone = ownersRepository.findById(1L).get().getTelephone();
        Assertions.assertThat(phone).isEqualTo("01012341234");
        Assertions.assertThat(phone).isNotEqualTo("01064564655");*/
    }

    @Test
    void deleteOwners() {
        ownersService.deleteOwners(2L);

        Assertions.assertThat(ownersRepository.findById(2L).isEmpty()).isTrue();
    }
}