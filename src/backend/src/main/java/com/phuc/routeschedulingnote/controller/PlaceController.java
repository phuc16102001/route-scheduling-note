package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.exception.PlaceNotFoundException;
import com.phuc.routeschedulingnote.model.Place;
import com.phuc.routeschedulingnote.repository.PlaceRepository;
import com.phuc.routeschedulingnote.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping("/places")
    public ResponseEntity<Place> newPlace(@RequestBody Place place) {
        return placeService.newPlace(place);
    }

    @GetMapping("/places")
    ResponseEntity<List<Place>> allPlace() {
        return placeService.allPlace();
    }

    @GetMapping("/places/{id}")
    ResponseEntity<Place> onePlace(@PathVariable Integer id) {
        return placeService.onePlace(id);
    }

    @DeleteMapping("/places/{id}")
    void deletePlace(@PathVariable Integer id) {
        placeService.deleteById(id);
    }
}
