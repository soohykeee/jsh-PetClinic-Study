package kr.co.jshpetclinicstudy.service;

import jakarta.transaction.Transactional;
import kr.co.jshpetclinicstudy.infra.exception.NotFoundException;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import kr.co.jshpetclinicstudy.persistence.entity.Visit;
import kr.co.jshpetclinicstudy.persistence.repository.PetRepository;
import kr.co.jshpetclinicstudy.persistence.repository.VetRepository;
import kr.co.jshpetclinicstudy.persistence.repository.VisitRepository;
import kr.co.jshpetclinicstudy.persistence.repository.search.VisitSearchRepository;
import kr.co.jshpetclinicstudy.service.model.mapper.VisitMapper;
import kr.co.jshpetclinicstudy.service.model.request.VisitRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VisitResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    private final VisitSearchRepository visitSearchRepository;

    private final VetRepository vetRepository;

    private final PetRepository petRepository;

    private final VisitMapper visitMapper;

    @Transactional
    public void createVisit(VisitRequestDto.CREATE create) {
        Optional<Pet> pet = petRepository.findById(create.getPetId());
        isPet(pet);
        Optional<Vet> vet = vetRepository.findById(create.getVetId());
        isVet(vet);

        final Visit visit = visitMapper.toEntity(create, pet.get(), vet.get());
        visitRepository.save(visit);
    }

    public List<VisitResponseDto.READ> getVisitsByCondition(VisitRequestDto.CONDITION condition) {
        final List<Visit> visits = visitSearchRepository.find(condition);

        return visits.stream()
                .map(visitMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateVisit(VisitRequestDto.UPDATE update) {
        final Optional<Visit> visit = visitRepository.findById(update.getVisitId());

        isVisit(visit);

        visit.get().updateVisit(update);

        visitRepository.save(visit.get());
    }

    @Transactional
    public void deleteVisit(Long id) {
        final Optional<Visit> visit = visitRepository.findById(id);
        isVisit(visit);
        visitRepository.deleteById(id);
    }

    private void isVisit(Optional<Visit> visit) {
        if (visit.isEmpty()) {
            throw new NotFoundException(ResponseStatus.FAIL_NOT_FOUND);
        }
    }

    private void isPet(Optional<Pet> pet) {
        if (pet.isEmpty()) {
            throw new NotFoundException(ResponseStatus.FAIL_NOT_FOUND);
        }
    }

    private void isVet(Optional<Vet> vet) {
        if (vet.isEmpty()) {
            throw new NotFoundException(ResponseStatus.FAIL_NOT_FOUND);
        }
    }
}
