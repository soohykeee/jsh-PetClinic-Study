package kr.co.jshpetclinicstudy.service.model.request;

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

        private String name;

        private LocalDate birthDate;

        private Long ownerId;

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

    }
}
