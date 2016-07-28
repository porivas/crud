package com.luxoft.probation.crud.service;

import com.luxoft.probation.crud.core.domain.Country;
import com.luxoft.probation.crud.core.dto.CountryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Country service
 * <p>
 * Created by hhayryan on 5/30/2016.
 */
@Service
public interface GEOService {

    void createCountryWithCities(CountryDTO countryDto);

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
