package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "pet_id", length = 4))
@NoArgsConstructor
@Getter
@Table(name="tbl_pets")
public class Pets extends BaseEntity{

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owners_id")
    private Owners owners;

    @Enumerated(EnumType.STRING)
    @Column(name="pets_types")
    private Types types;

    @Builder
    public Pets(String name,
                LocalDate birthDate,
                Owners owners,
                Types types) {
        this.name = name;
        this.birthDate = birthDate;
        this.owners = owners;
        this.types = types;
    }
}
