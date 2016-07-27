package com.luxoft.probation.crud.persistence.dao.mapper;

import com.luxoft.probation.crud.core.domain.Aircraft;
import com.luxoft.probation.crud.core.domain.Company;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Aircraft entity mapper
 * <p>
 * Created by HHayryan on 5/19/2016.
 */
public interface AircraftMapper {

    @Insert("INSERT INTO aircrafts(id, model, count_1_class, count_2_class, company_id)"
            + "VALUES(#{id}, #{model}, #{firstClassCount}, #{secondClassCount}, #{company.id})")
    @Options(useGeneratedKeys = true, flushCache = Options.FlushCachePolicy.TRUE, keyColumn = "id")
    void createAircraft(Aircraft aircraft);

    @Insert({"<script>",
            "REPLACE INTO aircrafts(id, model, count_1_class, count_2_class, company_id)",
            "VALUES",
            "<foreach  collection='aircraftList' item='aircraft' separator=','>",
            "( #{aircraft.id,jdbcType=INTEGER}, #{aircraft.model,jdbcType=VARCHAR},"
                    + "#{aircraft.firstClassCount,jdbcType=INTEGER}, #{aircraft.secondClassCount,jdbcType=INTEGER},"
                    + " #{aircraft.company.id,jdbcType=INTEGER})",
            "</foreach>",
            "</script>"})
    void createAircraftBatch(@Param("aircraftList") List<Aircraft> aircraftList);

    @Select("SELECT id, model, count_1_class, count_2_class, company_id FROM aircrafts")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "company", column = "company_id", javaType = Company.class, one = @One(select = "getCompanyById"))
    })
    List<Aircraft> getAllAircrafts();

    @Select("SELECT id, model, count_1_class, count_2_class, company_id FROM aircrafts"
            + " WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "company", column = "company_id", javaType = Company.class, one = @One(select = "getCompanyById"))
    })
    Aircraft getAircraftById(int id);

    @Select("SELECT id, model, count_1_class, count_2_class, company_id FROM aircrafts"
            + " WHERE model = #{model}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "company", column = "company_id", javaType = Company.class, one = @One(select = "getCompanyById"))
    })
    List<Aircraft> getAircraftListByModel(String model);

    @Select("SELECT id, model, count_1_class, count_2_class, company_id FROM aircrafts"
            + " WHERE company_id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "company", column = "company_id", javaType = Company.class, one = @One(select = "getCompanyById"))
    })
    List<Aircraft> getAircraftListByCompanyId(int id);

    @Update("UPDATE aircrafts SET model = #{model}, count_1_class = #{firstClassCount},"
            + " count_2_class = #{secondClassCount}, company_id = #{company.id} WHERE id=#{id}")
    void updateAircraft(Aircraft aircraft);

    @Delete("DELETE FROM aircrafts WHERE id = #{id}")
    void deleteAircraft(int id);

    @Delete({"<script>",
            "DELETE FROM aircrafts",
            "WHERE id IN",
            "<foreach collection='aircraftList' item='aircraft' open='(' close=')' separator=','>",
            "#{aircraft.id}",
            "</foreach>",
            "</script>"})
    void deleteAircraftBatch(@Param("aircraftList") List<Aircraft> aircraftList);

    @Select("SELECT id, name FROM companies WHERE id = #{id}")
    Company getCompanyById(int id);
}
