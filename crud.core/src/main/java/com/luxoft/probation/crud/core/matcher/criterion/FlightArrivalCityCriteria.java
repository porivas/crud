package com.luxoft.probation.crud.core.matcher.criterion;

import com.luxoft.probation.crud.core.domain.Flight;
import com.luxoft.probation.crud.core.matcher.Criteria;
import com.luxoft.probation.crud.core.util.DateUtil;

/**
 * Flight criteria for finding all flights with matching departure city criteria
 * <p>
 * Created by HHayryan on 5/24/2016.
 */
public class FlightArrivalCityCriteria implements Criteria<Flight> {

    private Flight deptFlight;

    public FlightArrivalCityCriteria(Flight deptFlight) {
        this.deptFlight = deptFlight;
    }

    @Override
    public boolean matches(Flight bean) {
        return bean.getDepartureCity().getId() == deptFlight.getArrivalCity().getId()
                && DateUtil.isAcceptableFlightTimeGap(deptFlight.getArrivalDate(), bean.getDepartureDate())
                && deptFlight.getAircraft().getCompany().getId() == bean.getAircraft().getCompany().getId();
    }
}
