package kr.co.jshpetclinicstudy.service.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OwnerResponseDto {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class READ {
        private Long ownerId;

        private String firstName;

        private String lastName;

        private String address;

        private String city;

        private String telephone;
    }

}
