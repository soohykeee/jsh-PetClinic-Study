package kr.co.jshpetclinicstudy.controller;

import kr.co.jshpetclinicstudy.service.VisitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class VisitsController {

    private final VisitsService visitsService;
}
