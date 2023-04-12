package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.service.PetService;
import kr.co.jshpetclinicstudy.service.model.request.PetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.PetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pets/")
public class PetController {

    private final PetService petService;

    public void createPet(@RequestBody @Valid PetRequestDto.CREATE create) {
        petService.createPet(create);
    }

    public List<PetResponseDto.READ> getPetList() {
        return petService.getPetList();
    }

    public PetResponseDto.READ getPet(Long id) {
        PetResponseDto.READ read = petService.getPet(id);
        return read;
    }

    public List<PetResponseDto.READ> getPetListOfOwner(Long ownerId) {
        return petService.getPetListOfOwner(ownerId);
    }

    public void updatePet(@RequestBody @Valid PetRequestDto.UPDATE update) {
        petService.updatePet(update);
    }

    public void deletePet(Long id) {
        petService.deletePet(id);
    }


}
