package com.luxoft.probation.crud.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Flight domain class
 * <p>
 * Created by HHayryan on 5/18/2016.
 */
public class Flight implements Serializable {
    private static final long serialVersionUID = 191241092832491753L;

    private int id;
    private Date departureDate;
    private Date arrivalDate;
    private int firstClassPrice;
    private int secondClassPrice;
    private Aircraft aircraft;
    private City departureCity;
    private City arrivalCity;

    public Flight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getFirstClassPrice() {
        return firstClassPrice;
    }

    public void setFirstClassPrice(int firstClassPrice) {
        this.firstClassPrice = firstClassPrice;
    }

    public int getSecondClassPrice() {
        return secondClassPrice;
    }

    public void setSecondClassPrice(int secondClassPrice) {
        this.secondClassPrice = secondClassPrice;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(City arrivalCity) {
        this.arrivalCity = arrivalCity;
    }
}
