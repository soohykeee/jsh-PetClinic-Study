package kr.co.jshpetclinicstudy.controller;

import kr.co.jshpetclinicstudy.service.OwnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class OwnersController {

    private final OwnersService ownersService;

}
