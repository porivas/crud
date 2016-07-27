package com.luxoft.probation.crud.persistence.dao.mapper;

import com.luxoft.probation.crud.core.domain.Company;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Company entity mapper
 * <p>
 * Created by HHayryan on 5/19/2016.
 */
public interface CompanyMapper {
    @Insert("INSERT INTO companies(id, name) VALUES (#{id}, #{name})")
    @Options(useGeneratedKeys = true, flushCache = Options.FlushCachePolicy.TRUE, keyColumn = "id")
    void createCompany(Company company);

    @Insert({
            "<script>",
            "REPLACE INTO companies (id, name)",
            "VALUES ",
            "<foreach  collection='companyList' item='company' separator=','>",
            "( #{company.id,jdbcType=INTEGER}, #{company.name,jdbcType=VARCHAR})",
            "</foreach>",
            "</script>"})
    void createCompanyBatch(@Param("companyList") List<Company> companyList);

    @Select("SELECT * FROM companies WHERE id = #{id}")
    Company getCompanyById(int id);

    @Select("SELECT * FROM companies WHERE name = #{name}")
    Company getCompanyByName(String name);

    @Update("UPDATE companies SET name = #{name} WHERE id = #{id}")
    void updateCompany(Company company);

    @Delete("DELETE FROM companies WHERE id = #{id}")
    void deleteCompany(int id);

    @Delete("DELETE FROM companies WHERE name = #{name}")
    void deleteCompanyByName(String name);

    @Delete({
            "<script>",
            "DELETE FROM companies",
            "WHERE id IN ",
            "<foreach collection='companyList' item='company' open='(' close=')' separator=','>",
            "#{company.id}",
            "</foreach>",
            "</script>"})
    void deleteCompanyBatch(@Param("companyList") List<Company> companyList);

    @Select("SELECT * FROM companies")
    List<Company> getAllCompanies();
}
