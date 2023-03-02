package com.phuc.routeschedulingnote.service;

import com.phuc.routeschedulingnote.dto.OrsFeatureDto;
import com.phuc.routeschedulingnote.dto.OrsGeocodeDto;
import com.phuc.routeschedulingnote.exception.GeocodeNotFoundException;
import com.phuc.routeschedulingnote.model.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapServiceImpl implements MapService {

    @Value("${ors.api_key}")
    private String orsApiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Coordinates searchCoordinate(String searchText) {
        final String uri = "https://api.openrouteservice.org/geocode/search?api_key={orsApiKey}&text={searchText}&size=1";
        Map<String, String> variables = new HashMap<>();
        variables.put("orsApiKey", orsApiKey);
        variables.put("searchText", searchText);

        OrsGeocodeDto searchObj = restTemplate.getForObject(uri, OrsGeocodeDto.class, variables);
        assert searchObj != null;
        List<OrsFeatureDto> features = searchObj.getFeatures();
        if (features.size()==0) {
            throw new GeocodeNotFoundException(searchText);
        }
        List<Double> coordinates = features.get(0).getGeometry().getCoordinates();

        Coordinates response = new Coordinates();
        response.setLng(coordinates.get(0));
        response.setLat(coordinates.get(1));
        return response;
    }
}
