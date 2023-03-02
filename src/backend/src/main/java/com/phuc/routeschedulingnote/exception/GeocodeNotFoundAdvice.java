package com.phuc.routeschedulingnote.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GeocodeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(GeocodeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String placeNotFoundHandler(GeocodeNotFoundException ex) {
        return ex.getMessage();
    }

}
