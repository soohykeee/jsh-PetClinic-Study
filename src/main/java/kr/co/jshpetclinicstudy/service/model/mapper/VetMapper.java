package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import kr.co.jshpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.jshpetclinicstudy.service.model.request.VetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VetMapper {

    Vet toEntity(VetRequestDto.CREATE create, List<VetSpecialty> vetSpecialties);

    VetResponseDto.READ toReadDto(Vet vet, List<String> specialtiesName);
}
