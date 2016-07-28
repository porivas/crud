package com.luxoft.probation.crud.service.impl;

import com.luxoft.probation.crud.core.domain.Order;
import com.luxoft.probation.crud.core.util.BoardClassEnum;
import com.luxoft.probation.crud.persistence.dao.OrderDAO;
import com.luxoft.probation.crud.service.OrderService;
import com.sun.javafx.beans.IDProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Order service implementation
 * <p>
 * Created by hhayryan on 5/30/2016.
 */
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public void createOrder(Order order) {
        orderDAO.createOrder(order);
    }

    @Override
    public void deleteOrder(int orderId) {
        orderDAO.deleteOrder(orderId);
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderDAO.getOrderById(orderId);
    }

    @Override
    public Order getOrderByOrderId(String orderId) {
        return orderDAO.getOrderByOrderId(orderId);
    }

    @Override
    public List<Order> getOrdersByBoardClass(BoardClassEnum boardClassEnum) {
        return orderDAO.getOrdersByBoardClass(boardClassEnum.getBoardClass());
    }

    @Override
    public List<Order> getOrdersByFlightId(int flightId) {
        return orderDAO.getOrdersByFlightId(flightId);
    }

    @Override
    public List<Order> getOrdersByDate(Date date) {
        return orderDAO.getOrdersByDate(date);
    }
}
