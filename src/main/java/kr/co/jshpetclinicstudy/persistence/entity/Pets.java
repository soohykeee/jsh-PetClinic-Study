package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "pets_id", length = 4))
@NoArgsConstructor
@Getter
public class Pets extends BaseEntity{

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "birth_date")
    private LocalDateTime birth_date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Owners owners;

    @Enumerated(EnumType.STRING)
    @Column(name="pets_types")
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
