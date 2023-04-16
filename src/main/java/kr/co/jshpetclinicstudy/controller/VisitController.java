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
        try {
            visitService.createVisit(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

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
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Read(Get) Visits Of Pet API
     *
     * @param petId
     * @return
     */
    @GetMapping("/pets/{pet_id}")
    public ResponseFormat<List<VisitResponseDto.READ>> getVisitsByPet(@PathVariable(name = "pet_id") Long petId) {
        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, visitService.getVisitsByPet(petId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Read(Get) Visits Of Owner API
     *
     * @param ownerId
     * @return
     */
    @GetMapping("/owners/{owner_id}")
    public ResponseFormat<List<VisitResponseDto.READ>> getVisitsByOwner(@PathVariable(name = "owner_id") Long ownerId) {
        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, visitService.getVisitsByOwner(ownerId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Read(Get) Visits Of Vet API
     *
     * @param vetId
     * @return
     */
    @GetMapping("/vets/{vet_id}")
    public ResponseFormat<List<VisitResponseDto.READ>> getVisitsByVet(@PathVariable(name = "vet_id") Long vetId) {
        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, visitService.getVisitsByVet(vetId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
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
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
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
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }
}
