package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.repository.OwnersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnersService {

    private final OwnersRepository ownersRepository;

}
