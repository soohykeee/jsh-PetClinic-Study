package kr.co.jshpetclinicstudy.service.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class PetResponseDto {

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

}
