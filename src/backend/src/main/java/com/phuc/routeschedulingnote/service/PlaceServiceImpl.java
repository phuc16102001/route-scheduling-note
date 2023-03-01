package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.exception.PlaceNotFoundException;
import com.phuc.routeschedulingnote.model.Place;
import com.phuc.routeschedulingnote.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService{

    @Autowired
    PlaceRepository placeRepository;

    @Override
    public Place newPlace(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public List<Place> allPlace() {
        return placeRepository.findAll();
    }

    @Override
    public Place onePlace(Integer id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));
    }

    @Override
    public void deleteById(Integer id) {
        placeRepository.deleteById(id);
    }
}
