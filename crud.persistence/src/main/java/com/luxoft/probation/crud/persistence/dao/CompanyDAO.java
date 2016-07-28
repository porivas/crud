package com.luxoft.probation.crud.persistence.dao;

import com.luxoft.probation.crud.core.domain.Company;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Company DAO
 * <p>
 * Created by HHayryan on 5/19/2016.
 */
@Component
public interface CompanyDAO {
    void createCompany(Company company) throws DuplicateKeyException;

    void createCompanyBatch(List<Company> companyList);

    Company getCompanyById(int id);

    Company getCompanyByName(String name);

    void updateCompany(Company company);

    void deleteCompany(int id);

    void deleteCompanyByName(String name);

    void deleteCompanyBatch(List<Company> companyList);

    List<Company> getAllCompanies();
}
