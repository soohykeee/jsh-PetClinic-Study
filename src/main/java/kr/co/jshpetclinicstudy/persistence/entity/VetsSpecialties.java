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

}
