package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.service.VetService;
import kr.co.jshpetclinicstudy.service.model.request.VetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vets/")
public class VetController {

    private final VetService vetService;

    public void createVet(@RequestBody @Valid VetRequestDto.CREATE create) {
        vetService.createVet(create);
    }

//    public List<VetResponseDto.READ> getVetList() {
//        return vetService.getVetList();
//    }

    public VetResponseDto.READ getVet(Long id) {
        VetResponseDto.READ read = vetService.getVet(id);
        return read;
    }

    public void updateVet(@RequestBody @Valid VetRequestDto.UPDATE update) {
        vetService.updateVet(update);
    }

    public void deleteVet(Long id) {
        vetService.deleteVet(id);
    }

}