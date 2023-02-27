package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.model.Place;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PlaceService {
    ResponseEntity<Place> newPlace(Place place);
    ResponseEntity<List<Place>> allPlace();
    ResponseEntity<Place> onePlace(Integer id);
    void deleteById(Integer id);

}
