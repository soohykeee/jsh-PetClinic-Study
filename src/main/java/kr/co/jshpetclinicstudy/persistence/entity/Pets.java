package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pets_id", length = 4)
    private Long petsId;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "birth_date")
    private LocalDateTime birth_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "owner_id")
    private Owners owners;

    @Enumerated(EnumType.STRING)
    @Column(name="type_id")
    private Types types;

    @Builder
    public Pets(String name,
                LocalDateTime birth_date,
                Owners owners,
                Types types) {
        this.name = name;
        this.birth_date = birth_date;
        this.owners = owners;
        this.types = types;
    }
}
