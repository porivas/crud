package com.luxoft.probation.crud.persistence.dao.impl;

import com.luxoft.probation.crud.core.domain.Country;
import com.luxoft.probation.crud.persistence.dao.CountryDAO;
import com.luxoft.probation.crud.persistence.dao.mapper.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Country DAO implementation
 * <p>
 * Created by hhayryan on 5/30/2016.
 */
public class CountryDAOImpl implements CountryDAO {

    @Autowired
    CountryMapper countryMapper;

    @Override
    public void createCountry(Country country) {
        countryMapper.createCountry(country);
    }

    @Override
    public void createCountryBatch(List<Country> countriesList) {
        countryMapper.createCountryBatch(countriesList);
    }

    @Override
    public Country getCountryById(int id) {
        return countryMapper.getCountryById(id);
    }

    @Override
    public List<Country> findCountryByName(String name) {
        return countryMapper.findCountryByName(name);
    }

    @Override
    public void updateCountry(Country country) {
        countryMapper.updateCountry(country);
    }

    @Override
    public void deleteCountry(int id) {
        countryMapper.deleteCountry(id);
    }

    @Override
    public void deleteCountryByName(String name) {
        countryMapper.deleteCountryByName(name);
    }

    @Override
    public void deleteCountryBatch(List<Country> countriesList) {
        countryMapper.deleteCountryBatch(countriesList);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryMapper.getAllCountries();
    }

    @Override
    public List<Country> getCountriesByPage(int from, int to) {
        return countryMapper.getCountriesByPage(from, to);
    }
}
