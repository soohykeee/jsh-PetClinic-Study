package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "pet_id", length = 4))
@NoArgsConstructor
@Getter
@Table(name="tbl_pets")
public class Pet extends BaseEntity{

    @Column(name = "name", length = 30)
    @NotNull
    private String name;

    @Column(name = "birth_date")
    @NotNull
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Enumerated(EnumType.STRING)
    @Column(name="pet_type")
    private Type type;

    @Builder
    public Pet(String name,
               LocalDate birthDate,
               Owner owner,
               Type type) {
        this.name = name;
        this.birthDate = birthDate;
        this.owner = owner;
        this.type = type;
    }

    public void changePetName(String changeName) {
        this.name = changeName;
    }

    public void changePetBirtDate(LocalDate changeBirthDate) {
        this.birthDate = changeBirthDate;
    }

    public void changePetType(Type changeType) {
        this.type = changeType;
    }

}
