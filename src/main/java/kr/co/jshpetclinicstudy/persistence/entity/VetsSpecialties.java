package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "vet_specialty_id", length = 4))
@Getter
@NoArgsConstructor
@Table(name = "tbl_vets_specialties")
public class VetsSpecialties extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id")
    private Specialties specialties;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id")
    private Vets vets;

    @Builder
    public VetsSpecialties(Specialties specialties,
                           Vets vets) {
        this.specialties = specialties;
        this.vets = vets;
    }

   /* public static VetsSpecialties dtoToEntity(VetsSpecialtiesRequestDto.CREATE create) {
        return VetsSpecialties.builder()
                .vets(create.getVets())
                .specialties(create.getSpecialties())
                .build();
    }

    public static VetsSpecialtiesResponseDto.READ_VETS entityToVetsDto(VetsSpecialties vetsSpecialties) {
        return VetsSpecialtiesResponseDto.READ_VETS.builder()
                .vetSpecialtyId(vetsSpecialties.getId())
                .specialties(vetsSpecialties.getSpecialties())
                .vetsList(vetsSpecialties.getVets())
                .build();
    }

    public static VetsSpecialtiesResponseDto.READ_SPEC entityToSpecDto(VetsSpecialties vetsSpecialties) {

    }*/
}
