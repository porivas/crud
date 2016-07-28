package com.luxoft.probation.crud.persistence.test;

import com.luxoft.probation.crud.core.domain.Country;
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

import static org.junit.Assert.*;

/**
 * Country DAO test
 * <p>
 * Created by hhayryan on 5/30/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/dao-test.xml")
public class CountryDAOTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryDAOTest.class);

    @Autowired
    CountryDAO countryDAO;

    @Test
    public void createCountryTest() {
        LOGGER.info("Start createCountryTest");
        Country country = new Country();
        country.setName(generateCountryRandomName());
        countryDAO.createCountry(country);

        Country selectedCountry = countryDAO.getCountryById(country.getId());

        assertNotNull("Country domain instance should not be null", selectedCountry);

        assertEquals("Country name should be equals", country.getName(), selectedCountry.getName());

        LOGGER.info("Country domain created successfully");

        countryDAO.deleteCountry(country.getId());
        LOGGER.info("Generated data during test successfully cleaned");
    }

    @Test
    public void createCountryBatchTest() {
        LOGGER.info("Start createCountryBatchTest");
        List<Country> countryList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Country country = new Country();
            country.setName(generateCountryRandomName());
            countryList.add(country);
        }

        countryDAO.createCountryBatch(countryList);

        List<Country> selectedCountryList = countryDAO.getAllCountries();

        assertNotNull("Selected list of Country domain should be initialized", selectedCountryList);

        assertEquals("Size of selected items should be equals", selectedCountryList.size(), countryList.size());

        LOGGER.info("List of Country domain created in batch mode successfully");

        countryDAO.deleteCountryBatch(selectedCountryList);
        LOGGER.info("Generated data during test successfully cleaned");
    }

    @Test
    public void getCountryByIdTest() {
        LOGGER.info("Start getCountryByIdTest");
        Country country = new Country();
        country.setName(generateCountryRandomName());
        countryDAO.createCountry(country);

        Country selectedCountry = countryDAO.getCountryById(country.getId());

        assertNotNull("Country domain instance should be initialized", selectedCountry);

        assertEquals("Country domain id should be equals", country.getId(), selectedCountry.getId());
        assertEquals("Country name should be equals", country.getName(), selectedCountry.getName());

        LOGGER.info("Country domain result, selected by ID successfully");

        countryDAO.deleteCountry(country.getId());
        LOGGER.info("Generated data during test successfully cleaned");
    }

    @Test
    public void findCountryByNameTest() {
        LOGGER.info("Start getCountryByName");
        Country country = new Country();
        country.setName(generateCountryRandomName());
        countryDAO.createCountry(country);

        try {
            List<Country> selectedCountryList = countryDAO.findCountryByName(country.getName().substring(0, 4) + "%");

            assertNotNull("Country domain instance should be initialized", selectedCountryList);
            assertEquals("Country name should be equals", country.getName(), selectedCountryList.get(0).getName());
            LOGGER.info("Country domain result, selected by Name successfully");
        } finally {
            countryDAO.deleteCountry(country.getId());
            LOGGER.info("Generated data during test successfully cleaned");
        }
    }

    @Test
    public void updateCountryTest() {
        LOGGER.info("Start updateCountryTest");
        Country country = new Country();
        country.setName(generateCountryRandomName());

        countryDAO.createCountry(country);

        country.setName(generateCountryRandomName());
        countryDAO.updateCountry(country);

        Country selectedCountry = countryDAO.getCountryById(country.getId());

        assertNotNull("Country domain instance should be initialized", selectedCountry);

        assertEquals("Country name should be equals", country.getName(), selectedCountry.getName());

        LOGGER.info("Country name successfully updated");

        countryDAO.deleteCountry(country.getId());
        LOGGER.info("Generated data during test successfully cleaned");
    }

    @Test
    public void deleteCountryTest() {
        LOGGER.info("Start deleteCountryTest");
        Country country = new Country();
        country.setName(generateCountryRandomName());
        countryDAO.createCountry(country);

        countryDAO.deleteCountry(country.getId());

        Country selectedCountry = countryDAO.getCountryById(country.getId());

        assertNull("Country with provided ID should not exist", selectedCountry);

        LOGGER.info("Country with provided ID: {}, successfully deleted", country.getId());
    }

    @Test
    public void deleteCountryByNameTest() {
        LOGGER.info("Start deleteCountryByNameTest");
        Country country = new Country();
        country.setName(generateCountryRandomName());
        countryDAO.createCountry(country);

        countryDAO.deleteCountryByName(country.getName());

        Country selectedCountry = countryDAO.getCountryById(country.getId());

        assertNull("Country with provided ID should not exist", selectedCountry);

        LOGGER.info("Country with provided ID: {}, successfully deleted", country.getId());
    }

    private String generateCountryRandomName() {
        return String.format("Country_%s", UUID.randomUUID().toString().substring(0, 9));
    }
}
