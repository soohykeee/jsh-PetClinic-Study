package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.jshpetclinicstudy.service.model.dtos.SpecialtiesRequestDto;
import kr.co.jshpetclinicstudy.service.model.dtos.SpecialtiesResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "specialty_id", length = 4))
@Getter
@NoArgsConstructor
@Table(name = "tbl_specialties")
public class Specialties extends BaseEntity {

    @Column(name = "name", length = 80)
    private String name;

    @OneToMany(mappedBy = "specialties")
    private List<VetsSpecialties> vets = new ArrayList<>();

    @Builder
    public Specialties(String name,
                       List<VetsSpecialties> vets) {
        this.name = name;
        this.vets = vets;
    }

    public static Specialties dtoToEntity(SpecialtiesRequestDto.CREATE create) {
        return Specialties.builder()
                .name(create.getName())
                .build();
    }

    public static SpecialtiesResponseDto.READ entityToDto(Specialties specialties) {
        return SpecialtiesResponseDto.READ.builder()
                .specialtyId(specialties.getId())
                .name(specialties.getName())
                .vets(specialties.getVets())
                .build();
    }

    public static SpecialtiesResponseDto.DETAIL_READ entityToDetailDto(Specialties specialties) {
        return SpecialtiesResponseDto.DETAIL_READ.builder()
                .specialtyId(specialties.getId())
                .name(specialties.getName())
                .vets(specialties.getVets())
                .build();
    }

    public void changeSpecialtyName(String changeName) {
        this.name = changeName;
    }
}
