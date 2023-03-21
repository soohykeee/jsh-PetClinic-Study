package kr.co.jshpetclinicstudy.controller;

import kr.co.jshpetclinicstudy.service.PetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PetsController {

    private final PetsService petsService;
}
