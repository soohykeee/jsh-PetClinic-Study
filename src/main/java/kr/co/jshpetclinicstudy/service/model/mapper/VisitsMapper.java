package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.persistence.entity.Visits;
import kr.co.jshpetclinicstudy.service.model.request.VisitsRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VisitsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VisitsMapper {

    Visits toEntity(VisitsRequestDto.CREATE create);

    @Mapping(target = "visitId", source = "id")
    @Mapping(target = "petName", source = "pets.name")
    @Mapping(target = "petType", source = "pets.types")
    @Mapping(target = "ownerFirstName", source = "pets.owners.firstName")
    VisitsResponseDto.READ toReadDto(Visits visits);
}
