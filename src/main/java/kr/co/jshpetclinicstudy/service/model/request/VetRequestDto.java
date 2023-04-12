package kr.co.jshpetclinicstudy.service.model.request;

import kr.co.jshpetclinicstudy.persistence.entity.VetSpecialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class VetRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CREATE {

        private String firstName;

        private String lastName;

        private List<VetSpecialty> specialties;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UPDATE {

        private Long vetId;

        private String firstName;

        private String lastName;

        private List<VetSpecialty> specialties;

    }
}
