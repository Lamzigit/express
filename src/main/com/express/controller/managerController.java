package main.com.express.controller;

import jdk.Exported;
import main.com.express.entity.Address;
import main.com.express.entity.Manager;
import main.com.express.entity.Orders;
import main.com.express.service.ManagerService;
import main.com.express.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linzhijie on 2017/1/2.
 */
@Controller
@RequestMapping(value = "/manager")
public class managerController {
    @Autowired
    public ManagerService managerService;

    @Autowired
    public OrdersService ordersService;

    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public String doLogin(Manager manager, HttpSession session){
        if(managerService.hasManager(manager)&&managerService.checkManager(manager)){
            session.setAttribute("manager",manager);
            return "console/index";
        }
        return "login";
    }

    @RequestMapping(value = "/order/undeal")
    public String undealOrder(HttpServletRequest request){
        List<Orders> ordersList= new ArrayList<Orders>();
        ordersList = ordersService.getOrdersBystate("waiting");
        request.setAttribute("ordersList",ordersList);
        return "order/undeal";
    }

    @RequestMapping(value = "/order/address")
    public @ResponseBody String addressOrder(Address address){
        return null;
    }

    @RequestMapping(value = "/order/add",method = RequestMethod.GET)
    public String addOrder(){
        return "order/add";
    }

    @RequestMapping(value = "/order/add",method = RequestMethod.POST)
    public String doAddOrder(Orders orders){
        //应添加checkOrder（）检查新order是否完整；
        ordersService.addOrder(orders);
        return "console-index";
    }

    @RequestMapping(value = "/route/embrace")
    public String embraceRoute(){
        return "route/embrace";
    }

    @RequestMapping(value = "/route/dispatch")
    public String dispatchRoute(){
        return "route/dispatch";
    }

    @RequestMapping(value = "/staff/grade")
    public String gradeStaff(){
        return "staff-grade";
    }

    @RequestMapping(value = "/staff/add" , method = RequestMethod.GET)
    public String addStaff(){
        return "staff/add";
    }

    @RequestMapping(value = "/staff/add" , method = RequestMethod.POST)
    public String doAddStaff(Manager manager){
        if(managerService.addManager(manager)){
            return "console/index";
        }
        else
            return "staff/add";
    }

    @RequestMapping(value = "/staff/list")
    public String listStaff(HttpServletRequest request){
        List<Manager> managerList = managerService.getAllManager();
        request.setAttribute("managerList",managerList);
        return "staff/list";
    }

    @RequestMapping(value = "/staff/delete")
    public @ResponseBody String deleteStaff(Manager manager){
        if(managerService.delManager(manager.getId()))
            return "200";
        else
            return "400";
    }

}
