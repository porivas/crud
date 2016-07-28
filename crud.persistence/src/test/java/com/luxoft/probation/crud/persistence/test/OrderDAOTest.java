package com.luxoft.probation.crud.persistence.test;

import com.luxoft.probation.crud.core.domain.*;
import com.luxoft.probation.crud.core.util.BoardClassEnum;
import com.luxoft.probation.crud.core.util.DateUtil;
import com.luxoft.probation.crud.persistence.dao.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Order DAO implementation's unit tests
 * <p>
 * Created by hhayryan on 5/27/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/dao-test.xml")
public class OrderDAOTest {
    private static final Logger LOG = LoggerFactory.getLogger(OrderDAOTest.class);

    private static final String DEPARTURE_DATE_VALUE = "01-06-2016 00:10";
    private static final String ARRIVAL_DATE_VALUE = "01-06-2016 02:40";

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    FlightDAO flightDAO;

    @Autowired
    CityDAO cityDAO;

    @Autowired
    CompanyDAO compankyDAO;

    @Autowired
    AircraftDAO aircraftDAO;

    @Autowired
    CompanyDAO companyDAO;

    @Test
    public void createOrderTest() throws ParseException {
        LOG.info("Start createOrderTest");
        Order order = initMock();
        orderDAO.createOrder(order);

        Order selectedOrder = orderDAO.getOrderByOrderId(order.getOrderId());

        assertNotNull("Selected domain should be initialized", order);

        assertEquals("Order ID should be equals", order.getOrderId(), selectedOrder.getOrderId());
        assertEquals("Order Credit Card should be equals", order.getCreditCard(), selectedOrder.getCreditCard());
        assertEquals("Order tickets count should be equals", order.getCount(), selectedOrder.getCount());
        assertEquals("Order board class should be equals", order.getBoardClass(), selectedOrder.getBoardClass());
        assertEquals("Order Flight should be equals", order.getFlight().getId(), selectedOrder.getFlight().getId());

        LOG.info("createOrderTest successfully complete");

        cleanOrderMock(selectedOrder);
        LOG.info("Generated data during test successfully cleaned");
    }

    private Order initMock() throws ParseException {
        Order order = new Order();
        order.setBoardClass(BoardClassEnum.BOARD_CLASS_1.getBoardClass());
        order.setCount(1);
        order.setCreditCard("8888444455557777");
        Flight flight = initFlightMock();
        flightDAO.createFlight(flight);
        order.setFlight(flight);
        order.setOrderDate(flight.getDepartureDate());

        return order;
    }

    private void cleanOrderMock(Order order) {
        orderDAO.deleteOrder(order.getId());
        cleanFlightMock(order.getFlight());
    }

    private Flight initFlightMock() throws ParseException {
        Flight flight = new Flight();
        flight.setDepartureDate(DateUtil.getDateFromString(DateUtil.DEFAULT_DATE_TIME_PATTERN, DEPARTURE_DATE_VALUE));
        flight.setArrivalDate(DateUtil.getDateFromString(DateUtil.DEFAULT_DATE_TIME_PATTERN, ARRIVAL_DATE_VALUE));

        City deptCity = new City();
        deptCity.setName("Wroclaw");
        cityDAO.createCity(deptCity);
        flight.setDepartureCity(deptCity);

        City arrivalCity = new City();
        arrivalCity.setName("Yervan");
        cityDAO.createCity(arrivalCity);
        flight.setArrivalCity(arrivalCity);

        flight.setFirstClassPrice(500);
        flight.setSecondClassPrice(400);

        Aircraft aircraft = new Aircraft();
        aircraft.setModel("Airbas 306");
        aircraft.setFirstClassCount(24);
        aircraft.setSecondClassCount(306);

        Company company = new Company();
        company.setName("LOT");
        companyDAO.createCompany(company);
        aircraft.setCompany(company);

        aircraftDAO.createAircraft(aircraft);
        flight.setAircraft(aircraft);

        return flight;
    }

    private void cleanFlightMock(Flight flight) {
        flightDAO.deleteFlight(flight.getId());
        aircraftDAO.deleteAircraft(flight.getAircraft().getId());
        companyDAO.deleteCompany(flight.getAircraft().getCompany().getId());
        cityDAO.deleteCity(flight.getDepartureCity().getId());
        cityDAO.deleteCity(flight.getArrivalCity().getId());
    }
}
