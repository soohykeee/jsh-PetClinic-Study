package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "visit_id", length = 4))
@Getter
@NoArgsConstructor
@Table(name="tbl_visits")
public class Visit extends BaseEntity{

    @Column(name = "visit_date")
    @NotNull
    private LocalDate visitDate;

    @Column(name = "description")
    @NotNull
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @Builder
    public Visit(LocalDate visitDate,
                 String description,
                 Pet pet,
                 Vet vet) {
        this.visitDate = visitDate;
        this.description = description;
        this.pet = pet;
        this.vet = vet;
    }

    public void changeVisitDate(LocalDate changeVisitDate) {
        this.visitDate = changeVisitDate;
    }

    public void changeVisitDescription(String changeDescription) {
        this.description = changeDescription;
    }

    public void changeVisitPet(Pet changeVisitPet) {
        this.pet = changeVisitPet;
    }
}
