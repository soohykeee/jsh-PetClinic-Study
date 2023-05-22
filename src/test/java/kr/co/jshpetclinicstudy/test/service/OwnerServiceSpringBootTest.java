package kr.co.jshpetclinicstudy.test.service;

import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import kr.co.jshpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.jshpetclinicstudy.service.OwnerService;
import kr.co.jshpetclinicstudy.service.model.request.OwnerRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.OwnerResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OwnerServiceSpringBootTest {

//    @Autowired
//    private OwnerService ownerService;
//    @Autowired
//    private OwnerRepository ownerRepository;

//    @Test
//    void createOwner() {
//        OwnerRequestDto.CREATE create = OwnerRequestDto.CREATE.builder()
//                .firstName("수혁")
//                .lastName("장")
//                .address("도봉구 창동")
//                .city("서울시")
//                .telephone("01064564655")
//                .build();
//
//        ownerService.createOwner(create);
//        Optional<Owner> owner = ownerRepository.findOwnerByTelephone("01064564655");
//        assertThat(owner.get().getFirstName()).isEqualTo("수혁");
//    }
//
//    @Test
//    void getOwnerList() {
////        List<OwnerResponseDto.READ> readList = ownerService.getOwnerList();
////
////        assertThat(readList.get(0).getFirstName()).isEqualTo("수혁");
////        assertThat(ownerRepository.findAll().size()).isEqualTo(readList.size());
//    }
//
//    @Test
//    void getOwner() {
////        OwnerResponseDto.READ read = ownerService.getOwner(1L);
////        assertThat(read.getTelephone()).isEqualTo("01064564655");
//    }
//
//    @Test
//    void updateOwner() {
//        OwnerRequestDto.UPDATE update = OwnerRequestDto.UPDATE.builder()
//                .ownerId(1L)
//                .firstName("철수")
//                .lastName("김")
//                .address("성북구 정릉동")
//                .city("서울시")
//                .telephone("01064564655")
//                .build();
//
//        ownerService.updateOwner(update);
//        assertThat(ownerRepository.findById(1L).get().getFirstName()).isEqualTo("철수");
//    }
//
//    @Test
//    void deleteOwner() {
//        ownerService.deleteOwner(1L);
//        assertThat(ownerRepository.findById(1L)).isEmpty();
//    }

}