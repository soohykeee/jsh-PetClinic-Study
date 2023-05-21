package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.co.jshpetclinicstudy.service.model.request.VetRequestDto;
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
public class Vet extends BaseEntity{

    @Column(name = "first_name", length = 30)
    @NotNull
    private String firstName;

    @Column(name = "last_name", length = 30)
    @NotNull
    private String lastName;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.ALL},
            mappedBy = "vet"
    )
    private List<VetSpecialty> vetSpecialties = new ArrayList<>();

    @Builder
    public Vet(String firstName,
               String lastName,
               List<VetSpecialty> vetSpecialties) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.vetSpecialties = vetSpecialties;
    }

    public void changeVetSpecialties(List<VetSpecialty> changeSpecialties) {
        this.vetSpecialties = changeSpecialties;
    }
}
