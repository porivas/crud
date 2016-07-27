package com.luxoft.probation.crud.persistence.dao.impl;

import com.luxoft.probation.crud.core.domain.City;
import com.luxoft.probation.crud.persistence.dao.CityDAO;
import com.luxoft.probation.crud.persistence.dao.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * City DAO implementation
 * <p>
 * Created by HHayryan on 5/19/2016.
 */
public class CityDAOImpl implements CityDAO {

    @Autowired
    private CityMapper cityMapper;

    @Override
    @Transactional
    public void createCity(City city) throws DuplicateKeyException {
        cityMapper.createCity(city);
    }

    @Override
    @Transactional
    public void createCityBatch(List<City> cityList) {
        cityMapper.createCityBatch(cityList);
    }

    @Override
    public City getCityById(int id) {
        return cityMapper.getCityById(id);
    }

    @Override
    public City getCityByName(String name) {
        return cityMapper.getCityByName(name);
    }

    @Override
    public List<City> getAllCitiesByCountryId(int countryId) {
        return cityMapper.getAllCitiesByCountryId(countryId);
    }

    @Override
    public List<City> getAllCities() {
        return cityMapper.getAllCities();
    }

    @Override
    public void updateCity(City city) {
        cityMapper.updateCity(city);
    }

    @Override
    public void deleteCity(int id) {
        cityMapper.deleteCity(id);
    }

    @Override
    public void deleteCityByName(String name) {
        cityMapper.deleteCityByName(name);
    }

    @Override
    public void deleteCityBatch(List<City> cityList) {
        cityMapper.deleteCityBatch(cityList);
    }
}
