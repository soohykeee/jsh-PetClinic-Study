package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import kr.co.jshpetclinicstudy.service.model.request.VetRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VetMapper {

    Vet toEntity(VetRequestDto.CREATE create);

    @Mapping(target = "vetId", source = "id")
    VetResponseDto.READ toReadDto(Vet vet);
}
