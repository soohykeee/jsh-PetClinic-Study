package kr.co.jshpetclinicstudy.service.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class VetRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CREATE {

        @NotBlank(message = "Please Enter FirstName")
        private String firstName;

        @NotBlank(message = "Please Enter LastName")
        private String lastName;

        @NotBlank(message = "Please Enter Specialties")
        private List<String> specialtiesName;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UPDATE {

        private Long vetId;

        private String firstName;

        private String lastName;

        private List<String> specialtiesName;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CONDITION {

        private List<Long> vetIds;

        private String firstName;

        private String specialtyName;

    }
}
