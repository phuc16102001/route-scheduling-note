package com.phuc.routeschedulingnote.support.error;

import lombok.Getter;

@Getter
public class ErrorMessage {

    private final String code;
    private final String message;

    public ErrorMessage(ErrorType errorType) {
        this.code = errorType.getCode().name();
        this.message = errorType.getMessage();
    }

}
