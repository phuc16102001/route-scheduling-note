package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.model.Coordinates;

public interface MapService {

    Coordinates searchCoordinate(String searchText);

}
