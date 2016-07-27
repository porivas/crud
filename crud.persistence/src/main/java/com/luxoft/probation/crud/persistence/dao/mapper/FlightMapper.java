package com.luxoft.probation.crud.persistence.dao.mapper;

import com.luxoft.probation.crud.core.domain.Aircraft;
import com.luxoft.probation.crud.core.domain.City;
import com.luxoft.probation.crud.core.domain.Company;
import com.luxoft.probation.crud.core.domain.Flight;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * Flight entity mapper
 * <p>
 * Created by HHayryan on 5/23/2016.
 */
public interface FlightMapper {

    @Insert("INSERT INTO flights(id, date_departure, date_arrival, price_1_class, price_2_class, aircraft_id, "
            + "id_departure_city, id_arrival_city) VALUES(#{id}, #{departureDate}, #{arrivalDate}, "
            + "#{firstClassPrice}, #{secondClassPrice}, #{aircraft.id}, #{departureCity.id}, #{arrivalCity.id})")
    @Options(useGeneratedKeys = true, flushCache = Options.FlushCachePolicy.TRUE, keyColumn = "id")
    void createFlight(Flight flight);

    @Delete("DELETE FROM flights WHERE id = #{id}")
    void deleteFlight(int id);

    @Update("UPDATE flights SET date_departure = #{departureDate}, date_arrival = #{arrivalDate},"
            + " price_1_class = #{firstClassPrice}, price_2_class = #{secondClassPrice},"
            + " aircraft_id = #{aircraft.id}, id_departure_city = #{departureCity.id}, id_arrival_city = #{arrivalCity.id} WHERE id=#{id}")
    void updateFlight(Flight flight);

    @Select("SELECT * FROM flights WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "departureDate", column = "date_departure", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "arrivalDate", column = "date_arrival", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "firstClassPrice", column = "price_1_class", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "secondClassPrice", column = "price_2_class", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "aircraft", column = "aircraft_id", javaType = Aircraft.class, one = @One(select = "getAircraftById")),
            @Result(property = "departureCity", column = "id_departure_city", javaType = City.class, one = @One(select = "getCityById")),
            @Result(property = "arrivalCity", column = "id_arrival_city", javaType = City.class, one = @One(select = "getCityById"))
    })
    Flight getFlightById(int id);

    /**
     * Retrieve flights based on paging approaches
     *
     * @param start - query result start row index.
     * @param count - query result row count beginning from start row index
     * @return List<Flight> - return paginated list of Flight domain
     */
    @Select("SELECT * FROM flights LIMIT #{start}, #{count}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "departureDate", column = "date_departure", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "arrivalDate", column = "date_arrival", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "firstClassPrice", column = "price_1_class", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "secondClassPrice", column = "price_2_class", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "aircraft", column = "aircraft_id", javaType = Aircraft.class, one = @One(select = "getAircraftById")),
            @Result(property = "departureCity", column = "id_departure_city", javaType = City.class, one = @One(select = "getCityById")),
            @Result(property = "arrivalCity", column = "id_arrival_city", javaType = City.class, one = @One(select = "getCityById"))
    })
    List<Flight> getAllFlightsPagination(@Param("start") int start, @Param("count") int count);

    @Select("SELECT * FROM flights WHERE date_departure BETWEEN #{from} AND #{to}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "departureDate", column = "date_departure", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "arrivalDate", column = "date_arrival", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "firstClassPrice", column = "price_1_class", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "secondClassPrice", column = "price_2_class", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "aircraft", column = "aircraft_id", javaType = Aircraft.class, one = @One(select = "getAircraftById")),
            @Result(property = "departureCity", column = "id_departure_city", javaType = City.class, one = @One(select = "getCityById")),
            @Result(property = "arrivalCity", column = "id_arrival_city", javaType = City.class, one = @One(select = "getCityById"))
    })
    List<Flight> getFlightsByDepartureDatePeriod(@Param("from") Date from, @Param("to") Date to);

    @Select("SELECT * FROM flights WHERE date_departure >= #{departureDate} AND date_departure <= #{endDate}"
            + " AND id_departure_city = #{departureCityId} AND id_arrival_city = #{arrivalCityId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "departureDate", column = "date_departure", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "arrivalDate", column = "date_arrival", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "firstClassPrice", column = "price_1_class", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "secondClassPrice", column = "price_2_class", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "aircraft", column = "aircraft_id", javaType = Aircraft.class, one = @One(select = "getAircraftById")),
            @Result(property = "departureCity", column = "id_departure_city", javaType = City.class, one = @One(select = "getCityById")),
            @Result(property = "arrivalCity", column = "id_arrival_city", javaType = City.class, one = @One(select = "getCityById"))
    })
    List<Flight> getFlightsByDeptDateAndFromToCities(@Param("departureDate") Date departureDate,
                                                     @Param("endDate") Date endDate,
                                                     @Param("departureCityId") int departureCityId,
                                                     @Param("arrivalCityId") int arrivalCityId);


    @Select("SELECT * FROM flights WHERE date_departure >= #{flightDate} AND date_departure <= #{endDate}"
            + " AND id_departure_city = #{departureCityId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "departureDate", column = "date_departure", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "arrivalDate", column = "date_arrival", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "firstClassPrice", column = "price_1_class", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "secondClassPrice", column = "price_2_class", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "aircraft", column = "aircraft_id", javaType = Aircraft.class, one = @One(select = "getAircraftById")),
            @Result(property = "departureCity", column = "id_departure_city", javaType = City.class, one = @One(select = "getCityById")),
            @Result(property = "arrivalCity", column = "id_arrival_city", javaType = City.class, one = @One(select = "getCityById"))
    })
    List<Flight> getFlightByDeptDateAndDeptCity(@Param("flightDate") Date flightDate,
                                                @Param("endDate") Date endDate,
                                                @Param("departureCityId") int departureCityId);

    @Select("SELECT * FROM flights WHERE date_departure >= #{flightDate} AND date_departure <= #{endDate}"
            + " AND id_arrival_city = #{arrivalCityId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "departureDate", column = "date_departure", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "arrivalDate", column = "date_arrival", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "firstClassPrice", column = "price_1_class", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "secondClassPrice", column = "price_2_class", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "aircraft", column = "aircraft_id", javaType = Aircraft.class, one = @One(select = "getAircraftById")),
            @Result(property = "departureCity", column = "id_departure_city", javaType = City.class, one = @One(select = "getCityById")),
            @Result(property = "arrivalCity", column = "id_arrival_city", javaType = City.class, one = @One(select = "getCityById"))
    })
    List<Flight> getFlightByArrivalDateAndArrivalCity(@Param("flightDate") Date flightDate,
                                                      @Param("endDate") Date endDate,
                                                      @Param("arrivalCityId") int arrivalCityId);

    @Select("SELECT COUNT(id) FROM flights")
    int getAllFlightsCount();

    @Select("Select * from aircrafts WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "company", column = "company_id", javaType = Company.class, one = @One(select = "getCompanyById")),
    })
    Aircraft getAircraftById(int id);

    @Select("SELECT id, name FROM companies WHERE id = #{id}")
    Company getCompanyById(int id);

    @Select("SELECT * FROM cities WHERE id = #{id}")
    City getCityById(int id);
}

