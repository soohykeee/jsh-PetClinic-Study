package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.infra.exception.NotFoundException;
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
        visitService.createVisit(create);

        return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
    }

    /**
     * Read(Get) Visit API
     *
     * @param condition
     * @return
     */
    @PostMapping("/search")
    public ResponseFormat<List<VisitResponseDto.READ>> getVisitsByCondition(@RequestBody @Valid VisitRequestDto.CONDITION condition) {
        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, visitService.getVisitsByCondition(condition));
    }

    /**
     * Update Visit API
     *
     * @param update
     * @return
     */
    @PutMapping
    public ResponseFormat<Void> updateVisit(@RequestBody @Valid VisitRequestDto.UPDATE update) {
        visitService.updateVisit(update);

        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }

    /**
     * Delete Visit API
     *
     * @param visitId
     * @return
     */
    @DeleteMapping("/{visit_id}")
    public ResponseFormat<Void> deleteVisit(@PathVariable(name = "visit_id") Long visitId) {
        visitService.deleteVisit(visitId);

        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }
}
