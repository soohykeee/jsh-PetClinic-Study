package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import kr.co.jshpetclinicstudy.persistence.entity.Visit;
import kr.co.jshpetclinicstudy.service.model.request.VisitRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VisitResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VisitMapper {

    Visit toEntity(VisitRequestDto.CREATE create, Pet pet, Vet vet);

    @Mapping(target = "visitId", source = "id")
    @Mapping(target = "petName", source = "pet.name")
    @Mapping(target = "petType", source = "pet.type")
    @Mapping(target = "petBirthDate", source = "pet.birthDate")
    @Mapping(target = "ownerFirstName", source = "pet.owner.firstName")
    @Mapping(target = "ownerTelephone", source = "pet.owner.telephone")
    @Mapping(target = "vetFistName", source="vet.firstName")
    VisitResponseDto.READ toReadDto(Visit visit);
}
