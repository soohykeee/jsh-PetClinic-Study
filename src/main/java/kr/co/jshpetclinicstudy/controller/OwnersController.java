package kr.co.jshpetclinicstudy.controller;

import kr.co.jshpetclinicstudy.persistence.dto.OwnersDto;
import kr.co.jshpetclinicstudy.service.OwnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/owners")
public class OwnersController {

    private final OwnersService ownersService;

    @GetMapping("/list")
    public void list() {

    }

    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String registerPost(OwnersDto dto) {
        ownersService.createOwners(dto);

        return "redirect:/api/v1/";
    }
}
