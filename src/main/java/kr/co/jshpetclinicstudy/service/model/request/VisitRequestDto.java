package kr.co.jshpetclinicstudy.service.model.request;

import kr.co.jshpetclinicstudy.persistence.entity.Pet;
import kr.co.jshpetclinicstudy.persistence.entity.Vet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class VisitRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CREATE {

        private LocalDate visitDate;

        private String description;

//        private Pet pet;
//
//        private Vet vet;

        private Long petId;

        private Long vetId;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UPDATE {

        private Long visitId;

        private LocalDate visitDate;

        private String description;

//        private Pet pet;
//
//        private Vet vet;

        private Long petId;

        private Long vetId;
    }


}
