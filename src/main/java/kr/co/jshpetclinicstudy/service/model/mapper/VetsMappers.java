package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.persistence.entity.Vets;
import kr.co.jshpetclinicstudy.service.model.request.VetsRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VetsMappers {

    Vets toVetsEntity(VetsRequestDto.CREATE create);

    @Mapping(target = "vetId", source = "id")
    VetsResponseDto.READ toReadDto(Vets vets);
}
