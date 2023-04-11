package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.entity.Vets;
import kr.co.jshpetclinicstudy.persistence.repository.VetsRepository;
import kr.co.jshpetclinicstudy.service.model.request.VetsRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetsResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VetsServiceTest {

    @Autowired
    private VetsService vetsService;
    @Autowired
    private VetsRepository vetsRepository;

    @Test
    void createVet() {

        VetsRequestDto.CREATE create = VetsRequestDto.CREATE.builder()
                .firstName("닥터")
                .lastName("김")
//                .specialties()
                .build();

        vetsService.createVet(create);
        Optional<Vets> vets = vetsRepository.findById(1L);
        assertThat(vets.get().getFirstName()).isEqualTo("닥터");
    }

    @Test
    void getVetList() {
        List<VetsResponseDto.READ> readList = vetsService.getVetList();

        assertThat(readList.get(0).getFirstName()).isEqualTo("닥터");
        assertThat(vetsRepository.findAll().size()).isEqualTo(readList.size());
    }

    @Test
    void getVet() {
        VetsResponseDto.READ read = vetsService.getVet(1L);
        assertThat(read.getFirstName()).isEqualTo("닥터");
    }

    @Test
    void updateVet() {
        VetsRequestDto.UPDATE update = VetsRequestDto.UPDATE.builder()
                .vetId(1L)
                .firstName("민수")
                .lastName("이")
                .build();

        vetsService.updateVet(update);
        assertThat(vetsRepository.findById(1L).get().getFirstName()).isEqualTo("민수");
    }

    @Test
    void deleteVet() {
        vetsService.deleteVet(1L);
        assertThat(vetsRepository.findById(1L)).isEmpty();
    }

}