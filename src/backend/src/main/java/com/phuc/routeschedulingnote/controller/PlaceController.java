package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.exception.PlaceNotFoundException;
import com.phuc.routeschedulingnote.model.Place;
import com.phuc.routeschedulingnote.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaceController {

    @Autowired
    private PlaceRepository placeRepository;

    @PostMapping("/places")
    public Place newPlace(@RequestBody Place place) {
        return placeRepository.save(place);
    }

    @GetMapping("/places")
    List<Place> allPlace() {
        return placeRepository.findAll();
    }

    @GetMapping("/places/{id}")
    Place onePlace(@PathVariable Integer id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));
    }

    @DeleteMapping("/places/{id}")
    void deletePlace(@PathVariable Integer id) {
        placeRepository.deleteById(id);
    }
}
