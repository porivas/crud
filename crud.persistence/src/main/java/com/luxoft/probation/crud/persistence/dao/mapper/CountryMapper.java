package com.luxoft.probation.crud.persistence.dao.mapper;

import com.luxoft.probation.crud.core.domain.City;
import com.luxoft.probation.crud.core.domain.Country;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Country domain mapper
 * <p>
 * Created by hhayryan on 5/30/2016.
 */
public interface CountryMapper {

    @Insert("INSERT INTO countries(id, name) VALUES (#{id}, #{name})")
    @Options(useGeneratedKeys = true, flushCache = Options.FlushCachePolicy.TRUE, keyColumn = "id")
    void createCountry(Country country);

    @Insert({
            "<script>",
            "REPLACE INTO countries (id, name)",
            "VALUES ",
            "<foreach  collection='countriesList' item='country' separator=','>",
            "( #{country.id,jdbcType=INTEGER}, #{country.name,jdbcType=VARCHAR})",
            "</foreach>",
            "</script>"})
    void createCountryBatch(@Param("countriesList") List<Country> countriesList);

    @Select("SELECT * FROM countries WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "cities", column = "id", javaType = List.class, many = @Many(select = "getAllCitiesByCountryId"))
    })
    Country getCountryById(int id);

    @Select("SELECT * FROM countries WHERE name LIKE #{name}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "cities", column = "id", javaType = List.class, many = @Many(select = "getAllCitiesByCountryId"))
    })
    List<Country> findCountryByName(String name);

    @Update("UPDATE countries SET name = #{name} WHERE id = #{id}")
    void updateCountry(Country country);

    @Delete("DELETE FROM countries WHERE id = #{id}")
    void deleteCountry(int id);

    @Delete("DELETE FROM countries WHERE name = #{name}")
    void deleteCountryByName(String name);

    @Delete({
            "<script>",
            "DELETE FROM countries",
            "WHERE id IN ",
            "<foreach collection='countriesList' item='country' open='(' close=')' separator=','>",
            "#{country.id}",
            "</foreach>",
            "</script>"})
    void deleteCountryBatch(@Param("countriesList") List<Country> countriesList);

    @Select("SELECT * FROM countries")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "cities", column = "id", javaType = List.class, many = @Many(select = "getAllCitiesByCountryId"))
    })
    List<Country> getAllCountries();

    @Select("SELECT * FROM countries LIMIT #{from}, #{to}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "cities", column = "id", javaType = List.class, many = @Many(select = "getAllCitiesByCountryId"))
    })
    List<Country> getCountriesByPage(int from, int to);

    @Select("SELECT * FROM cities WHERE country_id = #{countryId}")
    List<City> getAllCitiesByCountryId(int countryId);
}
