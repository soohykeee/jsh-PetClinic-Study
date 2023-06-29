package kr.co.jshpetclinicstudy.infra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResponseStatus {

    // Success Status
    SUCCESS_OK("요청이 성공적으로 처리되었습니다.", HttpStatus.OK),
    SUCCESS_CREATE("요청이 성공적으로 처리되어 새로운 리소스가 생성되었습니다.", HttpStatus.CREATED),
    SUCCESS_ACCEPTED("요청이 성공적으로 처리되었지만, 결과가 아직 완료되지 않았습니다.", HttpStatus.ACCEPTED),
    SUCCESS_NO_CONTENT("요청이 성공적으로 처리되었지만, 응답 데이터가 없습니다.", HttpStatus.NO_CONTENT),

    // Failed Status
    FAIL_BAD_REQUEST("클라이언트의 요청이 잘못되었습니다.", HttpStatus.BAD_REQUEST),
    FAIL_UNAUTHORIZED("클라이언트가 인증되지 않았습니다.", HttpStatus.UNAUTHORIZED),
    FAIL_FORBIDDEN("클라이언트가 요청한 리소스에 접근할 권한이 없습니다.", HttpStatus.FORBIDDEN),
    FAIL_NOT_FOUND("클라이언트가 요청한 리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    FAIL_METHOD_NOT_ALLOWED("클라이언트가 요청한 HTTP 메소드가 허용되지 않았습니다.", HttpStatus.METHOD_NOT_ALLOWED),

    // Valid Failed Status
    FAIL_INVALID_PARAMETER("파라미터 값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),

    // Login Failed Status
    FAIL_LOGIN_NOT_SUCCESS("로그인이 되지 않았습니다. 재시도 해주세요.", HttpStatus.BAD_REQUEST),

    // Member Failed Status
    FAIL_MEMBER_NOT_FOUND("클라이언트가 요청한 소유자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    FAIL_MEMBER_ROLE_NOT_FOUND("클라이언트가 요청한 소유자의 권한을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    FAIL_MEMBER_IDENTITY_DUPLICATED("클라이언트가 요청한 아이디가 중복되었습니다.", HttpStatus.BAD_REQUEST),
    FAIL_MEMBER_PASSWORD_NOT_MATCHED("클라이언트가 입력한 비밀번호가 소유자의 비밀번호와 일치하지 않습니다.", HttpStatus.BAD_REQUEST),

    // Token Failed Status
    FAIL_TOKEN_NOT_FOUND("클라이언트가 요청한 토큰 정보를 찾을 수 업습니다.", HttpStatus.NOT_FOUND),
    FAIL_REFRESHTOKEN_NOT_FOUND("클라이언트가 요청한 RefreshToken을 찾을 수 없습니다.(만료)", HttpStatus.NOT_FOUND),

    // Owner Failed Status
    FAIL_OWNER_NOT_FOUND("클라이언트가 요청한 소유자를 찾을 수 업습니다.", HttpStatus.NOT_FOUND),
    FAIL_TELEPHONE_DUPLICATED("클라이언트의 전화번호가 중복되었습니다.", HttpStatus.BAD_REQUEST),

    // Pet Failed Status
    FAIL_PET_NOT_FOUND("클라이언트가 요청한 반려동물을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    FAIL_TYPE_NOT_FOUND("클라이언트가 요청한 반려동물의 종류를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // Vet Failed Status
    FAIL_VET_NOT_FOUND("클라이언트가 요청한 수의사를 찾을 수 업습니다.", HttpStatus.NOT_FOUND),

    // Specialty Failed Status
    FAIL_SPECIALTY_NOT_FOUND("클라이언트가 요청한 전문학위를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // Visit Failed Status
    FAIL_VISIT_NOT_FOUND("클라이언트가 요청한 방문기록을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private String message;

    private HttpStatus statusCode;
}
