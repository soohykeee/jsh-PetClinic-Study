package kr.co.jshpetclinicstudy.persistence.entity.enums;

import kr.co.jshpetclinicstudy.infra.exception.NotFoundException;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Role {

    ROLE_USER("일반"),

    ROLE_ADMIN("관리자");

    String userRole;

    public static Role of(String userRole) {

        return Arrays.stream(Role.values())
                .filter(role -> role.toString().equalsIgnoreCase(userRole))
                .findAny().orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_ROLE_NOT_FOUND));
    }

}
