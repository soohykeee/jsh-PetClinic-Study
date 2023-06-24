package kr.co.jshpetclinicstudy.infra.exception;

import kr.co.jshpetclinicstudy.infra.model.ResponseStatus;

public class WrongPasswordException extends BusinessLogicException {

    public WrongPasswordException(ResponseStatus responseStatus) {
        super(responseStatus);
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
