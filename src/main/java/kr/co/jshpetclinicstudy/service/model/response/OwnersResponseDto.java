package kr.co.jshpetclinicstudy.service.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OwnersResponseDto {

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

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class DETAIL_READ {
        private Long ownerId;

        private String firstName;

        private String lastName;

        private String address;

        private String city;

        private String telephone;
    }

}
