package kr.co.jshpetclinicstudy.infra.handler;

import kr.co.jshpetclinicstudy.infra.exception.DuplicatedException;
import kr.co.jshpetclinicstudy.infra.exception.NotFoundException;
import kr.co.jshpetclinicstudy.infra.model.ResponseErrorFormat;
import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//전역적으로 예외 처리를 담당하는 클래스
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicatedException.class)
    protected ResponseEntity<ResponseErrorFormat> handleDuplicatedException(DuplicatedException e) {
        log.warn("-------HandleDuplicatedException-------", e);

        ResponseErrorFormat responseErrorFormat = ResponseErrorFormat.builder()
                .message(e.getMessage())
                .statusCode(ResponseStatus.FAIL_BAD_REQUEST.getStatusCode())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseErrorFormat);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ResponseErrorFormat> handleNotFoundException(NotFoundException e) {
        log.warn("-------HandleNotFoundException-------", e);

        ResponseErrorFormat responseErrorFormat = ResponseErrorFormat.builder()
                .message(e.getMessage())
                .statusCode(ResponseStatus.FAIL_NOT_FOUND.getStatusCode())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseErrorFormat);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ResponseErrorFormat> handleRuntimeException(RuntimeException e) {
        log.warn("-------HandleRuntimeException-------", e);

        ResponseErrorFormat responseErrorFormat = ResponseErrorFormat.builder()
                .message(e.getMessage())
                .statusCode(ResponseStatus.FAIL_BAD_REQUEST.getStatusCode())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseErrorFormat);
    }
}
