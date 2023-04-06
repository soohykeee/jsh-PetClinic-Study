package kr.co.jshpetclinicstudy.service.model;

import kr.co.jshpetclinicstudy.persistence.entity.Owners;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class PetsRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CREATE {

        private String name;

        private LocalDate birthDate;

        private Owners owners;

        private String type;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UPDATE {

        private Long petId;

        private String name;

        private LocalDate birthDate;

        private Owners owners;

        private String type;
    }
}
