package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.infra.model.ResponseFormat;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import kr.co.jshpetclinicstudy.service.VisitService;
import kr.co.jshpetclinicstudy.service.model.request.VisitRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VisitResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/visits")
public class VisitController {

    private final VisitService visitService;

    /**
     * Create Visit API
     *
     * @param create
     * @return
     */
    @PostMapping
    public ResponseFormat<Void> createVisit(@RequestBody @Valid VisitRequestDto.CREATE create) {
        try {
            visitService.createVisit(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
        // TODO - NotFoundException - If Pet or Owner is Empty Case
    }

//    public List<VisitResponseDto.READ> getVisitList() {
//        return visitService.getVisitList();
//    }

    /**
     * Read(Get) Visit API
     *
     * @param visitId
     * @return
     */
    @GetMapping("/{visit_id}")
    public ResponseFormat<VisitResponseDto.READ> getVisit(@PathVariable(name = "visit_id") Long visitId) {
        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, visitService.getVisit(visitId));
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
        // TODO - NotFoundException - If Visit is Empty Case
    }

    /**
     * Read(Get) VisitList Of Pet API
     *
     * @param petId
     * @return
     */
    @GetMapping("/pets/{pet_id}")
    public ResponseFormat<List<VisitResponseDto.READ>> getVisitListOfPet(@PathVariable(name = "pet_id") Long petId) {
        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, visitService.getVisitListOfPet(petId));
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
        // TODO - NotFoundException - If Pet is Empty Case
    }

    /**
     * Update Visit API
     *
     * @param update
     * @return
     */
    @PutMapping
    public ResponseFormat<Void> updateVisit(@RequestBody @Valid VisitRequestDto.UPDATE update) {
        try {
            visitService.updateVisit(update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
        // TODO - NotFoundException - If Visit is Empty Case
    }

    /**
     * Delete Visit API
     *
     * @param visitId
     * @return
     */
    @DeleteMapping("/{visit_id}")
    public ResponseFormat<Void> deleteVisit(@PathVariable(name = "visit_id") Long visitId) {
        try {
            visitService.deleteVisit(visitId);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
        // TODO - NotFoundException - If Visit is Empty Case
    }
}
