package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "vets_id", length = 4))
@Getter
@NoArgsConstructor
public class Vets extends BaseEntity{

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name="vets_specialties")
    private Specialties specialties;

    public Vets(String firstName,
                String lastName,
                Specialties specialties) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialties = specialties;
    }

}
