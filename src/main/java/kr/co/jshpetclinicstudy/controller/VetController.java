package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.infra.exception.NotFoundException;
import kr.co.jshpetclinicstudy.infra.model.ResponseFormat;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import kr.co.jshpetclinicstudy.service.VetService;
import kr.co.jshpetclinicstudy.service.model.request.VetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vets")
public class VetController {

    private final VetService vetService;

    /**
     * Create Vet + Specialty API
     *
     * @param create
     * @return
     */
    @PostMapping
    public ResponseFormat<Void> createVet(@RequestBody @Valid VetRequestDto.CREATE create) {
        try {
            vetService.createVet(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Get Vet API
     *
     * @param vetId
     * @return
     */
    @GetMapping("/{vet_id}")
    public ResponseFormat<VetResponseDto.READ> getVet(@PathVariable(name = "vet_id") Long vetId) {
        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getVet(vetId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Get All Specialties API
     *
     * @return
     */
    @GetMapping("/specialties")
    public ResponseFormat<Set<String>> getSpecialties() {
        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getSpecialties());
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Update Vet API
     *
     * @param update
     * @return
     */
    @PutMapping
    public ResponseFormat<Void> updateVet(@RequestBody @Valid VetRequestDto.UPDATE update) {
        try {
            vetService.updateVet(update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Delete Vet API
     *
     * @param vetId
     * @return
     */
    @DeleteMapping("/{vet_id}")
    public ResponseFormat<Void> deleteVet(@PathVariable(name = "vet_id") Long vetId) {
        try {
            vetService.deleteVet(vetId);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

}