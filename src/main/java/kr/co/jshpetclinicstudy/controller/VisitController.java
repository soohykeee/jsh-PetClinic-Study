package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.service.VisitService;
import kr.co.jshpetclinicstudy.service.model.request.VisitRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VisitResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/visits/")
public class VisitController {

    private final VisitService visitService;

    public void createVisit(@RequestBody @Valid VisitRequestDto.CREATE create) {
        visitService.createVisit(create);
    }

    public List<VisitResponseDto.READ> getVisitList() {
        return visitService.getVisitList();
    }

    public VisitResponseDto.READ getVisit(Long id) {
        VisitResponseDto.READ read = visitService.getVisit(id);
        return read;
    }

    public List<VisitResponseDto.READ> getVisitListOfPet(Long petId) {
        return visitService.getVisitListOfPet(petId);
    }

    public void updateVisit(@RequestBody @Valid VisitRequestDto.UPDATE update) {
        visitService.updateVisit(update);
    }

    public void deleteVisit(Long id) {
        visitService.deleteVisit(id);
    }
}
