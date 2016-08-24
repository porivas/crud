package com.luxoft.probation.crud.persistence.test;

import com.luxoft.probation.crud.core.domain.Aircraft;
import com.luxoft.probation.crud.core.domain.City;
import com.luxoft.probation.crud.core.domain.Company;
import com.luxoft.probation.crud.core.domain.Flight;
import com.luxoft.probation.crud.core.dto.FlightRoutDTO;
import com.luxoft.probation.crud.core.util.DateUtil;
import com.luxoft.probation.crud.persistence.dao.AircraftDAO;
import com.luxoft.probation.crud.persistence.dao.CityDAO;
import com.luxoft.probation.crud.persistence.dao.CompanyDAO;
import com.luxoft.probation.crud.persistence.dao.CountryDAO;
import com.luxoft.probation.crud.persistence.dao.FlightDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Flight DAO implementations test
 * <p>
 * Created by HHayryan on 5/24/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/dao-test.xml")
public class FlightDAOTest {

    private static final Logger LOG = LoggerFactory.getLogger(FlightDAOTest.class);

    private static final String DEPARTURE_DATE_VALUE = "01-06-2016 00:10";
    private static final String ARRIVAL_DATE_VALUE = "01-06-2016 02:40";

    private static final String COMPANY_LOT = "LOT";
    private static final String COMPANY_LH = "Lufthansa";
    private static final String COMPANY_AUS_AIR = "Austrian Airlines";

    private static final String CITY_WAW = "WROCLAW";
    private static final String CITY_EVN = "YEREVAN";
    private static final String CITY_MOS = "MOSCOW";
    private static final String CITY_KE = "KIEV";
    private static final String CITY_BR = "BERLIN";
    private static final String CITY_VN = "VIENA";
    private static final String CITY_PH = "PRAHA";

    @Autowired
    CityDAO cityDAO;

    @Autowired
    CompanyDAO companyDAO;

    @Autowired
    AircraftDAO aircraftDAO;

    @Autowired
    FlightDAO flightDAO;

    @Autowired
    CountryDAO countryDAO;

    @Test
    public void createFlightTest() throws ParseException {
        LOG.info("Start createFlightTest");
        Flight flight = initFlightMock();
        flightDAO.createFlight(flight);

        Flight selectedFlight = flightDAO.getFlightById(flight.getId());

        assertNotNull("Selected Flight domain object should be initialized", selectedFlight);

        assertEquals("Flight date departure should be equals",
                flight.getDepartureDate(), selectedFlight.getDepartureDate());
        assertEquals("Flight departure city should be equals",
                flight.getDepartureCity().getId(), selectedFlight.getDepartureCity().getId());
        assertEquals("Flight date arrival should be equals",
                flight.getArrivalDate(), selectedFlight.getArrivalDate());
        assertEquals("Flight arrival city should be equals",
                flight.getArrivalCity().getId(), selectedFlight.getArrivalCity().getId());
        assertEquals("Flight aircraft ID should be equals",
                flight.getAircraft().getId(), selectedFlight.getAircraft().getId());
        assertEquals("Flight aircraft first class price should be equals",
                flight.getFirstClassPrice(), selectedFlight.getFirstClassPrice());
        assertEquals("Flight aircraft secnd class price should be equals",
                flight.getSecondClassPrice(), selectedFlight.getSecondClassPrice());

        LOG.info("createFlightTest successfully complete");

        cleanFlightMock(selectedFlight);
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void deleteFlightTest() throws ParseException {
        LOG.info("Start deleteFlightTest");
        Flight flight = initFlightMock();
        flightDAO.createFlight(flight);

        Flight selectedFlight = flightDAO.getFlightById(flight.getId());

        assertNotNull("Selected Flight domain object should be initialized", selectedFlight);

        flightDAO.deleteFlight(selectedFlight.getId());

        Flight deletedFlight = flightDAO.getFlightById(selectedFlight.getId());

        assertNull("Flight with provided ID should not exists", deletedFlight);

        LOG.info("deleteFlightTest successfully complete");

        cleanFlightMock(selectedFlight);
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void updateFlight() throws ParseException {
        LOG.info("Start deleteFlightTest");
        Flight flight = initFlightMock();
        flightDAO.createFlight(flight);

        Flight selectedFlight = flightDAO.getFlightById(flight.getId());

        selectedFlight.setFirstClassPrice(1000);
        selectedFlight.setSecondClassPrice(500);
        selectedFlight.setDepartureDate(DateUtil.getDateFromString(DateUtil.DEFAULT_DATE_TIME_PATTERN, "04-06-2016 22:00"));
        selectedFlight.setArrivalDate(DateUtil.getDateFromString(DateUtil.DEFAULT_DATE_TIME_PATTERN, "05-06-2016 01:00"));

        flightDAO.updateFlight(selectedFlight);

        Flight updatedFlight = flightDAO.getFlightById(selectedFlight.getId());

        assertNotNull("Updated flight domain should be initialized", updatedFlight);
        assertEquals("Updated flight first class price should be equals",
                selectedFlight.getFirstClassPrice(), updatedFlight.getFirstClassPrice());
        assertEquals("Updated flight second class price should be equals",
                selectedFlight.getSecondClassPrice(), updatedFlight.getSecondClassPrice());
        assertEquals("Updated flight departure date should be equals",
                selectedFlight.getDepartureDate(), updatedFlight.getDepartureDate());
        assertEquals("Updated flight arrival date should be equals",
                selectedFlight.getArrivalDate(), updatedFlight.getArrivalDate());

        LOG.info("updateFlight successfully complete");

        cleanFlightMock(selectedFlight);
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void getAllFlightsPaginationTest() throws ParseException {
        LOG.info("Start deleteFlightTest");
        createMockList();

        int count = flightDAO.getAllFlightsCount();

        int pagesCount = count / 20; //Maximum 20 rows per each page
        for (int i = 0; i < pagesCount; i++) {
            List<Flight> selectedPageRows = flightDAO.getAllFlightsPagination(i, 20);
            if (selectedPageRows != null) {
                assertTrue("Selected rows should not be more than 20", (selectedPageRows.size() <= 20));
                selectedPageRows.clear();
            }
        }

        LOG.info("getAllFlightsPaginationTest successfully complete");

        deleteMockList(flightDAO.getAllFlightsPagination(0, 100));
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void getFlightsByDepartureDatePeriodTest() throws ParseException {
        LOG.info("Start deleteFlightTest");
        createMockList();

        Date departureDate = DateUtil.getDateFromString(DEPARTURE_DATE_VALUE);
        Date arrivaleDate = DateUtil.getDateFromString(ARRIVAL_DATE_VALUE);

        List<Flight> selectedFlightList = flightDAO.getFlightsByDepartureDatePeriod(departureDate, arrivaleDate);

        assertNotNull("Selected flight list should be initialized", selectedFlightList);

        for (Flight flight : selectedFlightList) {
            assertEquals("Flight departure date should be equals", departureDate, flight.getDepartureDate());
            assertEquals("Flight arrival date should be equals", arrivaleDate, flight.getArrivalDate());
        }

        LOG.info("getFlightsByDepartureDatePeriodTest successfully complete");
        deleteMockList(selectedFlightList);
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void getFlightsByDeptDateAndFromToCities() throws ParseException {
        LOG.info("Start deleteFlightTest");
        createMockList();
        City departureCity = cityDAO.getCityByName("Wroclaw");
        City arrivalCity = cityDAO.getCityByName("Yervan");
        List<Flight> selectedFlightsList = flightDAO.getFlightsByDeptDateAndFromToCities(
                DateUtil.getDateFromString(DEPARTURE_DATE_VALUE), departureCity.getId(), arrivalCity.getId());

        assertNotNull("Selected flights list should be initialized", selectedFlightsList);

        for (Flight flight : selectedFlightsList) {
            assertEquals("Flight departure city should be equals", departureCity.getId(), flight.getDepartureCity().getId());
            assertEquals("Flight arrival city should be equals", arrivalCity.getId(), flight.getArrivalCity().getId());
        }

        LOG.info("getFlightsByDeptDateAndFromToCities successfully complete");
        deleteMockList(selectedFlightsList);
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void getFlightRoutTest() throws ParseException {
        LOG.info("Start deleteFlightTest");
        List<Flight> flights = initMockFlightsRout();
        City cityDept = cityDAO.getCityByName(CITY_WAW);
        City cityArrival = cityDAO.getCityByName(CITY_EVN);

        List<FlightRoutDTO> flightRoutList = flightDAO.getFlightRout(DateUtil.getDateFromString("01-06-2016 00:00"),
                cityDept.getId(), cityArrival.getId());

        assertTrue("Selected flight rout should be initialized", !flightRoutList.isEmpty());

        for (FlightRoutDTO dto : flightRoutList) {
            Queue<Flight> flightRoutQueue = dto.getFlightRout();
            assertNotNull("Flight rout queue should be initialized", flightRoutQueue);

            Flight flightStart = flightRoutQueue.poll();
            assertNotNull("Rout start flight should be initialized", flightStart);

            assertTrue("Flight rout should has next step of rout", !flightRoutQueue.isEmpty());
            Flight flightNext = flightRoutQueue.poll();

            assertEquals("The arrival and departure city of flight should be equals",
                    flightStart.getArrivalCity().getId(), flightNext.getDepartureCity().getId());

            assertTrue("The gap of flights time should be equal or more than 40 minutes",
                    DateUtil.isAcceptableFlightTimeGap(flightStart.getArrivalDate(), flightNext.getDepartureDate()));

            assertEquals("The flights compny should be equals",
                    flightStart.getAircraft().getCompany().getId(), flightNext.getAircraft().getCompany().getId());
        }

        deleteRoutMockList(flights);
    }

    private void createMockList() throws ParseException {
        City deptCity = new City();
        deptCity.setName("Wroclaw");
        cityDAO.createCity(deptCity);

        City arrivalCity = new City();
        arrivalCity.setName("Yervan");
        cityDAO.createCity(arrivalCity);

        Aircraft aircraft = new Aircraft();
        aircraft.setModel("Airbas 306");
        aircraft.setFirstClassCount(24);
        aircraft.setSecondClassCount(306);

        Company company = new Company();
        company.setName("LOT");
        companyDAO.createCompany(company);
        aircraft.setCompany(company);

        aircraftDAO.createAircraft(aircraft);

        for (int i = 0; i < 100; i++) {
            Flight flight = new Flight();
            flight.setDepartureDate(DateUtil.getDateFromString(DateUtil.DEFAULT_DATE_TIME_PATTERN, DEPARTURE_DATE_VALUE));
            flight.setArrivalDate(DateUtil.getDateFromString(DateUtil.DEFAULT_DATE_TIME_PATTERN, ARRIVAL_DATE_VALUE));
            flight.setDepartureCity(deptCity);
            flight.setArrivalCity(arrivalCity);
            flight.setFirstClassPrice(500);
            flight.setSecondClassPrice(400);
            flight.setAircraft(aircraft);
            flightDAO.createFlight(flight);
        }
    }

    private void deleteMockList(List<Flight> mockList) {
        Flight mockFlight = mockList.get(0);

        for (Flight flight : mockList) {
            flightDAO.deleteFlight(flight.getId());
        }

        aircraftDAO.deleteAircraft(mockFlight.getAircraft().getId());
        companyDAO.deleteCompany(mockFlight.getAircraft().getCompany().getId());
        cityDAO.deleteCity(mockFlight.getDepartureCity().getId());
        cityDAO.deleteCity(mockFlight.getArrivalCity().getId());
    }

    private void deleteRoutMockList(List<Flight> mockList) {
        Set<City> citySet = new HashSet<>();
        Set<Company> companySet = new HashSet<>();
        Set<Aircraft> aircraftSet = new HashSet<>();

        for (Flight flight : mockList) {
            citySet.add(flight.getDepartureCity());
            citySet.add(flight.getArrivalCity());
            aircraftSet.add(flight.getAircraft());
            companySet.add(flight.getAircraft().getCompany());

            flightDAO.deleteFlight(flight.getId());
        }

        aircraftDAO.deleteAircraftBatch(Arrays.asList(aircraftSet.toArray(new Aircraft[]{})));
        cityDAO.deleteCityBatch(Arrays.asList(citySet.toArray(new City[]{})));
        companyDAO.deleteCompanyBatch(Arrays.asList(companySet.toArray(new Company[]{})));
    }

    private List<Flight> initMockFlightsRout() throws ParseException {
        List<Flight> flights = new ArrayList<>();

        Company lotCompany = new Company();
        lotCompany.setName(COMPANY_LOT);
        companyDAO.createCompany(lotCompany);

        Company lhCompany = new Company();
        lhCompany.setName(COMPANY_LH);
        companyDAO.createCompany(lhCompany);

        Company ausAirCompany = new Company();
        ausAirCompany.setName(COMPANY_AUS_AIR);
        companyDAO.createCompany(ausAirCompany);

        City waw = new City();
        waw.setName(CITY_WAW);
        cityDAO.createCity(waw);

        City evn = new City();
        evn.setName(CITY_EVN);
        cityDAO.createCity(evn);

        City br = new City();
        br.setName(CITY_BR);
        cityDAO.createCity(br);

        City mos = new City();
        mos.setName(CITY_MOS);
        cityDAO.createCity(mos);

        City ke = new City();
        ke.setName(CITY_KE);
        cityDAO.createCity(ke);

        City vn = new City();
        vn.setName(CITY_VN);
        cityDAO.createCity(vn);

        City ph = new City();
        ph.setName(CITY_PH);
        cityDAO.createCity(ph);

        Aircraft aircraftLot = new Aircraft();
        aircraftLot.setCompany(lotCompany);
        aircraftLot.setModel("Airbas 308");
        aircraftLot.setFirstClassCount(40);
        aircraftLot.setSecondClassCount(360);
        aircraftDAO.createAircraft(aircraftLot);

        Aircraft aircraftLH = new Aircraft();
        aircraftLH.setCompany(lhCompany);
        aircraftLH.setModel("Airbas 308");
        aircraftLH.setFirstClassCount(40);
        aircraftLH.setSecondClassCount(360);
        aircraftDAO.createAircraft(aircraftLH);

        Aircraft aircraftAus = new Aircraft();
        aircraftAus.setCompany(ausAirCompany);
        aircraftAus.setModel("Airbas 308");
        aircraftAus.setFirstClassCount(40);
        aircraftAus.setSecondClassCount(360);
        aircraftDAO.createAircraft(aircraftAus);

        flights.add(initFlight("01-06-2016 10:45", "01-06-2016 12:10", waw, vn, aircraftLot));
        flights.add(initFlight("01-06-2016 13:45", "01-06-2016 15:30", waw, ph, aircraftLot));
        flights.add(initFlight("01-06-2016 14:45", "01-06-2016 17:50", waw, ke, aircraftLot));

        flights.add(initFlight("01-06-2016 14:45", "01-06-2016 17:50", vn, evn, aircraftLot));
        flights.add(initFlight("01-06-2016 22:00", "02-06-2016 01:50", ke, evn, aircraftLot));
        flights.add(initFlight("01-06-2016 15:55", "01-06-2016 19:00", ph, evn, aircraftLot));


        flights.add(initFlight("01-06-2016 17:00", "01-06-2016 19:00", vn, mos, aircraftLot));
        flights.add(initFlight("01-06-2016 17:00", "01-06-2016 19:00", br, mos, aircraftLot));
        flights.add(initFlight("01-06-2016 20:00", "01-06-2016 22:45", mos, evn, aircraftLot));

        flights.add(initFlight("01-06-2016 20:00", "01-06-2016 22:45", mos, evn, aircraftLH));
        flights.add(initFlight("01-06-2016 20:00", "01-06-2016 22:45", mos, evn, aircraftAus));

        return flights;
    }

    private Flight initFlight(String deptDate, String arrDate, City deptCity, City arrCity, Aircraft air) throws ParseException {
        Flight flight = new Flight();
        flight.setDepartureDate(DateUtil.getDateFromString(deptDate));
        flight.setArrivalDate(DateUtil.getDateFromString(arrDate));
        flight.setDepartureCity(deptCity);
        flight.setArrivalCity(arrCity);
        flight.setFirstClassPrice(500);
        flight.setSecondClassPrice(400);
        flight.setAircraft(air);
        flightDAO.createFlight(flight);

        return flight;
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
