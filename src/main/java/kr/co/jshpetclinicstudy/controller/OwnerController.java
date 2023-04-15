package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.infra.model.ResponseFormat;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import kr.co.jshpetclinicstudy.service.OwnerService;
import kr.co.jshpetclinicstudy.service.model.request.OwnerRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.OwnerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
        // TODO - DuplicatedException - Owner Phone is Not Unique Case
    }

//    public List<OwnerResponseDto.READ> getOwnerList() {
//        return ownerService.getOwnerList();
//    }

    /**
     * Read(Get) Owner API
     *
     * @param ownerId
     * @return
     */
    @GetMapping("/{owner_id}")
    public ResponseFormat<OwnerResponseDto.READ> getOwner(@PathVariable(name = "owner_id") Long ownerId) {
        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, ownerService.getOwner(ownerId));
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
        // TODO - NotFoundException - If Owner is Empty Case
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
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
        // TODO - DuplicatedException - Owner Phone is Not Unique Case
        // TODO - NotFoundException - If Owner is Empty Case

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
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
        // TODO - NotFoundException - If Owner is Empty Case
    }

}