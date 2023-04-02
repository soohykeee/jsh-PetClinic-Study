package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.repository.VetsSpecialtiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VetsSpecialtiesService {

    private final VetsSpecialtiesRepository vetsSpecialtiesRepository;
}
