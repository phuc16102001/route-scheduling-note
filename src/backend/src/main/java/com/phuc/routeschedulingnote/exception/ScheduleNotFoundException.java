package com.phuc.routeschedulingnote.exception;

public class ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException(Integer id) {
        super("Cannot find schedule with id = "+id);
    }

}
