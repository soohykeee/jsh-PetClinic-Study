package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.service.OwnerService;
import kr.co.jshpetclinicstudy.service.model.request.OwnerRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.OwnerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    //CRUD 개발 시작 기능은 만들었다고 가정
    public void createOwner(@RequestBody @Valid OwnerRequestDto.CREATE create){
        ownerService.createOwner(create);
    }

    public List<OwnerResponseDto.READ> getOwnerList() {
        return ownerService.getOwnerList();
    }

    public OwnerResponseDto.READ getOwner(Long id) {
        OwnerResponseDto.READ read = ownerService.getOwner(id);
        return read;
    }

    public void updateOwner(@RequestBody @Valid OwnerRequestDto.UPDATE update) {
        ownerService.updateOwner(update);
    }

    public void deleteOwner(Long id) {
        ownerService.deleteOwner(id);
    }

}