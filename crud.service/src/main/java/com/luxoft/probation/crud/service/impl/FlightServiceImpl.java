package com.luxoft.probation.crud.service.impl;

import com.luxoft.probation.crud.core.domain.Flight;
import com.luxoft.probation.crud.persistence.dao.FlightDAO;
import com.luxoft.probation.crud.service.FlightService;
import org.springframework.stereotype.Service;

/**
 * Created by hhayryan on 6/1/2016.
 */
public class FlightServiceImpl implements FlightService {

    private FlightDAO flightDAO;

    @Override
    public Flight getFlightById(int flightId) {
        return flightDAO.getFlightById(flightId);
    }

    public void setFlightDAO(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }
}
