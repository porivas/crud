package com.luxoft.probation.crud.persistence.dao.mapper;

import com.luxoft.probation.crud.core.domain.Aircraft;
import com.luxoft.probation.crud.core.domain.City;
import com.luxoft.probation.crud.core.domain.Company;
import com.luxoft.probation.crud.core.domain.Flight;
import com.luxoft.probation.crud.core.domain.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * Order entity mapper
 * <p>
 * Created by hhayryan on 5/27/2016.
 */
public interface OrderMaper {

    @Insert("INSERT INTO orders(id, board_class, count, credit_card, flight_id, order_id, order_date)"
            + " VALUES(#{id}, #{boardClass}, #{count}, #{creditCard}, #{flight.id}, #{orderId}, #{orderDate})")
    void createOrder(Order order);

    @Update("UPDATE orders SET(board_class=#{boardClass}, count=#{count}, credit_card=#{creditCard},"
            + " flight_id=#{flight.id}, order_id=#{orderId}, order_date=#{orderDate} WHERE id=#{id})")
    void updateOrder(Order order);

    @Delete("DELETE FROM orders WHERE id=#{id}")
    void deleteOrder(int id);

    @Select("SELECT * FROM orders WHERE id=#{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "boardClass", column = "board_class"),
            @Result(property = "count", column = "count"),
            @Result(property = "creditCard", column = "credit_card"),
            @Result(property = "flight", column = "flight_id", javaType = Flight.class, one = @One(select = "getFlightById")),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "orderDate", column = "order_date"),
    })
    Order getOrderById(int id);

    @Select("SELECT * FROM orders WHERE order_id = #{orderId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "boardClass", column = "board_class"),
            @Result(property = "count", column = "count"),
            @Result(property = "creditCard", column = "credit_card"),
            @Result(property = "flight", column = "flight_id", javaType = Flight.class, one = @One(select = "getFlightById")),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "orderDate", column = "order_date"),
    })
    Order getOrderByOrderId(String orderId);

    @Select("SELECT * FROM orders WHERE board_class = #{boardClass}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "boardClass", column = "board_class"),
            @Result(property = "count", column = "count"),
            @Result(property = "creditCard", column = "credit_card"),
            @Result(property = "flight", column = "flight_id", javaType = Flight.class, one = @One(select = "getFlightById")),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "orderDate", column = "order_date"),
    })
    List<Order> getOrdersByBoardClass(int boardClass);

    @Select("SELECT * FROM orders WHERE flight_id = #{flightId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "boardClass", column = "board_class"),
            @Result(property = "count", column = "count"),
            @Result(property = "creditCard", column = "credit_card"),
            @Result(property = "flight", column = "flight_id", javaType = Flight.class, one = @One(select = "getFlightById")),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "orderDate", column = "order_date"),
    })
    List<Order> getOrdersByFlightId(int flightId);

    @Select("SELECT * FROM orders WHERE order_date = #{orderDate}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "boardClass", column = "board_class"),
            @Result(property = "count", column = "count"),
            @Result(property = "creditCard", column = "credit_card"),
            @Result(property = "flight", column = "flight_id", javaType = Flight.class, one = @One(select = "getFlightById")),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "orderDate", column = "order_date"),
    })
    List<Order> getOrdersByDate(Date date);

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
