package main.com.express.controller;

import main.com.express.entity.Orders;
import main.com.express.service.OrdersService;
import main.com.express.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by linzhijie on 2017/1/1.
 */
@Controller
@RequestMapping(value = "/order")
public class orderController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add")
    public String addOrder(Orders orders){
        orders.setUid(userService.getUserByPhone(orders.getPhone()).getId());
        orders.setTransnum(String.valueOf((1+Math.random()*(100000000))));
        ordersService.addOrder(orders);
        return "orderhistory";
    }

}
