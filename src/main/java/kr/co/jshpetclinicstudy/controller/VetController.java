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

import java.util.List;
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
        vetService.createVet(create);

        return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);

    }

    /**
     * Get Vet API
     *
     * @param condition
     * @return
     */
    @PostMapping("/search")
    public ResponseFormat<List<VetResponseDto.READ>> getVetsByCondition(@RequestBody @Valid VetRequestDto.CONDITION condition) {
        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getVetsByCondition(condition));
    }

    /**
     * Get All Specialties API
     *
     * @return
     */
    @GetMapping("/specialties")
    public ResponseFormat<Set<String>> getSpecialties() {
        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, vetService.getSpecialties());
    }

    /**
     * Update Vet API
     *
     * @param update
     * @return
     */
    @PutMapping
    public ResponseFormat<Void> updateVet(@RequestBody @Valid VetRequestDto.UPDATE update) {
        vetService.updateVet(update);

        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }

    /**
     * Delete Vet API
     *
     * @param vetId
     * @return
     */
    @DeleteMapping("/{vet_id}")
    public ResponseFormat<Void> deleteVet(@PathVariable(name = "vet_id") Long vetId) {
        vetService.deleteVet(vetId);

        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }

}