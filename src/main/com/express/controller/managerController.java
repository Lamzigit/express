package main.com.express.controller;

import main.com.express.service.ManagerService;
import main.com.express.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by linzhijie on 2017/1/2.
 */
@Controller
public class managerController {
    @Autowired
    public ManagerService managerService;

    @Autowired
    public OrdersService ordersService;



}
