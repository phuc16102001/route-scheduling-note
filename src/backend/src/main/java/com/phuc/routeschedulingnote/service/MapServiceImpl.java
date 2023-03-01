package com.phuc.routeschedulingnote.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phuc.routeschedulingnote.model.GeoCoordinates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapServiceImpl implements MapService {

    @Value("${ors.api_key}")
    private String orsApiKey;

    @Override
    public GeoCoordinates searchCoordinate(String searchText) {
        final String uri = "https://api.openrouteservice.org/geocode/search?api_key={orsApiKey}&text={searchText}&size=1";
        Map<String, String> variables = new HashMap<>();
        variables.put("orsApiKey", orsApiKey);
        variables.put("searchText", searchText);

        RestTemplate rest = new RestTemplate();
        Object searchObj = rest.getForObject(uri, Object.class, variables);
        System.out.println(searchObj);

        ObjectMapper mapper = new ObjectMapper();
        GeoCoordinates searchCoordinates = mapper.convertValue(searchObj, GeoCoordinates.class);

        GeoCoordinates response = new GeoCoordinates();
        response.setLat(searchCoordinates.getLat());
        response.setLng(searchCoordinates.getLng());
        return response;
    }
}
