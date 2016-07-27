package com.luxoft.probation.crud.persistence.test;

import com.luxoft.probation.crud.core.domain.Company;
import com.luxoft.probation.crud.persistence.dao.CompanyDAO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Company DAO implementation's unit tests
 * <p>
 * Created by HHayryan on 5/19/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/dao-test.xml")
public class CompanyDAOTestDAO {

    private static final Logger LOG = LoggerFactory.getLogger(CompanyDAOTestDAO.class);

    @Autowired
    CompanyDAO companyDAO;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void createCompanyTest() {
        LOG.info("Start createCompanyTest");
        Company company = new Company();
        company.setName(generateCompanyRandomName());
        companyDAO.createCompany(company);

        Company selectedCompany = companyDAO.getCompanyByName(company.getName());

        assertNotNull("Company domain instance should not be null", selectedCompany);

        assertEquals("Company name should be equals", company.getName(), selectedCompany.getName());

        LOG.info("Company domain created successfully");

        companyDAO.deleteCompany(company.getId());
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void createCompanyBatchTest() {
        LOG.info("Start createCompanyBatchTest");
        List<Company> companyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Company company = new Company();
            company.setName(generateCompanyRandomName());
            companyList.add(company);
        }

        companyDAO.createCompanyBatch(companyList);

        List<Company> selectedCompanyList = companyDAO.getAllCompanies();

        assertNotNull("Selected list of Company domain should be initialized", selectedCompanyList);

        assertEquals("Size of selected items should be equals", selectedCompanyList.size(), companyList.size());

        LOG.info("List of Company domain created in batch mode successfully");

        companyDAO.deleteCompanyBatch(selectedCompanyList);
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void createDuplicateCompanyTest() {
        LOG.info("Start createDuplicateCompanyTest");
        Company company = new Company();
        company.setName(generateCompanyRandomName());

        companyDAO.createCompany(company);

        try {
            exception.expect(DuplicateKeyException.class);
            companyDAO.createCompany(company);
        } finally {
            companyDAO.deleteCompany(company.getId());
            LOG.info("Generated data during test successfully cleaned");
        }
    }

    @Test
    public void getCompanyByIdTest() {
        LOG.info("Start getCompanyByIdTest");
        Company company = new Company();
        company.setName(generateCompanyRandomName());
        companyDAO.createCompany(company);

        Company selectedCompany = companyDAO.getCompanyById(company.getId());

        assertNotNull("Company domain instance should be initialized", selectedCompany);

        assertEquals("Company domain id should be equals", company.getId(), selectedCompany.getId());
        assertEquals("Company name should be equals", company.getName(), selectedCompany.getName());

        LOG.info("Company domain result, selected by ID successfully");

        companyDAO.deleteCompany(company.getId());
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void getCompanyByName() {
        LOG.info("Start getCompanyByName");
        Company company = new Company();
        company.setName(generateCompanyRandomName());
        companyDAO.createCompany(company);

        Company selectedCompany = companyDAO.getCompanyByName(company.getName());

        assertNotNull("Company domain instance should be initialized", selectedCompany);
        assertEquals("Company name should be equals", company.getName(), selectedCompany.getName());
        LOG.info("Company domain result, selected by Name successfully");

        companyDAO.deleteCompany(company.getId());
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void updateCompanyTest() {
        LOG.info("Start updateCompanyTest");
        Company company = new Company();
        company.setName(generateCompanyRandomName());

        companyDAO.createCompany(company);

        company.setName(generateCompanyRandomName());
        companyDAO.updateCompany(company);

        Company selectedCompany = companyDAO.getCompanyById(company.getId());

        assertNotNull("Company domain instance should be initialized", selectedCompany);

        assertEquals("Company name should be equals", company.getName(), selectedCompany.getName());

        LOG.info("Company name successfully updated");

        companyDAO.deleteCompany(company.getId());
        LOG.info("Generated data during test successfully cleaned");
    }

    @Test
    public void deleteCompanyTest() {
        LOG.info("Start deleteCompanyTest");
        Company company = new Company();
        company.setName(generateCompanyRandomName());
        companyDAO.createCompany(company);

        companyDAO.deleteCompany(company.getId());

        Company selectedCompany = companyDAO.getCompanyById(company.getId());

        assertNull("Company with provided ID should not exist", selectedCompany);

        LOG.info("Company with provided ID: {}, successfully deleted", company.getId());
    }

    @Test
    public void deleteCompanyByNameTest() {
        LOG.info("Start deleteCompanyByNameTest");
        Company company = new Company();
        company.setName(generateCompanyRandomName());
        companyDAO.createCompany(company);

        companyDAO.deleteCompanyByName(company.getName());

        Company selectedCompany = companyDAO.getCompanyByName(company.getName());

        assertNull("Company with provided ID should not exist", selectedCompany);

        LOG.info("Company with provided ID: {}, successfully deleted", company.getId());
    }

    private String generateCompanyRandomName() {
        return String.format("Company_%s", UUID.randomUUID().toString().substring(0, 9));
    }
}
