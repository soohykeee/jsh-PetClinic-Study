package kr.co.jshpetclinicstudy.service.model.response;

import kr.co.jshpetclinicstudy.persistence.entity.VetSpecialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class VetResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class READ {

        private Long vetId;

        private String firstName;

        private String lastName;

        private List<VetSpecialty> specialties;
    }

}
