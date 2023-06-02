package kr.co.jshpetclinicstudy.persistence.entity.enums;

import kr.co.jshpetclinicstudy.infra.exception.NotFoundException;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Type {

    DOG("강아지"),

    CAT("고양이"),

    BIRD("새"),

    REPTILE("파충류"),

    FISH("물고기"),

    OTHER("기타");

    String petType;

    public static Type of(String petType) {

        return Arrays.stream(Type.values())
                .filter(type -> type.toString().equalsIgnoreCase(petType))
                .findAny().orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_TYPE_NOT_FOUND));
    }
}
