package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.service.VetsService;
import kr.co.jshpetclinicstudy.service.model.request.VetsRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vets/")
public class VetsController {

    private final VetsService vetsService;

    public void createVet(@RequestBody @Valid VetsRequestDto.CREATE create) {
        vetsService.createVet(create);
    }

    public List<VetsResponseDto.READ> getVetList() {
        return vetsService.getVetList();
    }

    public VetsResponseDto.DETAIL_READ getVet(Long id) {
        VetsResponseDto.DETAIL_READ detailRead = vetsService.getVet(id);
        return detailRead;
    }

    public void updateVet(@RequestBody @Valid VetsRequestDto.UPDATE update) {
        vetsService.updateVet(update);
    }

    public void deleteVet(Long id) {
        vetsService.deleteVet(id);
    }

}