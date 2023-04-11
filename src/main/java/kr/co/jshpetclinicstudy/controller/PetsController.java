package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.service.PetsService;
import kr.co.jshpetclinicstudy.service.model.request.PetsRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.PetsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pets/")
public class PetsController {

    private final PetsService petsService;

    public void createPet(@RequestBody @Valid PetsRequestDto.CREATE create) {
        petsService.createPet(create);
    }

    public List<PetsResponseDto.READ> getPetList() {
        return petsService.getPetList();
    }

    public PetsResponseDto.DETAIL_READ getPet(Long id) {
        PetsResponseDto.DETAIL_READ detailRead = petsService.getPet(id);
        return detailRead;
    }

    public List<PetsResponseDto.READ> getPetListOfOwner(Long ownerId) {
        return petsService.getPetListOfOwner(ownerId);
    }

    public void updatePet(@RequestBody @Valid PetsRequestDto.UPDATE update) {
        petsService.updatePet(update);
    }

    public void deletePet(Long id) {
        petsService.deletePet(id);
    }


}
