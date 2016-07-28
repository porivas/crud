package com.luxoft.probation.crud.persistence.dao;

import com.luxoft.probation.crud.core.domain.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Order DAO
 * <p>
 * Created by hhayryan on 5/27/2016.
 */
@Component
public interface OrderDAO {

    void createOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(int id);

    Order getOrderById(int id);

    Order getOrderByOrderId(String orderId);

    List<Order> getOrdersByBoardClass(int boardClass);

    List<Order> getOrdersByFlightId(int flightId);

    List<Order> getOrdersByDate(Date date);
}
