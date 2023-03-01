package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.model.GeoCoordinates;

public interface MapService {

    GeoCoordinates searchCoordinate(String searchText);

}
