package kr.co.jshpetclinicstudy.persistence.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "specialty_id", length = 4))
@Getter
@NoArgsConstructor
@Table(name = "tbl_specialties")
public class Specialty extends BaseEntity {

    @Column(name = "specialty_name", length = 80)
    private String specialtyName;

    @Builder
    public Specialty(String specialtyName) {
        this.specialtyName = specialtyName;
    }


}
