package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.infra.exception.NotFoundException;
import kr.co.jshpetclinicstudy.infra.model.ResponseFormat;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import kr.co.jshpetclinicstudy.service.PetService;
import kr.co.jshpetclinicstudy.service.model.request.PetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.PetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pets")
public class PetController {

    private final PetService petService;

    /**
     * Create Pet API
     *
     * @param create
     * @return
     */
    @PostMapping
    public ResponseFormat<Void> createPet(@RequestBody @Valid PetRequestDto.CREATE create) {
        petService.createPet(create);

        return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
    }

    /**
     * Read(Get) Pet API
     *
     * @param condition
     * @return
     */
    @PostMapping("/search")
    public ResponseFormat<List<PetResponseDto.READ>> getPetsByCondition(@RequestBody @Valid PetRequestDto.CONDITION condition) {
        return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, petService.getPetsByCondition(condition));
    }

    /**
     * Update Pet API
     *
     * @param update
     * @return
     */
    @PutMapping
    public ResponseFormat<Void> updatePet(@RequestBody @Valid PetRequestDto.UPDATE update) {
        petService.updatePet(update);

        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }

    /**
     * Delete Pet API
     *
     * @param petId
     * @return
     */
    @DeleteMapping("/{pet_id}")
    public ResponseFormat<Void> deletePet(@PathVariable(name = "pet_id") Long petId) {
        petService.deletePet(petId);

        return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
    }

}
