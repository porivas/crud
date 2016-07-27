package com.luxoft.probation.crud.persistence.dao.impl;

import com.luxoft.probation.crud.core.domain.Order;
import com.luxoft.probation.crud.persistence.dao.OrderDAO;
import com.luxoft.probation.crud.persistence.dao.mapper.OrderMaper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Order DAO implementation
 * <p>
 * Created by hhayryan on 5/27/2016.
 */
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private OrderMaper orderMaper;

    @Override
    public void createOrder(Order order) {
        orderMaper.createOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderMaper.updateOrder(order);
    }

    @Override
    public void deleteOrder(int id) {
        orderMaper.deleteOrder(id);
    }

    @Override
    public Order getOrderById(int id) {
        return orderMaper.getOrderById(id);
    }

    @Override
    public Order getOrderByOrderId(String orderId) {
        return orderMaper.getOrderByOrderId(orderId);
    }

    @Override
    public List<Order> getOrdersByBoardClass(int boardClass) {
        return orderMaper.getOrdersByBoardClass(boardClass);
    }

    @Override
    public List<Order> getOrdersByFlightId(int flightId) {
        return orderMaper.getOrdersByFlightId(flightId);
    }

    @Override
    public List<Order> getOrdersByDate(Date date) {
        return orderMaper.getOrdersByDate(date);
    }
}
