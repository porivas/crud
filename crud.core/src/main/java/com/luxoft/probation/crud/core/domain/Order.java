package com.luxoft.probation.crud.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Ticket order domain class
 * <p>
 * Created by HHayryan on 5/18/2016.
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 9004820977963031278L;

    private int id;
    private int boardClass;
    private int count;
    private String creditCard;
    private Flight flight;
    private String orderId;
    private Date orderDate;

    public Order() {
        orderId = UUID.randomUUID().toString().replace("-", "X");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoardClass() {
        return boardClass;
    }

    public void setBoardClass(int boardClass) {
        this.boardClass = boardClass;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
