package kr.co.jshpetclinicstudy.controller;

import kr.co.jshpetclinicstudy.service.PetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/pets/")
public class PetsController {

    private final PetsService petsService;
}
