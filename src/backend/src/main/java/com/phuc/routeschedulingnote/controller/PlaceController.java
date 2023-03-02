package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.dto.outbound.PlaceDto;
import com.phuc.routeschedulingnote.model.Place;
import com.phuc.routeschedulingnote.service.PlaceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/places")
    public PlaceDto newPlace(@RequestBody Place place) {
        Place createdPlace = placeService.newPlace(place);
        return modelMapper.map(createdPlace, PlaceDto.class);
    }

    @GetMapping("/places")
    List<PlaceDto> allPlace() {
        List<Place> allPlace = placeService.allPlace();
        return allPlace.stream()
                .map(element -> modelMapper.map(element, PlaceDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/places/{id}")
    PlaceDto onePlace(@PathVariable Integer id) {
        Place selectedPlace = placeService.onePlace(id);
        return modelMapper.map(selectedPlace, PlaceDto.class);
    }

    @DeleteMapping("/places/{id}")
    void deletePlace(@PathVariable Integer id) {
        placeService.deleteById(id);
    }
}
