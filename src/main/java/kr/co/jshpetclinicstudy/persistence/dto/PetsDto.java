package kr.co.jshpetclinicstudy.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetsDto {

    private Long petId;

    private String name;

    private LocalDate birthDate;

    private String ownerFirstName;

    private String ownerTelephone;

    private String type;
}
