package com.phuc.routeschedulingnote.service.impl;

import com.phuc.routeschedulingnote.dto.ors.OrsDirectionDto;
import com.phuc.routeschedulingnote.dto.ors.OrsDirectionQueryDto;
import com.phuc.routeschedulingnote.dto.ors.OrsGeoFeatureDto;
import com.phuc.routeschedulingnote.dto.ors.OrsGeocodeDto;
import com.phuc.routeschedulingnote.model.Coordinates;
import com.phuc.routeschedulingnote.service.MapService;
import com.phuc.routeschedulingnote.support.error.CoreApiException;
import com.phuc.routeschedulingnote.support.error.ErrorType;
import com.phuc.routeschedulingnote.support.error.ExitCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MapServiceImpl implements MapService {

    @Value("${ors.api_key}")
    private String orsApiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Coordinates searchCoordinate(String searchText) {
        final String uri = "https://api.openrouteservice.org/geocode/search?api_key={orsApiKey}&text={searchText}&size=1";
        Map<String, String> variables = new HashMap<>();
        variables.put("orsApiKey", orsApiKey);
        variables.put("searchText", searchText);

        OrsGeocodeDto searchObj = restTemplate.getForObject(uri, OrsGeocodeDto.class, variables);
        assert searchObj != null;
        List<OrsGeoFeatureDto> features = searchObj.getFeatures();
        if (features.size()==0) {
            ErrorType notFound = new ErrorType(
                    HttpStatus.NOT_FOUND,
                    ExitCode.E404,
                    "Cannot find place with search text = " + searchText);
            throw new CoreApiException(notFound);
        }
        List<Double> coordinates = features.get(0).getGeometry().getCoordinates();

        Coordinates response = new Coordinates();
        response.setLng(coordinates.get(0));
        response.setLat(coordinates.get(1));
        return response;
    }

    @Override
    public List<Coordinates> routeDirection(List<Coordinates> coordinates) {
        final String uri = "https://api.openrouteservice.org/v2/directions/driving-car/geojson";
        OrsDirectionQueryDto query = new OrsDirectionQueryDto();
        query.setCoordinates(coordinates.stream().map(
                element -> new ArrayList<>(
                        Arrays.asList(element.getLng(), element.getLat()))
        ).collect(Collectors.toList()));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", orsApiKey);
        HttpEntity<OrsDirectionQueryDto> entity = new HttpEntity<>(query, headers);
        OrsDirectionDto searchObj = restTemplate.postForObject(uri, entity, OrsDirectionDto.class);
        assert searchObj != null;

        return searchObj.getFeatures().get(0).getGeometry().getCoordinates().stream().map(
                element -> {
                    Coordinates coordinate = new Coordinates();
                    coordinate.setLat(element.get(1));
                    coordinate.setLng(element.get(0));
                    return coordinate;
                }
        ).toList();
    }


}
