package kr.co.jshpetclinicstudy.service.model.request;

import kr.co.jshpetclinicstudy.persistence.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class PetRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CREATE {

        private String name;

        private LocalDate birthDate;

//        private Owner owner;

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

//        private Owner owner;

        private Long ownerId;

        private String type;
    }
}
