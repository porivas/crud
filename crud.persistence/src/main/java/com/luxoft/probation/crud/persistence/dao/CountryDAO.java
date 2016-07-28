package com.luxoft.probation.crud.persistence.dao;

import com.luxoft.probation.crud.core.domain.Country;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Country DAO
 * <p>
 * Created by hhayryan on 5/30/2016.
 */
@Component
public interface CountryDAO {

    void createCountry(Country country);

    void createCountryBatch(List<Country> countriesList);

    Country getCountryById(int id);

    List<Country> findCountryByName(String name);

    void updateCountry(Country country);

    void deleteCountry(int id);

    void deleteCountryByName(String name);

    void deleteCountryBatch(List<Country> countriesList);

    List<Country> getAllCountries();

    List<Country> getCountriesByPage(int from, int to);
}
