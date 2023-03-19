package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Visits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visits_id", length = 4)
    private Long visitsId;

    @Column(name = "visit_date")
    private LocalDateTime visitDate;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pets pets;

    public Visits(LocalDateTime visitDate,
                  String description,
                  Pets pets) {
        this.visitDate = visitDate;
        this.description = description;
        this.pets = pets;
    }
}
