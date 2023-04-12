package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import kr.co.jshpetclinicstudy.service.model.request.OwnerRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.OwnerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    Owner toEntity(OwnerRequestDto.CREATE create);

    @Mapping(target = "ownerId", source = "id")
    OwnerResponseDto.READ toReadDto(Owner owner);

}
