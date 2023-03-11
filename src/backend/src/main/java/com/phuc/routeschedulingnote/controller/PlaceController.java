package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.dto.place.PlaceGetDto;
import com.phuc.routeschedulingnote.dto.place.PlaceListDto;
import com.phuc.routeschedulingnote.dto.place.PlacePostDto;
import com.phuc.routeschedulingnote.model.Place;
import com.phuc.routeschedulingnote.service.PlaceService;
import com.phuc.routeschedulingnote.support.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/places")
    public ApiResponse<PlaceGetDto> newPlace(@RequestBody PlacePostDto placePostDto) {
        Place place = modelMapper.map(placePostDto, Place.class);
        Place createdPlace = placeService.newPlace(place);
        PlaceGetDto placeGetDto = modelMapper.map(createdPlace, PlaceGetDto.class);
        return ApiResponse.success(placeGetDto);
    }

    @GetMapping("/places")
    public ApiResponse<List<PlaceListDto>> allPlace() {
        List<Place> allPlace = placeService.allPlace();
        List<PlaceListDto> placeListDto = allPlace.stream()
                .map(element -> modelMapper.map(element, PlaceListDto.class))
                .toList();
        return ApiResponse.success(placeListDto);
    }

    @GetMapping("/places/{id}")
    public ApiResponse<PlaceGetDto> onePlace(@PathVariable Integer id) {
        Place selectedPlace = placeService.onePlace(id);
        PlaceGetDto placeGetDto = modelMapper.map(selectedPlace, PlaceGetDto.class);
        return ApiResponse.success(placeGetDto);
    }

    @DeleteMapping("/places/{id}")
    public ApiResponse<?> deletePlace(@PathVariable Integer id) {
        placeService.deleteById(id);
        return ApiResponse.success();
    }
}
