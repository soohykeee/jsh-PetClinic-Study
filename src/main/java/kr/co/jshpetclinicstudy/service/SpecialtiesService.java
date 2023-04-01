package kr.co.jshpetclinicstudy.service;

import kr.co.jshpetclinicstudy.persistence.entity.Specialties;
import kr.co.jshpetclinicstudy.persistence.entity.Vets;
import kr.co.jshpetclinicstudy.persistence.repository.SpecialtiesRepository;
import kr.co.jshpetclinicstudy.service.model.dtos.SpecialtiesRequestDto;
import kr.co.jshpetclinicstudy.service.model.dtos.SpecialtiesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialtiesService {

    private final SpecialtiesRepository specialtiesRepository;

    public void createSpecialty(SpecialtiesRequestDto.CREATE create) {
        final Specialties specialties = Specialties.dtoToEntity(create);
        specialtiesRepository.save(specialties);
    }

    public List<SpecialtiesResponseDto.READ> getSpecialtyList() {
        return specialtiesRepository.findAll().stream().map(Specialties::entityToDto).collect(Collectors.toList());
    }

    public SpecialtiesResponseDto.DETAIL_READ getSpecialty(Long id) {
        return Specialties.entityToDetailDto(specialtiesRepository.findById(id).get());
    }

    public void updateSpecialty(SpecialtiesRequestDto.UPDATE update) {
        final Optional<Specialties> specialties = specialtiesRepository.findById(update.getSpecialtyId());
        isSpecialties(specialties);

        specialties.get().changeSpecialtyName(update.getName());

        specialtiesRepository.save(specialties.get());
    }

    public void deleteSpecialty(Long id) {
        final Optional<Specialties> specialties = specialtiesRepository.findById(id);
        isSpecialties(specialties);
        specialtiesRepository.deleteById(id);
    }

    private void isSpecialties(Optional<Specialties> specialties) {
        if (specialties.isEmpty()) {
            throw new RuntimeException("This specialties is Not Exist");
        }
    }
}
