package com.luxoft.probation.crud.persistence.dao;

import com.luxoft.probation.crud.core.domain.Flight;
import com.luxoft.probation.crud.core.dto.FlightRoutDTO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Flight DAO
 * <p>
 * Created by HHayryan on 5/24/2016.
 */
@Component
public interface FlightDAO {

    void createFlight(Flight flight);

    void deleteFlight(int id);

    void updateFlight(Flight flight);

    Flight getFlightById(int id);

    List<Flight> getAllFlightsPagination(int start, int count);

    List<Flight> getFlightsByDepartureDatePeriod(Date from, Date to);

    List<Flight> getFlightsByDeptDateAndFromToCities(Date departureDate, int departureCityId, int arrivalCityId);

    List<FlightRoutDTO> getFlightRout(Date flightDate, int departureCityId, int arrivalCityId);

    int getAllFlightsCount();
}
