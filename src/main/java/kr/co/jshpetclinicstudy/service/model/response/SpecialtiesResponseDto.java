package kr.co.jshpetclinicstudy.service.model.response;

import kr.co.jshpetclinicstudy.persistence.entity.VetsSpecialties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class SpecialtiesResponseDto {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class READ {
        private Long specialtyId;

        private String name;

        private List<VetsSpecialties> vets;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class DETAIL_READ {
        private Long specialtyId;

        private String name;

        private List<VetsSpecialties> vets;
    }
}