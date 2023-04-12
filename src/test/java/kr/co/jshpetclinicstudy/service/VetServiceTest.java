package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import kr.co.jshpetclinicstudy.persistence.repository.VetRepository;
import kr.co.jshpetclinicstudy.service.model.request.VetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VetServiceTest {

    @Autowired
    private VetService vetService;
    @Autowired
    private VetRepository vetRepository;

    @Test
    void createVet() {

        VetRequestDto.CREATE create = VetRequestDto.CREATE.builder()
                .firstName("닥터")
                .lastName("김")
//                .specialties()
                .build();

        vetService.createVet(create);
        Optional<Vet> vets = vetRepository.findById(1L);
        assertThat(vets.get().getFirstName()).isEqualTo("닥터");
    }

    @Test
    void getVetList() {
        List<VetResponseDto.READ> readList = vetService.getVetList();

        assertThat(readList.get(0).getFirstName()).isEqualTo("닥터");
        assertThat(vetRepository.findAll().size()).isEqualTo(readList.size());
    }

    @Test
    void getVet() {
        VetResponseDto.READ read = vetService.getVet(1L);
        assertThat(read.getFirstName()).isEqualTo("닥터");
    }

    @Test
    void updateVet() {
        VetRequestDto.UPDATE update = VetRequestDto.UPDATE.builder()
                .vetId(1L)
                .firstName("민수")
                .lastName("이")
                .build();

        vetService.updateVet(update);
        assertThat(vetRepository.findById(1L).get().getFirstName()).isEqualTo("민수");
    }

    @Test
    void deleteVet() {
        vetService.deleteVet(1L);
        assertThat(vetRepository.findById(1L)).isEmpty();
    }

}