package com.phuc.routeschedulingnote.exception;

public class GeocodeNotFoundException extends RuntimeException{

    public GeocodeNotFoundException(String searchText) {
        super("Cannot found search text = " + searchText);
    }

}
