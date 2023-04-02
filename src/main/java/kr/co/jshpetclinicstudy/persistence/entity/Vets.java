package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "vet_id", length = 4))
@Getter
@NoArgsConstructor
@Table(name="tbl_vets")
public class Vets extends BaseEntity{

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @OneToMany(mappedBy = "vets")
    private List<VetsSpecialties> vets = new ArrayList<>();

    public Vets(String firstName,
                String lastName,
                List<VetsSpecialties> vets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.vets = vets;
    }
}
