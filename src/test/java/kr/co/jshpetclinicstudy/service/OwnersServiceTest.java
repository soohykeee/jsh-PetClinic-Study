package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.entity.Owners;
import kr.co.jshpetclinicstudy.persistence.repository.OwnersRepository;
import kr.co.jshpetclinicstudy.service.model.request.OwnersRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.OwnersResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OwnersServiceTest {

    @Autowired
    private OwnersService ownersService;
    @Autowired
    private OwnersRepository ownersRepository;

    @Test
    void createOwner() {
        OwnersRequestDto.CREATE create = OwnersRequestDto.CREATE.builder()
                .firstName("수혁")
                .lastName("장")
                .address("도봉구 창동")
                .city("서울시")
                .telephone("01064564655")
                .build();

        ownersService.createOwner(create);
        Optional<Owners> owners = ownersRepository.findOwnerByTelephone("01064564655");
        assertThat(owners.get().getFirstName()).isEqualTo("수혁");
    }

    @Test
    void getOwnerList() {
        List<OwnersResponseDto.READ> readList = ownersService.getOwnerList();

        assertThat(readList.get(0).getFirstName()).isEqualTo("수혁");
        assertThat(ownersRepository.findAll().size()).isEqualTo(readList.size());
    }

    @Test
    void getOwner() {
        OwnersResponseDto.DETAIL_READ detailRead = ownersService.getOwner(2L);
        assertThat(detailRead.getTelephone()).isEqualTo("01064564655");
    }

    @Test
    void updateOwner() {
        OwnersRequestDto.UPDATE update = OwnersRequestDto.UPDATE.builder()
                .ownerId(2L)
                .firstName("철수")
                .lastName("김")
                .address("성북구 정릉동")
                .city("서울시")
                .telephone("01064564655")
                .build();

        ownersService.updateOwner(update);
        assertThat(ownersRepository.findById(2L).get().getFirstName()).isEqualTo("철수");
    }

    @Test
    void deleteOwner() {
        ownersService.deleteOwner(2L);
        assertThat(ownersRepository.findById(2L)).isEmpty();
    }

}