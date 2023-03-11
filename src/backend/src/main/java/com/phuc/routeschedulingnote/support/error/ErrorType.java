package com.phuc.routeschedulingnote.support.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorType {
    private final HttpStatus status;
    private final ExitCode code;
    private final String message;

    public ErrorType() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = ExitCode.E500;
        this.message = "An unexpected error has occurred";
    }

    public ErrorType(HttpStatus status, ExitCode code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
