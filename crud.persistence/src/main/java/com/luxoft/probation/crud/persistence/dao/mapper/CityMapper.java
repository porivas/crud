package com.luxoft.probation.crud.persistence.dao.mapper;

import com.luxoft.probation.crud.core.domain.City;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * City entity mapper
 * <p>
 * Created by HHayryan on 5/19/2016.
 */
public interface CityMapper {

    @Insert("INSERT INTO cities(id, name, country_id) VALUES (#{id}, #{name}, #{countryId})")
    @Options(useGeneratedKeys = true, flushCache = Options.FlushCachePolicy.TRUE, keyColumn = "id")
    void createCity(City city);

    @Insert({
            "<script>",
            "REPLACE INTO cities (id, name, country_id)",
            "VALUES ",
            "<foreach  collection='cityList' item='city' separator=','>",
            "( #{city.id,jdbcType=INTEGER}, #{city.name,jdbcType=VARCHAR}, #{city.countryId,jdbcType=INTEGER})",
            "</foreach>",
            "</script>"})
    void createCityBatch(@Param("cityList") List<City> cityList);

    @Select("SELECT * FROM cities WHERE id = #{id}")
    City getCityById(int id);

    @Select("SELECT * FROM cities WHERE name = #{name}")
    City getCityByName(String name);

    @Select("SELECT * FROM cities WHERE country_id = #{countryId}")
    List<City> getAllCitiesByCountryId(int countryId);

    @Update("UPDATE cities SET name = #{name} WHERE id = #{id}")
    void updateCity(City city);

    @Delete("DELETE FROM cities WHERE id = #{id}")
    void deleteCity(int id);

    @Delete("DELETE FROM cities WHERE name = #{name}")
    void deleteCityByName(String name);

    @Delete({
            "<script>",
            "DELETE FROM cities",
            "WHERE id IN ",
            "<foreach collection='cityList' item='city' open='(' close=')' separator=','>",
            "#{city.id}",
            "</foreach>",
            "</script>"})
    void deleteCityBatch(@Param("cityList") List<City> cityList);

    @Select("SELECT * FROM cities")
    List<City> getAllCities();
}
