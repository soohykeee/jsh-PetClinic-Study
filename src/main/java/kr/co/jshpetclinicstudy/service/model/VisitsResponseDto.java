package kr.co.jshpetclinicstudy.service.model;

import kr.co.jshpetclinicstudy.persistence.entity.Pets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class VisitsResponseDto {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class READ {

        private Long visitId;

        private LocalDate visitDate;

        private String description;

        private Pets pets;
    }


    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class DETAIL_READ {

        private Long visitId;

        private LocalDate visitDate;

        private String description;

        private Pets pets;

        private String petName;

        private String petType;

        private String ownerFirstName;
    }
}
