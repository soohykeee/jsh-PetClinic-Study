package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.persistence.entity.Pets;
import kr.co.jshpetclinicstudy.service.model.request.PetsRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.PetsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PetsMappers {

    Pets toPetsEntity(PetsRequestDto.CREATE create);

    @Mapping(target="petId", source = "id")
    @Mapping(target = "ownerFirstName", source = "owners.firstName")
    @Mapping(target = "ownerTelephone", source = "owners.telephone")
    PetsResponseDto.READ toReadDto(Pets pets);

}
