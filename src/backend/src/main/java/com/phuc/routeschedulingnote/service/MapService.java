package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.model.GeoCoordinates;
import org.springframework.http.ResponseEntity;

public interface MapService {

    ResponseEntity<GeoCoordinates> searchCoordinate(String searchText);

}
