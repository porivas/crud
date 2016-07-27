package com.luxoft.probation.crud.persistence.test;

import com.luxoft.probation.crud.core.domain.City;
import com.luxoft.probation.crud.core.domain.Country;
import com.luxoft.probation.crud.persistence.dao.CityDAO;
import com.luxoft.probation.crud.persistence.dao.CountryDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * City DAO implementation's unit tests
 * <p>
 * Created by HHayryan on 5/19/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/dao-test.xml")
public class CityDAOTestDAO {

    private static final Logger LOG = LoggerFactory.getLogger(CityDAOTestDAO.class);
    @Autowired
    CityDAO cityDAO;

    @Autowired
    CountryDAO countryDAO;


    @Test
    public void createCityTest() {
        LOG.info("Start createCityTest");
        City city = new City();
        city.setName(generateCityRandomName());

        Country country = createMockCountry();

        city.setCountryId(country.getId());
        cityDAO.createCity(city);

        City selectedCity = cityDAO.getCityByName(city.getName());

        assertNotNull("City domain instance should not be null", selectedCity);

        assertEquals("City name should be equals", city.getName(), selectedCity.getName());

        LOG.info("City domain created successfully");

        cityDAO.deleteCity(city.getId());
        countryDAO.deleteCountry(country.getId());
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void createCityBatchTest() {
        LOG.info("Start createCityBatchTest");
        List<City> cityList = new ArrayList<>();

        Country country = createMockCountry();

        for (int i = 0; i < 10; i++) {
            City city = new City();
            city.setName(generateCityRandomName());
            city.setCountryId(country.getId());
            cityList.add(city);
        }

        cityDAO.createCityBatch(cityList);

        List<City> selectedCitiesList = cityDAO.getAllCities();

        assertNotNull("Selected list of City domain should be initialized", selectedCitiesList);

        assertEquals("Size of selected items should be equals", cityList.size(), selectedCitiesList.size());

        LOG.info("List of City domain created in batch mode successfully");

        cityDAO.deleteCityBatch(selectedCitiesList);
        countryDAO.deleteCountry(country.getId());
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void getCityByIdTest() {
        LOG.info("Start getCityByIdTest");
        City city = new City();
        city.setName(generateCityRandomName());
        Country country = createMockCountry();
        city.setCountryId(country.getId());
        cityDAO.createCity(city);

        City selectedCity = cityDAO.getCityById(city.getId());

        assertNotNull("City domain instance should be initialized", selectedCity);

        assertEquals("City domain id should be equals", city.getId(), selectedCity.getId());
        assertEquals("City name should be equals", city.getName(), selectedCity.getName());

        LOG.info("City domain result, selected by ID successfully");

        cityDAO.deleteCity(city.getId());
        countryDAO.deleteCountry(country.getId());
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void getCityByName() {
        LOG.info("Start getCityByName");
        City city = new City();
        city.setName(generateCityRandomName());
        Country country = createMockCountry();
        city.setCountryId(country.getId());
        cityDAO.createCity(city);

        City selectedCity = cityDAO.getCityByName(city.getName());

        assertNotNull("City domain instance should be initialized", selectedCity);
        assertEquals("City name should be equals", city.getName(), selectedCity.getName());
        LOG.info("City domain result, selected by Name successfully");

        cityDAO.deleteCity(city.getId());
        countryDAO.deleteCountry(country.getId());
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void updateCityTest() {
        LOG.info("Start updateCityTest");
        City city = new City();
        city.setName(generateCityRandomName());
        Country country = createMockCountry();
        city.setCountryId(country.getId());
        cityDAO.createCity(city);

        city.setName(generateCityRandomName());
        cityDAO.updateCity(city);

        City selectedCity = cityDAO.getCityById(city.getId());

        assertNotNull("City domain instance should be initialized", selectedCity);

        assertEquals("City name should be equals", city.getName(), selectedCity.getName());

        LOG.info("City name successfully updated");

        cityDAO.deleteCity(city.getId());
        countryDAO.deleteCountry(country.getId());
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void deleteCityTest() {
        LOG.info("Start deleteCityTest");
        City city = new City();
        city.setName(generateCityRandomName());
        Country country = createMockCountry();
        city.setCountryId(country.getId());
        cityDAO.createCity(city);

        cityDAO.deleteCity(city.getId());

        City selectedCity = cityDAO.getCityById(city.getId());

        assertNull("City with provided ID should not exist", selectedCity);

        countryDAO.deleteCountry(country.getId());

        LOG.info("City with provided ID: {}, successfully deleted", city.getId());
    }

    @Test
    public void deleteCityByNameTest() {
        LOG.info("Start deleteCityByNameTest");
        City city = new City();
        city.setName(generateCityRandomName());
        Country country = createMockCountry();
        city.setCountryId(country.getId());
        cityDAO.createCity(city);

        cityDAO.deleteCityByName(city.getName());

        City selectedCity = cityDAO.getCityByName(city.getName());

        assertNull("City with provided ID should not exist", selectedCity);

        countryDAO.deleteCountry(country.getId());
        LOG.info("City with provided ID: {}, successfully deleted", city.getId());
    }

    private String generateCityRandomName() {
        return String.format("City_Name_%s", UUID.randomUUID().toString().substring(0, 9));
    }

    private Country createMockCountry() {
        Country country = new Country();
        country.setName("Poland");
        countryDAO.createCountry(country);

        return country;
    }
}
