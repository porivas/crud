package com.luxoft.probation.crud.core.domain;

import java.io.Serializable;

/**
 * Company domain class
 * <p>
 * Created by HHayryan on 5/18/2016.
 */
public class Company implements Serializable {

    private static final long serialVersionUID = -8596543181616625460L;

    private int id;
    private String name;

    public Company() {
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

}
