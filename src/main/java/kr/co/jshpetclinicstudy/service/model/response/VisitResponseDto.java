package kr.co.jshpetclinicstudy.service.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class VisitResponseDto {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class READ {

        private Long visitId;

        private LocalDate visitDate;

        private String description;

        private String petName;

        private String petType;

        private String petBirthDate;

        private String ownerFirstName;

        private String ownerTelephone;

        private String vetFistName;
    }

}
