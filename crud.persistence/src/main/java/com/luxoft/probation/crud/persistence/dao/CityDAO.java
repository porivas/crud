package com.luxoft.probation.crud.persistence.dao;

import com.luxoft.probation.crud.core.domain.City;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

/**
 * City DAO
 * <p>
 * Created by HHayryan on 5/19/2016.
 */
public interface CityDAO {

    void createCity(City city) throws DuplicateKeyException;

    void createCityBatch(List<City> cityList);

    City getCityById(int id);

    City getCityByName(String name);

    List<City> getAllCitiesByCountryId(int countryId);

    List<City> getAllCities();

    void updateCity(City city);

    void deleteCity(int id);

    void deleteCityByName(String name);

    void deleteCityBatch(List<City> cityList);
}
