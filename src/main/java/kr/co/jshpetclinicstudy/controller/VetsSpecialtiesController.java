package kr.co.jshpetclinicstudy.controller;

import kr.co.jshpetclinicstudy.service.VetsSpecialtiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class VetsSpecialtiesController {

    private final VetsSpecialtiesService vetsSpecialtiesService;
}
