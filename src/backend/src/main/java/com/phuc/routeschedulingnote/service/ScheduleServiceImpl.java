package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.model.Coordinates;
import com.phuc.routeschedulingnote.model.Schedule;
import com.phuc.routeschedulingnote.model.Stop;
import com.phuc.routeschedulingnote.repository.PlaceNoteRepository;
import com.phuc.routeschedulingnote.repository.ScheduleRepository;
import com.phuc.routeschedulingnote.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PlaceNoteRepository placeNoteRepository;

    @Autowired
    StopRepository stopRepository;

    @Autowired
    MapService mapService;

    @Override
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
}
