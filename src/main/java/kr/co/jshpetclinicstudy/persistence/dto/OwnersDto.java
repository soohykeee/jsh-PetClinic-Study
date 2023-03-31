package kr.co.jshpetclinicstudy.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnersDto {

    private Long ownerId;

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String telephone;

}
