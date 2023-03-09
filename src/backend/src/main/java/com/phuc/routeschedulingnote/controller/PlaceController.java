package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.dto.place.PlaceGetDto;
import com.phuc.routeschedulingnote.dto.place.PlaceListDto;
import com.phuc.routeschedulingnote.dto.place.PlacePostDto;
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
    public PlaceGetDto newPlace(@RequestBody PlacePostDto placePostDto) {
        Place place = modelMapper.map(placePostDto, Place.class);
        Place createdPlace = placeService.newPlace(place);
        return modelMapper.map(createdPlace, PlaceGetDto.class);
    }

    @GetMapping("/places")
    List<PlaceListDto> allPlace() {
        List<Place> allPlace = placeService.allPlace();
        return allPlace.stream()
                .map(element -> modelMapper.map(element, PlaceListDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/places/{id}")
    PlaceGetDto onePlace(@PathVariable Integer id) {
        Place selectedPlace = placeService.onePlace(id);
        return modelMapper.map(selectedPlace, PlaceGetDto.class);
    }

    @DeleteMapping("/places/{id}")
    void deletePlace(@PathVariable Integer id) {
        placeService.deleteById(id);
    }
}
