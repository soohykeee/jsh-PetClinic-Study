package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.jshpetclinicstudy.service.model.request.VisitsRequestDto;
import kr.co.jshpetclinicstudy.service.model.response.VisitsResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "visit_id", length = 4))
@Getter
@NoArgsConstructor
@Table(name="tbl_visits")
public class Visits extends BaseEntity{

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pets pets;

    @Builder
    public Visits(LocalDate visitDate,
                  String description,
                  Pets pets) {
        this.visitDate = visitDate;
        this.description = description;
        this.pets = pets;
    }

    public void changeVisitDate(LocalDate changeVisitDate) {
        this.visitDate = changeVisitDate;
    }

    public void changeVisitDescription(String changeDescription) {
        this.description = changeDescription;
    }

    public void changeVisitPet(Pets changeVisitPet) {
        this.pets = changeVisitPet;
    }
}
