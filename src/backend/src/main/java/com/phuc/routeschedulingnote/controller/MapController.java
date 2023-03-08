package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.dto.CoordinatesDto;
import com.phuc.routeschedulingnote.model.Coordinates;
import com.phuc.routeschedulingnote.service.MapService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MapController {

    @Autowired
    MapService mapService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/search")
    public CoordinatesDto searchCoordinate(
            @RequestParam(name="searchText") String searchText) {
        Coordinates coordinates = mapService.searchCoordinate(searchText);
        return modelMapper.map(coordinates, CoordinatesDto.class);
    }

    @PostMapping("/direction")
    public List<CoordinatesDto> routeDirection(
            @RequestBody List<CoordinatesDto> coordinatesDtoList
    ) {
        List<Coordinates> coordinates = coordinatesDtoList.stream()
                .map(element -> modelMapper.map(element, Coordinates.class))
                .collect(Collectors.toList());
        List<Coordinates> routes = mapService.routeDirection(coordinates);
        return routes.stream()
                .map(element -> modelMapper.map(element, CoordinatesDto.class))
                .collect(Collectors.toList());
    }
}
