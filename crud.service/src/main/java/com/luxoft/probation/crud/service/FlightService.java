package com.luxoft.probation.crud.service;

import com.luxoft.probation.crud.core.domain.Flight;
import org.springframework.stereotype.Service;

/**
 * Created by hhayryan on 6/1/2016.
 */
@Service
public interface FlightService {

    Flight getFlightById(int flightId);
}
