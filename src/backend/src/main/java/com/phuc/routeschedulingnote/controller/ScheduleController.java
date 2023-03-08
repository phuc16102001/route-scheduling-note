package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.dto.ScheduleDto;
import com.phuc.routeschedulingnote.model.Schedule;
import com.phuc.routeschedulingnote.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ScheduleDto addSchedule(@RequestBody ScheduleDto scheduleDto) {
        Schedule schedule = modelMapper.map(scheduleDto, Schedule.class);
        Schedule result = scheduleService.addSchedule(schedule);
        return modelMapper.map(result, ScheduleDto.class);
    }

}
