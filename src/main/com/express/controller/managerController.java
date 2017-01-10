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

    /**
     * 登录  get方法转跳登录页面
     *      post方法接受管理员信息，验证登录
     * @return
     */

    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public String doLogin(Manager manager, HttpSession session){
        if(managerService.hasManager(manager)&&managerService.checkManager(manager)){
            manager.setType(managerService.getManagerByName(manager.getName()).getType());
            session.setAttribute("manager",manager);
            return "console/index";
        }
        return "login";
    }

    /**
     * 获取未处理订单列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/order/undeal")
    public String undealOrder(HttpServletRequest request){
        List<Orders> ordersList= new ArrayList<Orders>();
        ordersList = ordersService.getOrdersBystate("waiting");
        request.setAttribute("ordersList",ordersList);
        return "order/undeal";
    }

    /**
     * 后台修改订单地址
     * @param address
     * @return
     */
    @RequestMapping(value = "/order/address")
    public @ResponseBody String addressOrder(Address address){
        return null;
    }

    /**
     * 后台添加订单  get方法转跳订单填写页面
     *             post方法处理提交的订单信息
     * @return
     */
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

    /**
     * 获取揽件路线列表
     * @return
     */
    @RequestMapping(value = "/route/embrace")
    public String embraceRoute(){
        return "route/embrace";
    }

    /**
     * 获取派件路线列表
     * @return
     */
    @RequestMapping(value = "/route/dispatch")
    public String dispatchRoute(){
        return "route/dispatch";
    }

    /**
     * 修改管理员权限
     * @return
     */
    @RequestMapping(value = "/staff/grade")
    public String gradeStaff(){
        return "staff-grade";
    }

    /**
     * 添加管理员  get方法转跳添加页面
     *           post方法处理提交的管理员信息
     * @return
     */
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

    /**
     * 管理员列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/staff/list")
    public String listStaff(HttpServletRequest request){
        List<Manager> managerList = managerService.getAllManager();
        request.setAttribute("managerList",managerList);
        return "staff/list";
    }

    /**
     * 删除管理员
     * @param manager
     * @return
     */
    @RequestMapping(value = "/staff/delete")
    public @ResponseBody String deleteStaff(Manager manager){
        if(managerService.delManager(manager.getId()))
            return "200";
        else
            return "400";
    }

}
