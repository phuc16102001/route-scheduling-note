package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.dto.CoordinatesDto;
import com.phuc.routeschedulingnote.model.Coordinates;
import com.phuc.routeschedulingnote.service.MapService;
import com.phuc.routeschedulingnote.support.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MapController {

    @Autowired
    MapService mapService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/search")
    public ApiResponse<CoordinatesDto> searchCoordinate(@RequestParam(name="searchText") String searchText) {
        Coordinates coordinates = mapService.searchCoordinate(searchText);
        CoordinatesDto coordinatesDto = modelMapper.map(coordinates, CoordinatesDto.class);
        return ApiResponse.success(coordinatesDto);
    }

    @PostMapping("/direction")
    public ApiResponse<List<CoordinatesDto>> routeDirection(@RequestBody List<CoordinatesDto> coordinatesDtoList) {
        List<Coordinates> coordinates = coordinatesDtoList.stream().map(
                element -> modelMapper.map(element, Coordinates.class)
        ).toList();
        List<Coordinates> routes = mapService.routeDirection(coordinates);
        List<CoordinatesDto> coordinatesDto = routes.stream().map(
                element -> modelMapper.map(element, CoordinatesDto.class)
        ).toList();
        return ApiResponse.success(coordinatesDto);
    }
}
