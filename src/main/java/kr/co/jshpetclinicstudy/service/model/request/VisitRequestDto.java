package kr.co.jshpetclinicstudy.service.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class VisitRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CREATE {

        @NotBlank(message = "Please Enter VisitDate")
        private LocalDate visitDate;

        @NotBlank(message = "Please Enter Description")
        private String description;

        @NotBlank(message = "Please Enter PetId")
        private Long petId;

        @NotBlank(message = "Please Enter VetId")
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

        private Long petId;

        private Long vetId;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CONDITION {

        private List<Long> visitIds;

        private LocalDate startDate;

        private LocalDate endDate;

        private String ownerFirstName;

        private String petName;

        private String vetFirstName;

    }

}
