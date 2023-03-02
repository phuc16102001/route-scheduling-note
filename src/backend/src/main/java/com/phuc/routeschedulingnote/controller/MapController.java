package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.dto.CoordinatesDto;
import com.phuc.routeschedulingnote.model.Coordinates;
import com.phuc.routeschedulingnote.service.MapService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
