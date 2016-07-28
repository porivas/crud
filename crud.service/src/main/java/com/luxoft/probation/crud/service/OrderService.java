package com.luxoft.probation.crud.service;

import com.luxoft.probation.crud.core.domain.Order;
import com.luxoft.probation.crud.core.util.BoardClassEnum;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Order service
 * <p>
 * Created by hhayryan on 5/30/2016.
 */
@Service
public interface OrderService {

    void createOrder(Order order);

    void deleteOrder(int orderId);

    Order getOrderById(int orderId);

    Order getOrderByOrderId(String orderId);

    List<Order> getOrdersByBoardClass(BoardClassEnum boardClassEnum);

    List<Order> getOrdersByFlightId(int flightId);

    List<Order> getOrdersByDate(Date date);
}
