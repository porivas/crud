package com.luxoft.probation.crud.persistence.dao.impl;

import com.luxoft.probation.crud.core.domain.Flight;
import com.luxoft.probation.crud.core.dto.FlightRoutDTO;
import com.luxoft.probation.crud.core.matcher.criterion.FlightArrivalCityCriteria;
import com.luxoft.probation.crud.core.util.DateUtil;
import com.luxoft.probation.crud.core.util.SearchUtil;
import com.luxoft.probation.crud.persistence.dao.FlightDAO;
import com.luxoft.probation.crud.persistence.dao.mapper.FlightMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Flight DAO implementation
 * <p>
 * Created by HHayryan on 5/24/2016.
 */
public class FlightDAOImpl implements FlightDAO {

    @Autowired
    FlightMapper flightMapper;

    @Override
    public void createFlight(Flight flight) {
        flightMapper.createFlight(flight);
    }

    @Override
    public void deleteFlight(int id) {
        flightMapper.deleteFlight(id);
    }

    @Override
    public void updateFlight(Flight flight) {
        flightMapper.updateFlight(flight);
    }

    @Override
    public Flight getFlightById(int id) {
        return flightMapper.getFlightById(id);
    }

    @Override
    public List<Flight> getAllFlightsPagination(int start, int count) {
        return flightMapper.getAllFlightsPagination(start, count);
    }

    @Override
    public List<Flight> getFlightsByDepartureDatePeriod(Date from, Date to) {
        return flightMapper.getFlightsByDepartureDatePeriod(from, to);
    }

    @Override
    public List<Flight> getFlightsByDeptDateAndFromToCities(Date departureDate, int departureCityId, int arrivalCityId) {
        return flightMapper.getFlightsByDeptDateAndFromToCities(departureDate,
                DateUtil.getIntervalDay(departureDate, 1),
                departureCityId, arrivalCityId);
    }

    /**
     * Retrieve flight rout based on date, departure city and arrival city
     *
     * @param flightDate      - Date and Time of flight departure
     * @param departureCityId - ID of flight departure city
     * @param arrivalCityId   - ID of arrivalCityId
     * @return List<FlightRoutDTO> - list of data transfer objects of flight rout
     */
    @Override
    public List<FlightRoutDTO> getFlightRout(Date flightDate, int departureCityId, int arrivalCityId) {
        List<FlightRoutDTO> flightRoutList = new ArrayList<>();

        //retrieve all flights domains based on departure city ID and flight date
        List<Flight> departureFlightsList = flightMapper.getFlightByDeptDateAndDeptCity(flightDate,
                DateUtil.getIntervalDay(flightDate, 1), departureCityId);

        //retrieve all flights domains based on arrival city ID and flight
        List<Flight> arrivalFlightsList = flightMapper.getFlightByArrivalDateAndArrivalCity(flightDate,
                DateUtil.getIntervalDay(flightDate, 1), arrivalCityId);

        departureFlightsList.forEach(flight -> {
            List<Flight> matchedDeptCities = SearchUtil.search(arrivalFlightsList,
                    new FlightArrivalCityCriteria(flight));

            if (matchedDeptCities != null && !matchedDeptCities.isEmpty()) {
                FlightRoutDTO dto = new FlightRoutDTO();
                Queue<Flight> flightsRoutQueue = new LinkedList<>();
                flightsRoutQueue.add(flight);
                flightsRoutQueue.addAll(matchedDeptCities);
                dto.setFlightRout(flightsRoutQueue);
                flightRoutList.add(dto);
            }
        });

        /*for (Flight flight : departureFlightsList) {
            //filter all flights where arrival city equals to rout destination city and departure city equals to flight arrival city
            List<Flight> matchedDeptCities = SearchUtil.search(arrivalFlightsList,
                    new FlightArrivalCityCriteria(flight));

            if (matchedDeptCities != null && !matchedDeptCities.isEmpty()) {
                FlightRoutDTO dto = new FlightRoutDTO();
                Queue<Flight> flightsRoutQueue = new LinkedList<>();
                flightsRoutQueue.add(flight);
                flightsRoutQueue.addAll(matchedDeptCities);
                dto.setFlightRout(flightsRoutQueue);
                flightRoutList.add(dto);
            }
        }*/

        return flightRoutList;
    }

    @Override
    public int getAllFlightsCount() {
        return flightMapper.getAllFlightsCount();
    }
}
