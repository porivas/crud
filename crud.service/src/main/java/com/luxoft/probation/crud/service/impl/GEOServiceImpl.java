package com.luxoft.probation.crud.service.impl;

import com.luxoft.probation.crud.core.domain.City;
import com.luxoft.probation.crud.core.domain.Country;
import com.luxoft.probation.crud.core.dto.CountryDTO;
import com.luxoft.probation.crud.persistence.dao.CityDAO;
import com.luxoft.probation.crud.persistence.dao.CountryDAO;
import com.luxoft.probation.crud.service.GEOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Country service implementation
 * <p>
 * Created by hhayryan on 5/30/2016.
 */
public class GEOServiceImpl implements GEOService {

    @Autowired
    CountryDAO countryDAO;

    @Autowired
    private CityDAO cityDAO;

    @Override
    public void createCountryWithCities(CountryDTO countryDto) {
        Country country = new Country();
        country.setName(countryDto.getCountryName());
        countryDAO.createCountry(country);

        for (String cityName : countryDto.getCitiesList()) {
            City city = new City();
            city.setName(cityName);
            city.setCountryId(country.getId());
            cityDAO.createCity(city);
        }
    }

    @Override
    public void createCountry(Country country) {
        countryDAO.createCountry(country);
    }

    @Override
    public void createCountryBatch(List<Country> countriesList) {
        countryDAO.createCountryBatch(countriesList);
    }

    @Override
    public Country getCountryById(int id) {
        return countryDAO.getCountryById(id);
    }

    @Override
    public List<Country> findCountryByName(String name) {
        return countryDAO.findCountryByName(name);
    }

    @Override
    public void updateCountry(Country country) {
        countryDAO.updateCountry(country);
    }

    @Override
    public void deleteCountry(int id) {
        countryDAO.deleteCountry(id);
    }

    @Override
    public void deleteCountryByName(String name) {
        countryDAO.deleteCountryByName(name);
    }

    @Override
    public void deleteCountryBatch(List<Country> countriesList) {
        countryDAO.deleteCountryBatch(countriesList);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryDAO.getAllCountries();
    }

    public List<Country> getCountriesByPage(int from, int to) {
        return countryDAO.getCountriesByPage(from, to);
    }
}
