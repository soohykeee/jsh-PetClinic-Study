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
public class VetSpecialty extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id")
    private Specialty specialties;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id")
    private Vet vets;

    @Builder
    public VetSpecialty(Specialty specialties,
                        Vet vets) {
        this.specialties = specialties;
        this.vets = vets;
    }

}
