package com.phuc.routeschedulingnote.service.impl;

import com.phuc.routeschedulingnote.model.Place;
import com.phuc.routeschedulingnote.repository.PlaceRepository;
import com.phuc.routeschedulingnote.service.PlaceService;
import com.phuc.routeschedulingnote.support.error.CoreApiException;
import com.phuc.routeschedulingnote.support.error.ErrorType;
import com.phuc.routeschedulingnote.support.error.ExitCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

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
        ErrorType notFound = new ErrorType(
                HttpStatus.NOT_FOUND,
                ExitCode.E404,
                "Cannot find place with id = " + id);
        return placeRepository.findById(id)
                .orElseThrow(() -> new CoreApiException(notFound));
    }

    @Override
    public void deleteById(Integer id) {
        placeRepository.deleteById(id);
    }
}
