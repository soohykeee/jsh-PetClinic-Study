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

//    @Transactional
//    public List<VisitResponseDto.READ> getVisitList() {
//        return visitRepository.findAll().stream()
//                .map(visitMapper::toReadDto).collect(Collectors.toList());
//    }
//
//    @Transactional
//    public VisitResponseDto.READ getVisit(Long id) {
//        final Optional<Visit> visit = visitRepository.findById(id);
//        isVisit(visit);
//        return visitMapper.toReadDto(visit.get());
//    }

    public List<VisitResponseDto.READ> getVisitsByCondition(VisitRequestDto.CONDITION condition) {
        final List<Visit> visits = visitSearchRepository.find(condition);

        return visits.stream()
                .map(visitMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<VisitResponseDto.READ> getVisitsByPet(Long petId) {
        return visitRepository
                .findVisitsByPetId(petId)
                .stream()
                .map(visitMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<VisitResponseDto.READ> getVisitsByOwner(Long ownerId) {
        return visitRepository
                .findVisitsByOwnerId(ownerId)
                .stream()
                .map(visitMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<VisitResponseDto.READ> getVisitsByVet(Long vetId) {
        return visitRepository
                .findVisitsByVetId(vetId)
                .stream()
                .map(visitMapper::toReadDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateVisit(VisitRequestDto.UPDATE update) {
        final Optional<Visit> visit = visitRepository.findById(update.getVisitId());
        isVisit(visit);

        visit.get().changeVisitDate(update.getVisitDate());
        visit.get().changeVisitDescription(update.getDescription());
        Optional<Pet> changePet = petRepository.findById(update.getPetId());
        isPet(changePet);
        visit.get().changeVisitPet(changePet.get());

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
