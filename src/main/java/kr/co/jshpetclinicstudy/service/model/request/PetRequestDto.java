package kr.co.jshpetclinicstudy.service.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class PetRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CREATE {

        @NotBlank(message = "Please Enter Name")
        private String name;

        @NotBlank(message = "Please Enter BirthDate")
        private LocalDate birthDate;

        @NotBlank(message = "Please Enter OwnerId")
        private Long ownerId;

        @NotBlank(message = "Please Enter PetType")
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

        private Long ownerId;

        private String type;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CONDITION {

        private List<Long> petIds;

        private String name;

        private LocalDate birthDate;

        private String type;

        private String ownerFirstName;

    }
}
