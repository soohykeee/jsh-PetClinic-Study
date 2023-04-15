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
        try {
            petService.createPet(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Read(Get) Pet API
     *
     * @param petId
     * @return
     */
    @GetMapping("/{pet_id}")
    public ResponseFormat<PetResponseDto.READ> getPet(@PathVariable(name = "pet_id") Long petId) {
        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, petService.getPet(petId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Read(Get) PetList Of Owner API
     *
     * @param ownerId
     * @return
     */
    @GetMapping("/owner/{owner_id}")
    public ResponseFormat<List<PetResponseDto.READ>> getPetListOfOwner(@PathVariable(name = "owner_id") Long ownerId) {
        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, petService.getPetListOfOwner(ownerId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Update Pet API
     *
     * @param update
     * @return
     */
    @PutMapping
    public ResponseFormat<Void> updatePet(@RequestBody @Valid PetRequestDto.UPDATE update) {
        try {
            petService.updatePet(update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Delete Pet API
     *
     * @param petId
     * @return
     */
    @DeleteMapping("/{pet_id}")
    public ResponseFormat<Void> deletePet(@PathVariable(name = "pet_id") Long petId) {
        try {
            petService.deletePet(petId);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

}
