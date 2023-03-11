package com.phuc.routeschedulingnote.service.impl;

import com.phuc.routeschedulingnote.model.Coordinates;
import com.phuc.routeschedulingnote.model.Schedule;
import com.phuc.routeschedulingnote.model.Stop;
import com.phuc.routeschedulingnote.repository.PlaceNoteRepository;
import com.phuc.routeschedulingnote.repository.ScheduleRepository;
import com.phuc.routeschedulingnote.repository.StopRepository;
import com.phuc.routeschedulingnote.service.MapService;
import com.phuc.routeschedulingnote.service.ScheduleService;
import com.phuc.routeschedulingnote.support.error.CoreApiException;
import com.phuc.routeschedulingnote.support.error.ErrorType;
import com.phuc.routeschedulingnote.support.error.ExitCode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PlaceNoteRepository placeNoteRepository;

    @Autowired
    StopRepository stopRepository;

    @Autowired
    MapService mapService;

    @Override
    @Transactional
    public Schedule addSchedule(Schedule schedule) {
        List<Coordinates> coordinatesList = schedule.getPlaceNotes().stream().map(
                element -> element.getPlace().getCoordinates()
        ).collect(Collectors.toList());
        List<Coordinates> routeCoordinates = mapService.routeDirection(coordinatesList);

        // Schedule
        Schedule inserted = scheduleRepository.save(schedule);

        // Place Note
        inserted.getPlaceNotes().forEach(
                element -> element.setSchedule(inserted)
        );
        placeNoteRepository.saveAll(inserted.getPlaceNotes());

        // Stops
        List<Stop> stops = new ArrayList<>();
        int order = 1;
        for (Coordinates cor: routeCoordinates) {
            Stop stop = new Stop();
            stop.setCoordinates(cor);
            stop.setStopOrder(order++);
            stop.setSchedule(inserted);
            stops.add(stop);
        }
        stopRepository.saveAll(stops);

        return inserted;
    }

    @Override
    public List<Schedule> getListSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getById(Integer id) {
        ErrorType notFound = new ErrorType(
                HttpStatus.NOT_FOUND,
                ExitCode.E404,
                "Cannot find schedule with id = " + id);
        return scheduleRepository.findById(id).orElseThrow(
                () -> new CoreApiException(notFound)
        );
    }

    @Override
    public void deleteById(Integer id) {
        scheduleRepository.deleteById(id);
    }
}
