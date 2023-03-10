package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.model.Schedule;

import java.util.List;

public interface ScheduleService {

    Schedule addSchedule(Schedule schedule);
    List<Schedule> getListSchedule();

    Schedule getById(Integer id);
    void deleteById(Integer Id);

}
