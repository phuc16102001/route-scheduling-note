package com.phuc.routeschedulingnote.support.error;

import lombok.Getter;

@Getter
public class CoreApiException extends RuntimeException {
    private final ErrorType errorType;

    public CoreApiException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
