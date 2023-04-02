package kr.co.jshpetclinicstudy.service.model;

import kr.co.jshpetclinicstudy.persistence.entity.Pets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class VisitsRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CREATE {

        private LocalDate visitDate;

        private String description;

        private Pets pets;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UPDATE {

        private Long visitId;

        private LocalDate visitDate;

        private String description;

        private Pets pets;

    }


}
