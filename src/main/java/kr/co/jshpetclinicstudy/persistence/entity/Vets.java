package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.jshpetclinicstudy.service.model.request.VetsRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VetsResponseDto;
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
    private List<VetsSpecialties> specialties = new ArrayList<>();

    @Builder
    public Vets(String firstName,
                String lastName,
                List<VetsSpecialties> specialties) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialties = specialties;
    }

    public void changeVetFirstName(String changeFirstName) {
        this.firstName = changeFirstName;
    }

    public void changeVetLastName(String changeLastName) {
        this.lastName = changeLastName;
    }

    public void changeVetSpecialties(List<VetsSpecialties> changeSpecialties) {
        this.specialties = changeSpecialties;
    }
}
