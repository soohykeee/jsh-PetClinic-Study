package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.service.SpecialtiesService;
import kr.co.jshpetclinicstudy.service.model.SpecialtiesRequestDto;
import kr.co.jshpetclinicstudy.service.model.SpecialtiesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/specialties/")
public class SpecialtiesController {

    private final SpecialtiesService specialtiesService;

    public void createSpecialty(@RequestBody @Valid SpecialtiesRequestDto.CREATE create) {
        specialtiesService.createSpecialty(create);
    }

    public List<SpecialtiesResponseDto.READ> getSpecialtyList() {
        return specialtiesService.getSpecialtyList();
    }

    public SpecialtiesResponseDto.DETAIL_READ getSpecialty(Long id) {
        SpecialtiesResponseDto.DETAIL_READ detailRead = specialtiesService.getSpecialty(id);
        return detailRead;
    }

    public void updateSpecialty(@RequestBody @Valid SpecialtiesRequestDto.UPDATE update) {
        specialtiesService.updateSpecialty(update);
    }

    public void deleteSpecialty(Long id) {
        specialtiesService.deleteSpecialty(id);
    }

}