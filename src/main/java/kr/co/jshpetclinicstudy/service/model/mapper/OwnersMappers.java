package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.persistence.entity.Owners;
import kr.co.jshpetclinicstudy.service.model.request.OwnersRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.OwnersResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OwnersMappers {

    OwnersMappers INSTANCE = Mappers.getMapper(OwnersMappers.class);

    Owners toOwnersEntity(OwnersRequestDto.CREATE create);

    @Mapping(target = "ownerId", source = "id")
    OwnersResponseDto.READ toReadDto(Owners owners);

}
