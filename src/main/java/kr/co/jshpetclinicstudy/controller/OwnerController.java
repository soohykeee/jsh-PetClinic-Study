package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.infra.exception.DuplicatedException;
import kr.co.jshpetclinicstudy.infra.exception.NotFoundException;
import kr.co.jshpetclinicstudy.infra.model.ResponseFormat;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import kr.co.jshpetclinicstudy.service.OwnerService;
import kr.co.jshpetclinicstudy.service.model.request.OwnerRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.OwnerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    /**
     * Create Owner API
     *
     * @param create
     * @return
     */
    @PostMapping
    public ResponseFormat<Void> createOwner(@RequestBody @Valid OwnerRequestDto.CREATE create){
        try {
            ownerService.createOwner(create);
            return ResponseFormat.success(ResponseStatus.SUCCESS_CREATE);
        } catch (DuplicatedException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_TELEPHONE_DUPLICATED);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Read(Get) Owner API
     *
     * @param condition
     * @return
     */
    @PostMapping("/search")
    public ResponseFormat<List<OwnerResponseDto.READ>> getOwnersByCondition(@RequestBody @Valid OwnerRequestDto.CONDITION condition) {
        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, ownerService.getOwnersByCondition(condition));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /**
     * Update Owner API
     *
     * @param update
     * @return
     */
    @PutMapping
    public ResponseFormat<Void> updateOwner(@RequestBody @Valid OwnerRequestDto.UPDATE update) {
        try {
            ownerService.updateOwner(update);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (DuplicatedException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_TELEPHONE_DUPLICATED);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }

    }

    /**
     * Delete Owner API
     *
     * @param ownerId
     * @return
     */
    @DeleteMapping("/{owner_id}")
    public ResponseFormat<Void> deleteOwner(@PathVariable(name = "owner_id") Long ownerId) {

        try {
            ownerService.deleteOwner(ownerId);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

}