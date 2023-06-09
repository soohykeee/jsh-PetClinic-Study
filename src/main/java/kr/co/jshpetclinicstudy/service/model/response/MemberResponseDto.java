package kr.co.jshpetclinicstudy.service.model.response;

import kr.co.jshpetclinicstudy.service.model.request.TokenDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponseDto {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class READ {

        private Long memberId;

        private String identity;

        private String name;

        private String role;

        private TokenDto token;
    }
}
