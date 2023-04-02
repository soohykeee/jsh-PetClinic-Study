package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.repository.VetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VetsService {

    private final VetsRepository vetsRepository;
}
