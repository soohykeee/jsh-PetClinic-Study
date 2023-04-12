package kr.co.jshpetclinicstudy.service;

import jakarta.transaction.Transactional;
import kr.co.jshpetclinicstudy.persistence.entity.Visit;
import kr.co.jshpetclinicstudy.persistence.repository.VisitRepository;
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

    private final VisitMapper visitMapper;

    @Transactional
    public void createVisit(VisitRequestDto.CREATE create) {
        final Visit visit = visitMapper.toEntity(create);
        visitRepository.save(visit);
    }

    @Transactional
    public List<VisitResponseDto.READ> getVisitList() {
        return visitRepository.findAll().stream()
                .map(visitMapper::toReadDto).collect(Collectors.toList());
    }

    @Transactional
    public VisitResponseDto.READ getVisit(Long id) {
        final Optional<Visit> visit = visitRepository.findById(id);
        isVisit(visit);
        return visitMapper.toReadDto(visit.get());
    }

    @Transactional
    public List<VisitResponseDto.READ> getVisitListOfPet(Long petId) {
        return visitRepository.findVisitListByPetId(petId).stream()
                .map(visitMapper::toReadDto).collect(Collectors.toList());
    }

    @Transactional
    public void updateVisit(VisitRequestDto.UPDATE update) {
        final Optional<Visit> visit = visitRepository.findById(update.getVisitId());
        isVisit(visit);

        visit.get().changeVisitDate(update.getVisitDate());
        visit.get().changeVisitDescription(update.getDescription());
        visit.get().changeVisitPet(update.getPet());

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
            throw new RuntimeException("This Visit is Not Exist");
        }
    }
}