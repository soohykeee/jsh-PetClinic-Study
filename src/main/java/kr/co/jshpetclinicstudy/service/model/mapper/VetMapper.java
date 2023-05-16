package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import kr.co.jshpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.jshpetclinicstudy.service.model.request.VetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VetMapper {

    Vet toEntity(VetRequestDto.CREATE create, List<VetSpecialty> vetSpecialties);

    @Mapping(target = "specialtiesName", source = "specialtiesName")
    @Mapping(target = "firstName", source = "vet.firstName")
    @Mapping(target = "lastName", source = "vet.lastName")
    VetResponseDto.READ toReadDto(Vet vet, List<String> specialtiesName);

}
