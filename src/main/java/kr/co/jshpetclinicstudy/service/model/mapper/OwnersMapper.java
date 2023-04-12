package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.persistence.entity.Owners;
import kr.co.jshpetclinicstudy.service.model.request.OwnersRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.OwnersResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OwnersMapper {

    Owners toEntity(OwnersRequestDto.CREATE create);

    @Mapping(target = "ownerId", source = "id")
    OwnersResponseDto.READ toReadDto(Owners owners);

}
