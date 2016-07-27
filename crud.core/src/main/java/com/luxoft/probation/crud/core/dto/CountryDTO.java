package com.luxoft.probation.crud.core.dto;

import com.luxoft.probation.crud.core.domain.City;
import com.luxoft.probation.crud.core.domain.Country;

import java.io.Serializable;
import java.util.List;

/**
 * Country DTO
 * <p>
 * Created by hhayryan on 5/30/2016.
 */
public class CountryDTO implements Serializable {

    private static final long serialVersionUID = 3639593636364303111L;

    private String countryName;
    private List<String> citiesList;

    public CountryDTO() {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<String> getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(List<String> citiesList) {
        this.citiesList = citiesList;
    }
}
