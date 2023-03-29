package kr.co.jshpetclinicstudy.controller;

import kr.co.jshpetclinicstudy.service.SpecialtiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SpecialtiesController {

    private final SpecialtiesService specialtiesService;

}
