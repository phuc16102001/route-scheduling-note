package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.model.Place;

import java.util.List;

public interface PlaceService {
    Place newPlace(Place place);
    List<Place> allPlace();
    Place onePlace(Integer id);
    void deleteById(Integer id);

}
