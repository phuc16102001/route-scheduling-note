package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.dto.schedule.ScheduleGetDto;
import com.phuc.routeschedulingnote.dto.schedule.ScheduleListDto;
import com.phuc.routeschedulingnote.dto.schedule.SchedulePostDto;
import com.phuc.routeschedulingnote.model.Schedule;
import com.phuc.routeschedulingnote.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ScheduleController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ScheduleGetDto addSchedule(@RequestBody SchedulePostDto scheduleDto) {
        Schedule schedule = modelMapper.map(scheduleDto, Schedule.class);
        Schedule result = scheduleService.addSchedule(schedule);
        return modelMapper.map(result, ScheduleGetDto.class);
    }

    @GetMapping("/schedules")
    public List<ScheduleListDto> getListSchedule() {
        List<Schedule> schedules = scheduleService.getListSchedule();
        return schedules.stream().map(
            element -> modelMapper.map(element, ScheduleListDto.class)
        ).collect(Collectors.toList());
    }


    @GetMapping("/schedules/{id}")
    public ScheduleGetDto getOneSchedule(@PathVariable Integer id) {
        Schedule schedules = scheduleService.getOneSchedule(id);
        return modelMapper.map(schedules, ScheduleGetDto.class);
    }

}
