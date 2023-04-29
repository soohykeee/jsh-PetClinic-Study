package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import kr.co.jshpetclinicstudy.service.model.request.PetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.PetResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PetMapper {

    Pet toEntity(PetRequestDto.CREATE create, Owner owner);

    @Mapping(target="petId", source = "id")
    @Mapping(target = "ownerFirstName", source = "owner.firstName")
    @Mapping(target = "ownerTelephone", source = "owner.telephone")
    PetResponseDto.READ toReadDto(Pet pet);

}
