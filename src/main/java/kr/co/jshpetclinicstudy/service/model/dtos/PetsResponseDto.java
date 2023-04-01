package kr.co.jshpetclinicstudy.service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class PetsResponseDto {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class READ {
        private Long petId;

        private String name;

        private LocalDate birthDate;

        private String ownerFirstName;

        private String ownerTelephone;

        private String type;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class DETAIL_READ {
        private Long petId;

        private String name;

        private LocalDate birthDate;

        private String ownerFirstName;

        private String ownerTelephone;

        private String type;
    }
}
