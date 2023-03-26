package com.phuc.routeschedulingnote.service.impl;

import com.phuc.routeschedulingnote.exception.CoreApiException;
import com.phuc.routeschedulingnote.model.Place;
import com.phuc.routeschedulingnote.model.PlaceNote;
import com.phuc.routeschedulingnote.model.User;
import com.phuc.routeschedulingnote.repository.PlaceRepository;
import com.phuc.routeschedulingnote.repository.UserRepository;
import com.phuc.routeschedulingnote.security.UserDetailsImpl;
import com.phuc.routeschedulingnote.service.PlaceService;
import com.phuc.routeschedulingnote.support.error.ErrorType;
import com.phuc.routeschedulingnote.support.error.ExitCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Place newPlace(Place place) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ErrorType errorType = new ErrorType(HttpStatus.NOT_FOUND, ExitCode.E2001, "Username not found");
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new CoreApiException(errorType));

        place.setUser(user);
        return placeRepository.save(place);
    }

    @Override
    @PostFilter("filterObject.user.id == authentication.principal.id")
    public List<Place> allPlace() {
        return placeRepository.findAll();
    }

    @Override
    @PostAuthorize("returnObject.user.id == authentication.principal.id")
    public Place onePlace(Integer id) {
        ErrorType notFound = new ErrorType(
                HttpStatus.NOT_FOUND,
                ExitCode.E404,
                "Cannot find place with id = " + id);
        return placeRepository.findById(id)
                .orElseThrow(() -> new CoreApiException(notFound));
    }

    @Override
    @PostAuthorize("returnObject.user.id == authentication.principal.id")
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
