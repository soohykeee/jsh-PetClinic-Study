package kr.co.jshpetclinicstudy.test.service;

import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import kr.co.jshpetclinicstudy.persistence.repository.VetRepository;
import kr.co.jshpetclinicstudy.service.VetService;
import kr.co.jshpetclinicstudy.service.model.request.VetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VetServiceSpringBootTest {

    @Autowired
    private VetService vetService;
    @Autowired
    private VetRepository vetRepository;


//    @Test
//    void createVet() {
//
//        List<String> testName = new ArrayList<>();
//        testName.add("test1");
//        testName.add("test2");
//        testName.add("test3");
//
//        VetRequestDto.CREATE create = VetRequestDto.CREATE.builder()
//                .firstName("닥터")
//                .lastName("김")
//                .specialtiesName(testName)
//                .build();
//
//        vetService.createVet(create);
//        Optional<Vet> vet = vetRepository.findById(1L);
//
//        assertThat(vet.get().getFirstName()).isEqualTo("닥터");
//    }
//
//    @Test
//    void getVet() {
//        VetResponseDto.READ read = vetService.getVet(1L);
//        assertThat(read.getFirstName()).isEqualTo("닥터");
//    }
//
//    @Test
//    void updateVet() {
//        List<String> testName = new ArrayList<>();
//        testName.add("update_test1");
//        testName.add("test2");
//        testName.add("test3");
//
//        VetRequestDto.UPDATE update = VetRequestDto.UPDATE.builder()
//                .vetId(1L)
//                .firstName("민수")
//                .lastName("이")
//                .specialtiesName(testName)
//                .build();
//
//        vetService.updateVet(update);
////        assertThat(vetRepository.findById(1L).get().getFirstName()).isEqualTo("민수");
//    }
//
//    @Test
//    void deleteVet() {
//        vetService.deleteVet(1L);
//        assertThat(vetRepository.findById(1L)).isEmpty();
//    }

}