package com.luxoft.probation.crud.persistence.test;

import com.luxoft.probation.crud.core.domain.Aircraft;
import com.luxoft.probation.crud.core.domain.Company;
import com.luxoft.probation.crud.persistence.dao.AircraftDAO;
import com.luxoft.probation.crud.persistence.dao.CompanyDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Aircraft DAO implementation unit tests
 * <p>
 * Created by HHayryan on 5/20/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/dao-test.xml")
public class AircraftDAOTestDAO {
    private static final Logger LOG = LoggerFactory.getLogger(AircraftDAOTestDAO.class);

    private static final String AIRCRAFT_MODEL = "Airbas 306";
    private static final String COMPANY_NAME = "Armenian Airlines";
    private static final int FIRST_CLASS_COUNT = 24;
    private static final int SECOND_CLASS_COUNT = 120;

    @Autowired
    AircraftDAO aircraftDAO;

    @Autowired
    CompanyDAO companyDAO;

    @Test
    public void createAircraftTest() {
        LOG.info("Start createAircraftTest");
        Aircraft aircraft = initMockDomain();
        aircraftDAO.createAircraft(aircraft);

        Aircraft selectedAircraft = aircraftDAO.getAircraftById(aircraft.getId());
        assertNotNull("Aircraft domain instance should not be null", selectedAircraft);

        assertEquals("Aircraft ID should be equals", aircraft.getId(), selectedAircraft.getId());

        LOG.info("Aircraft domain created successfully");

        cleanMock(selectedAircraft);
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void createAircraftBatchTest() {
        LOG.info("Start createAircraftTest");
        List<Aircraft> aircraftList = initListOfMockDomains();
        aircraftDAO.createAircraftBatch(aircraftList);

        List<Aircraft> selectedAircraftList = aircraftDAO.getAllAircrafts();

        assertNotNull("Selected list of Aircraft domain should be initialized", selectedAircraftList);

        assertEquals("Size of selected items should be equals", aircraftList.size(), selectedAircraftList.size());

        LOG.info("List of Aircarft domain created in batch mode successfully");

        cleanMockList(selectedAircraftList);
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void getAircraftByIdTest() {
        LOG.info("Start getAircraftByIdTest");
        Aircraft aircraft = initMockDomain();
        aircraftDAO.createAircraft(aircraft);

        Aircraft selectedAircraft = aircraftDAO.getAircraftById(aircraft.getId());
        assertNotNull("Aircraft domain instance should be initialized", selectedAircraft);

        assertEquals("Aircraft domain id should be equals", selectedAircraft.getId(), aircraft.getId());
        assertEquals("Aircraft model should be equals", selectedAircraft.getModel(), aircraft.getModel());

        LOG.info("Aircraft domain result, selected by ID successfully");

        cleanMock(selectedAircraft);
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void getAircraftListByModelTest() {
        LOG.info("Start getAircraftByIdTest");
        List<Aircraft> aircraftList = initListOfMockDomains();
        aircraftDAO.createAircraftBatch(aircraftList);

        List<Aircraft> selectedAircraftsList = aircraftDAO.getAircraftListByModel(AIRCRAFT_MODEL);

        assertNotNull("Selected aircraft domains list should be initialized", selectedAircraftsList);

        assertEquals("Selected aircraft domains list size should be equals", aircraftList.size(), selectedAircraftsList.size());

        for (Aircraft domain : selectedAircraftsList) {
            assertEquals("Selected aircraft domain's model should be equals", AIRCRAFT_MODEL, domain.getModel());
        }

        LOG.info("getAircraftListByModelTest successfully complete");

        cleanMockList(selectedAircraftsList);
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void getAircraftByCompanyIdTest() {
        LOG.info("Start getAircraftByCompanyIdTest");
        List<Aircraft> aircraftList = initListOfMockDomains();

        aircraftDAO.createAircraftBatch(aircraftList);

        int aircraftCompanyId = aircraftList.get(0).getCompany().getId();

        List<Aircraft> selectedAircraftList = aircraftDAO.getAircraftListByCompanyId(aircraftList.get(0).getCompany().getId());

        assertNotNull("Selected aircrafts domain list should be initialized", selectedAircraftList);

        assertEquals("Selected aircraft domain list size should be equals", aircraftList.size(), selectedAircraftList.size());

        for (Aircraft domain : selectedAircraftList) {
            assertEquals("Selected aircraft company ID should be equals", aircraftCompanyId, domain.getCompany().getId());
        }

        LOG.info("getAircraftByCompanyIdTest successfully complete");

        cleanMockList(selectedAircraftList);
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void updateAircraftTest() {
        LOG.info("Stert updateAircraft");
        String aircraftModel = "Boing 738";
        Aircraft aircraft = initMockDomain();
        aircraftDAO.createAircraft(aircraft);

        aircraft.setModel(aircraftModel);
        aircraftDAO.updateAircraft(aircraft);

        Aircraft selectedAircraft = aircraftDAO.getAircraftById(aircraft.getId());

        assertNotNull("Selected aircraft domain should be initialized", selectedAircraft);

        assertEquals("Selected aircraft model should be equals", aircraftModel, selectedAircraft.getModel());

        LOG.info("updateAircraftTest successfully complete");

        cleanMock(selectedAircraft);
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void deleteAircraftTest() {
        LOG.info("Start deleteAircraftTest");
        Aircraft aircraft = initMockDomain();
        aircraftDAO.createAircraft(aircraft);

        int aircraftId = aircraft.getId();
        int aircraftCompanyId = aircraft.getCompany().getId();

        aircraftDAO.deleteAircraft(aircraftId);

        Aircraft selectedAircraft = aircraftDAO.getAircraftById(aircraft.getId());

        assertNull("Selected aircraft should be null", selectedAircraft);

        LOG.info("deleteAircraftTest successfully complete");

        companyDAO.deleteCompany(aircraftCompanyId);
        LOG.info("Generated data during test successfully cleaned");
    }

    private Aircraft initMockDomain() {
        Aircraft aircraft = new Aircraft();
        aircraft.setModel(AIRCRAFT_MODEL);
        aircraft.setFirstClassCount(FIRST_CLASS_COUNT);
        aircraft.setSecondClassCount(SECOND_CLASS_COUNT);

        Company company = new Company();
        company.setName(COMPANY_NAME);
        companyDAO.createCompany(company);

        aircraft.setCompany(company);

        return aircraft;
    }

    private List<Aircraft> initListOfMockDomains() {
        List<Aircraft> aircraftList = new ArrayList<>(10);
        Company company = new Company();
        company.setName(COMPANY_NAME);
        companyDAO.createCompany(company);
        for (int i = 0; i < 10; i++) {
            Aircraft aircraft = new Aircraft();
            aircraft.setModel(AIRCRAFT_MODEL);
            aircraft.setFirstClassCount(FIRST_CLASS_COUNT);
            aircraft.setSecondClassCount(SECOND_CLASS_COUNT);
            aircraft.setCompany(company);
            aircraftList.add(aircraft);
        }

        return aircraftList;
    }

    private void cleanMock(Aircraft aircraft) {
        aircraftDAO.deleteAircraft(aircraft.getId());
        companyDAO.deleteCompany(aircraft.getCompany().getId());
    }

    private void cleanMockList(List<Aircraft> aircraftList) {
        aircraftDAO.deleteAircraftBatch(aircraftList);
        companyDAO.deleteCompany(aircraftList.get(0).getCompany().getId());
    }
}
