package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.persistence.entity.Vets;
import kr.co.jshpetclinicstudy.service.model.request.VetsRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VetsMapper {

    Vets toEntity(VetsRequestDto.CREATE create);

    @Mapping(target = "vetId", source = "id")
    VetsResponseDto.READ toReadDto(Vets vets);
}
