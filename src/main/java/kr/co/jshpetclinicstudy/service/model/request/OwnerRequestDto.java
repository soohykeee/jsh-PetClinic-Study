package kr.co.jshpetclinicstudy.service.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class OwnerRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CREATE {

        @NotBlank(message = "Please Enter FirstName")
        private String firstName;

        @NotBlank(message = "Please Enter LastName")
        private String lastName;

        @NotBlank(message = "Please Enter Address")
        private String address;

        @NotBlank(message = "Please Enter City")
        private String city;

        @NotBlank(message = "Please Enter Telephone")
        private String telephone;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UPDATE {
        private Long ownerId;

        private String firstName;

        private String lastName;

        private String address;

        private String city;

        private String telephone;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CONDITION {

        private List<Long> ownerIds;

        private String firstName;

        private String city;

        private String telephone;

    }
}
