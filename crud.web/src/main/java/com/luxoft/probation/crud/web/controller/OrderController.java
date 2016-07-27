package com.luxoft.probation.crud.web.controller;

import com.luxoft.probation.crud.core.domain.Flight;
import com.luxoft.probation.crud.core.domain.Order;
import com.luxoft.probation.crud.service.FlightService;
import com.luxoft.probation.crud.service.OrderService;
import com.luxoft.probation.crud.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Order REST controller
 * <p>
 * Created by hhayryan on 5/31/2016.
 */
@Controller
@RequestMapping(value = "/order/*")
public class OrderController extends BaseController {

    @Autowired
    OrderService orderService;

    @Autowired
    FlightService flightService;

    @RequestMapping(method = RequestMethod.PUT, value = "/buy")
    public ModelAndView orderTicket(HttpServletRequest request) {
        ModelAndView jsonView = new ModelAndView(viewJSON);

        Order order = new Order();
        order.setOrderDate(new Date());
        order.setBoardClass(Integer.parseInt(request.getParameter("border_class")));
        order.setCount(Integer.parseInt(request.getParameter("ticket_count")));
        order.setCreditCard(request.getParameter("credit_card"));

        Flight flight = flightService.getFlightById(Integer.parseInt(request.getParameter("flight_id")));
        if (flight == null) {
            jsonView.getModel().put("error",
                    "Sorry but selected flight not available, please try to select other flight offer");

            return jsonView;
        }

        order.setFlight(flight);
        orderService.createOrder(order);

        jsonView.getModel().put("message",
                String.format("The ticket complete purchased, your order ID is: %s ," +
                        " please save this ID for future use", order.getOrderId()));

        return jsonView;
    }
}
