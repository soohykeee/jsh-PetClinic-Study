package kr.co.jshpetclinicstudy.controller;

import jakarta.validation.Valid;
import kr.co.jshpetclinicstudy.service.OwnersService;
import kr.co.jshpetclinicstudy.service.model.dtos.OwnersRequestDto;
import kr.co.jshpetclinicstudy.service.model.dtos.OwnersResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/owners")
public class OwnersController {

    private final OwnersService ownersService;

    //CRUD 개발 시작 기능은 만들었다고 가정
    public void createOwner(@RequestBody @Valid OwnersRequestDto.CREATE create){
        ownersService.createOwner(create);
    }

    public List<OwnersResponseDto.READ> getOwnerList() {
        return ownersService.getOwnerList();
    }

    public OwnersResponseDto.DETAIL_READ getOwner(Long id) {
        OwnersResponseDto.DETAIL_READ detailRead = ownersService.getOwner(id);
        return detailRead;
    }

    public void updateOwner(@RequestBody @Valid OwnersRequestDto.UPDATE update) {
        ownersService.updateOwner(update);
    }

    public void deleteOwner(Long id) {
        ownersService.deleteOwner(id);
    }

}
