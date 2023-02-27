package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.exception.PlaceNotFoundException;
import com.phuc.routeschedulingnote.model.Place;
import com.phuc.routeschedulingnote.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService{

    @Autowired
    PlaceRepository placeRepository;

    @Override
    public ResponseEntity<Place> newPlace(Place place) {
        return ResponseEntity.ok(placeRepository.save(place));
    }

    @Override
    public ResponseEntity<List<Place>> allPlace() {
        return ResponseEntity.ok(placeRepository.findAll());
    }

    @Override
    public ResponseEntity<Place> onePlace(Integer id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));
        return ResponseEntity.ok(place);
    }

    @Override
    public void deleteById(Integer id) {
        placeRepository.deleteById(id);
    }
}
