package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.model.GeoCoordinates;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements MapService {

    @Override
    public ResponseEntity<GeoCoordinates> searchCoordinate(String searchText) {
        final String uri = "";

        GeoCoordinates response = new GeoCoordinates();
        response.setLat(1);
        response.setLng(1);
        return ResponseEntity.ok(response);
    }
}
