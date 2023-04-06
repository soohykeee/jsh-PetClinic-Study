package kr.co.jshpetclinicstudy.service.model;

import kr.co.jshpetclinicstudy.persistence.entity.Specialties;
import kr.co.jshpetclinicstudy.persistence.entity.Vets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class VetsSpecialtiesResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    // 해당 학위를 갖고있는 수의사들 목록
    public static class READ_VETS {

        private Long vetSpecialtyId;

        private Specialties specialties;

        private List<Vets> vetsList;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    // 해당 수의사가 갖고있는 학위들 목록
    public static class READ_SPEC {

        private Long vetSpecialtyId;

        private Vets vets;

        private List<Specialties> specialtiesList;
    }
}