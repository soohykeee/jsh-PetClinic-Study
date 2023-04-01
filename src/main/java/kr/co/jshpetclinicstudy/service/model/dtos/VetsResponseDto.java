package kr.co.jshpetclinicstudy.service.model.dtos;

import kr.co.jshpetclinicstudy.persistence.entity.VetsSpecialties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class VetsResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class READ {

        private Long vetId;

        private String firstName;

        private String lastName;

        private List<VetsSpecialties> specialties;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DETAIL_READ {

        private Long vetId;

        private String firstName;

        private String lastName;

        private List<VetsSpecialties> specialties;
    }

}
