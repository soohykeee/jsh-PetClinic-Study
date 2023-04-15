package kr.co.jshpetclinicstudy.service.model.mapper;

import kr.co.jshpetclinicstudy.persistence.entity.Specialty;
import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import kr.co.jshpetclinicstudy.persistence.entity.VetSpecialty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VetSpecialtyMapper {

    VetSpecialty toEntity(Specialty specialty, Vet vet);

}
