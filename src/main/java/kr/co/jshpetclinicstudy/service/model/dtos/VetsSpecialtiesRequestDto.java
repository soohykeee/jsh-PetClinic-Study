package kr.co.jshpetclinicstudy.service.model.dtos;

import kr.co.jshpetclinicstudy.persistence.entity.Specialties;
import kr.co.jshpetclinicstudy.persistence.entity.Vets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class VetsSpecialtiesRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CREATE {
        private Specialties specialties;

        private Vets vets;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UPDATE {

        private Long vetSpecialtyId;

        private Specialties specialties;

        private Vets vets;
    }
}
