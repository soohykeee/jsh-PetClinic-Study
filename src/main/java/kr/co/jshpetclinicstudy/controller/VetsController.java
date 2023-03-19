package kr.co.jshpetclinicstudy.controller;

import kr.co.jshpetclinicstudy.service.VetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class VetsController {

    private final VetsService vetsService;
}
