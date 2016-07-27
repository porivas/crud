package com.luxoft.probation.crud.core.domain;

import java.io.Serializable;

/**
 * Aircraft domain class
 * <p>
 * Created by HHayryan on 5/18/2016.
 */
public class Aircraft implements Serializable {
    private static final long serialVersionUID = 1134750164235396065L;

    private int id;
    private String model;
    private int firstClassCount;
    private int secondClassCount;
    private Company company;

    public Aircraft() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getFirstClassCount() {
        return firstClassCount;
    }

    public void setFirstClassCount(int firstClassCount) {
        this.firstClassCount = firstClassCount;
    }

    public int getSecondClassCount() {
        return secondClassCount;
    }

    public void setSecondClassCount(int secondClassCount) {
        this.secondClassCount = secondClassCount;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
