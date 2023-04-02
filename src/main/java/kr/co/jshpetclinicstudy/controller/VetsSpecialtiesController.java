package kr.co.jshpetclinicstudy.controller;

import kr.co.jshpetclinicstudy.service.VetsSpecialtiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vetsSpecialties/")
public class VetsSpecialtiesController {

    private final VetsSpecialtiesService vetsSpecialtiesService;

    /*public void createVetAndSpec(@RequestBody @Valid VetsSpecialtiesRequestDto.CREATE create) {
        vetsSpecialtiesService.createVetAndSpec(create);
    }

    // 해당 학위를 갖고있는 수의사들 목록 조회
    public List<VetsSpecialtiesResponseDto.READ_VETS> getVetListOfSpec(Long specialtyId) {
        return vetsSpecialtiesService.getVetListOfSpec(specialtyId);
    }

    // 해당 수의사가 갖고있는 학위들 목록
    public List<VetsSpecialtiesResponseDto.READ_SPEC> getSpecListOfVet(Long vetId) {
        return vetsSpecialtiesService.getSpecListOfVet(vetId);
    }*/

    /**
     * update
     *
     * delete
     */

}