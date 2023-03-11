package com.phuc.routeschedulingnote.service.impl;

import com.phuc.routeschedulingnote.exception.CoreApiException;
import com.phuc.routeschedulingnote.model.Place;
import com.phuc.routeschedulingnote.model.PlaceNote;
import com.phuc.routeschedulingnote.repository.PlaceRepository;
import com.phuc.routeschedulingnote.service.PlaceService;
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
        ErrorType notFound = new ErrorType(
                HttpStatus.NOT_FOUND,
                ExitCode.E404,
                "Place not found with id = " + id);
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new CoreApiException(notFound));
        List<PlaceNote> placeNotes = place.getPlaceNotes();
        if (placeNotes.size()>0) {
            ErrorType involveException = new ErrorType(
                    HttpStatus.CONFLICT,
                    ExitCode.E1000,
                    "Place are related to some other resource");
            throw new CoreApiException(involveException);
        }
        placeRepository.deleteById(id);
    }
}
