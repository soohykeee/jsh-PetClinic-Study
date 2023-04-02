package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.service.VisitsService;
import kr.co.jshpetclinicstudy.service.model.PetsResponseDto;
import kr.co.jshpetclinicstudy.service.model.VisitsRequestDto;
import kr.co.jshpetclinicstudy.service.model.VisitsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/visits/")
public class VisitsController {

    private final VisitsService visitsService;

    public void createVisit(@RequestBody @Valid VisitsRequestDto.CREATE create) {
        visitsService.createVisit(create);
    }

    public List<VisitsResponseDto.READ> getVisitList() {
        return visitsService.getVisitList();
    }

    public VisitsResponseDto.DETAIL_READ getVisit(Long id) {
        VisitsResponseDto.DETAIL_READ detailRead = visitsService.getVisit(id);
        return detailRead;
    }

    public List<VisitsResponseDto.READ> getVisitListOfPet(Long petId) {
        return visitsService.getVisitListOfPet(petId);
    }

    public void updateVisit(@RequestBody @Valid VisitsRequestDto.UPDATE update) {
        visitsService.updateVisit(update);
    }

    public void deleteVisit(Long id) {
        visitsService.deleteVisit(id);
    }
}
