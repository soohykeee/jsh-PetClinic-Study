package kr.co.jshpetclinicstudy.infra.exception;

import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;

public class BusinessLogicException extends RuntimeException {

    private ResponseStatus responseStatus;

    public BusinessLogicException(ResponseStatus responseStatus) {
        super(responseStatus.getMessage());
        this.responseStatus = responseStatus;
    }

    public BusinessLogicException(String message) {
        super(message);
    }
}
