package com.luxoft.probation.crud.core.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hhayryan on 5/30/2016.
 */
public class Country implements Serializable {
    private static final long serialVersionUID = -462814545101139745L;

    private int id;
    private String name;
    List<City> cities;

    public Country() {
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

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
