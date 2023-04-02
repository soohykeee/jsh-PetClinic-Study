package kr.co.jshpetclinicstudy.service;

import jakarta.transaction.Transactional;
import kr.co.jshpetclinicstudy.persistence.entity.Visits;
import kr.co.jshpetclinicstudy.persistence.repository.VisitsRepository;
import kr.co.jshpetclinicstudy.service.model.VisitsRequestDto;
import kr.co.jshpetclinicstudy.service.model.VisitsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitsService {

    private final VisitsRepository visitsRepository;

    public void createVisit(VisitsRequestDto.CREATE create) {
        final Visits visits = Visits.dtoToEntity(create);
        visitsRepository.save(visits);
    }

    public List<VisitsResponseDto.READ> getVisitList() {
        return visitsRepository.findAll().stream().map(Visits::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    public VisitsResponseDto.DETAIL_READ getVisit(Long id) {
        return Visits.entityToDetailDto(visitsRepository.findById(id).get());
    }

    @Transactional
    public List<VisitsResponseDto.READ> getVisitListOfPet(Long petId) {
        return visitsRepository.findVisitListByPetId(petId).stream().map(Visits::entityToDto).collect(Collectors.toList());
    }

    public void updateVisit(VisitsRequestDto.UPDATE update) {
        final Optional<Visits> visits = visitsRepository.findById(update.getVisitId());
        isVisits(visits);

        visits.get().changeVisitDate(update.getVisitDate());
        visits.get().changeVisitDescription(update.getDescription());
        visits.get().changeVisitPet(update.getPets());

        visitsRepository.save(visits.get());
    }

    public void deleteVisit(Long id) {
        final Optional<Visits> visits = visitsRepository.findById(id);
        isVisits(visits);
        visitsRepository.deleteById(id);
    }

    private void isVisits(Optional<Visits> visits) {
        if (visits.isEmpty()) {
            throw new RuntimeException("This Visits is Not Exist");
        }
    }
}
