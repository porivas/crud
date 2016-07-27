package com.luxoft.probation.crud.core.dto;

import com.luxoft.probation.crud.core.domain.Flight;

import java.io.Serializable;
import java.util.Queue;

/**
 * Flights DTO which encapsulated route of flight form departure city to arrival city
 * <p>
 * Created by HHayryan on 5/24/2016.
 */
public class FlightRoutDTO implements Serializable {
    private static final long serialVersionUID = -4778450336033096866L;

    private Queue<Flight> flightRout;

    public FlightRoutDTO() {
    }

    public Queue<Flight> getFlightRout() {
        return flightRout;
    }

    public void setFlightRout(Queue<Flight> flightRout) {
        this.flightRout = flightRout;
    }
}
