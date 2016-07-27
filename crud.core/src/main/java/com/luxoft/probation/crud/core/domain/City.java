package com.luxoft.probation.crud.core.domain;

import java.io.Serializable;

/**
 * City domain class
 * <p>
 * Created by HHayryan on 5/18/2016.
 */
public class City implements Serializable {
    private static final long serialVersionUID = -5379828134487877092L;

    private int id;
    private String name;
    private int countryId;

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
