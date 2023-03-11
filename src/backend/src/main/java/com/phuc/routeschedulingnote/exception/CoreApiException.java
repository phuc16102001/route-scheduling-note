package com.phuc.routeschedulingnote.exception;

import com.phuc.routeschedulingnote.support.error.ErrorType;
import lombok.Getter;

@Getter
public class CoreApiException extends RuntimeException {
    private final ErrorType errorType;

    public CoreApiException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
