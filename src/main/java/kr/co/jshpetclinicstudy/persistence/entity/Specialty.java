package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
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
public class Specialty extends BaseEntity {

    @Column(name = "name", length = 80)
    private String name;

    @OneToMany(mappedBy = "specialties")
    private List<VetSpecialty> vets = new ArrayList<>();

    @Builder
    public Specialty(String name,
                     List<VetSpecialty> vets) {
        this.name = name;
        this.vets = vets;
    }


}
