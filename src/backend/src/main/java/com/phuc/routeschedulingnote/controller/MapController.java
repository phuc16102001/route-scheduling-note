package com.phuc.routeschedulingnote.controller;

import com.phuc.routeschedulingnote.model.GeoCoordinates;
import com.phuc.routeschedulingnote.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {

    @Autowired
    MapService mapService;

    @GetMapping("/search")
    public GeoCoordinates searchCoordinate(
            @RequestParam(name="searchText") String searchText) {
        return mapService.searchCoordinate(searchText);
    }
}
