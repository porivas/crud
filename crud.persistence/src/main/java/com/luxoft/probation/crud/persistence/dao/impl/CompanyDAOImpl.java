package com.luxoft.probation.crud.persistence.dao.impl;

import com.luxoft.probation.crud.core.domain.Company;
import com.luxoft.probation.crud.persistence.dao.CompanyDAO;
import com.luxoft.probation.crud.persistence.dao.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Company DAO implementation
 * <p>
 * Created by HHayryan on 5/19/2016.
 */
public class CompanyDAOImpl implements CompanyDAO {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    @Transactional
    public void createCompany(Company company) throws DuplicateKeyException {
        companyMapper.createCompany(company);
    }

    @Override
    @Transactional
    public void createCompanyBatch(List<Company> companyList) {
        companyMapper.createCompanyBatch(companyList);
    }

    @Override
    public Company getCompanyById(int id) {
        return companyMapper.getCompanyById(id);
    }

    @Override
    public Company getCompanyByName(String name) {
        return companyMapper.getCompanyByName(name);
    }

    @Override
    public void updateCompany(Company company) {
        companyMapper.updateCompany(company);
    }

    @Override
    public void deleteCompany(int id) {
        companyMapper.deleteCompany(id);
    }

    @Override
    public void deleteCompanyByName(String name) {
        companyMapper.deleteCompanyByName(name);
    }

    @Override
    public void deleteCompanyBatch(List<Company> companyList) {
        companyMapper.deleteCompanyBatch(companyList);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyMapper.getAllCompanies();
    }
}
