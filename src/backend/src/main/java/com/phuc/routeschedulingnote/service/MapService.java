package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.model.Coordinates;

import java.util.List;

public interface MapService {

    Coordinates searchCoordinate(String searchText);
    List<Coordinates> routeDirection(List<Coordinates> coordinates);
}
