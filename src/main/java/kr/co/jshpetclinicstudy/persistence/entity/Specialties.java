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
public class Specialties extends BaseEntity {

    @Column(name = "name", length = 80)
    private String name;

    @OneToMany(mappedBy = "specialties")
    private List<VetsSpecialties> specialties = new ArrayList<>();

    public Specialties(String name,
                       List<VetsSpecialties> specialties) {
        this.name = name;
        this.specialties = specialties;
    }
}
