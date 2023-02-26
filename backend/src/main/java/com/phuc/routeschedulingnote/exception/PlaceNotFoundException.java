package com.phuc.routeschedulingnote.exception;

public class PlaceNotFoundException extends RuntimeException{

    public PlaceNotFoundException(Integer id) {
        super("Cannot find place with id = " + id);
    }

}
