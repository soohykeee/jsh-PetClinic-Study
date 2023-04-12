package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
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
    private LocalDate visitDate;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Builder
    public Visit(LocalDate visitDate,
                 String description,
                 Pet pet) {
        this.visitDate = visitDate;
        this.description = description;
        this.pet = pet;
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
