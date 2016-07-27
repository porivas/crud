package com.luxoft.probation.crud.service.test;

import com.luxoft.probation.crud.core.dto.CountryDTO;
import com.luxoft.probation.crud.service.GEOService;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Geo service unit tests
 * <p>
 * Created by hhayryan on 5/30/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/service-test.xml")
public class GeoServiceTest {

    @Autowired
    GEOService geoService;

    @Test
    public void getCountryTest() {
        ObjectMapper maper = new ObjectMapper();
        Map<String, Set<String>> countriesMap = new HashMap<>();
        try {
            List<Map> mapList = maper.readValue(new File(GeoServiceTest.class.getResource("/airports.json").toURI()), new TypeReference<List<Map>>() {
            });

            for (Map map : mapList) {
                String country = (String) map.get("country");
                if (country != null && !country.isEmpty()) {
                    if (countriesMap.containsKey(country)) {
                        Set<String> cities = countriesMap.get(country);
                        cities.add((String) map.get("city"));
                    } else {
                        Set<String> cities = new HashSet<>();
                        cities.add((String) map.get("city"));
                        countriesMap.put(country, cities);
                    }
                }
            }

            for (String countryName : countriesMap.keySet()) {
                CountryDTO countryDTO = new CountryDTO();
                countryDTO.setCountryName(countryName);
                countryDTO.setCitiesList(Arrays.asList(countriesMap.get(countryName).toArray(new String[]{})));
                geoService.createCountryWithCities(countryDTO);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
