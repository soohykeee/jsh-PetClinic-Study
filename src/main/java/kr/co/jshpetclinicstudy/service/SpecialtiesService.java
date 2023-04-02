package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.repository.SpecialtiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialtiesService {

    private final SpecialtiesRepository specialtiesRepository;

}
