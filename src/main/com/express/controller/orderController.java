package main.com.express.controller;

import main.com.express.entity.Address;
import main.com.express.entity.Orders;
import main.com.express.entity.User;
import main.com.express.service.AddressService;
import main.com.express.service.OrdersService;
import main.com.express.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/add")
    public String addOrder(Orders orders){
        String phone = orders.getPhone();
        //前端页面无法获取并传送用户id,唯有重新查询用户信息
        User user = userService.getUserByPhone(phone);
        orders.setUid(user.getId());
        //生成随机数作为单号
        orders.setTransnum(String.valueOf((100000+Math.random()*(999999-100000+1))));//随机数范围：(最小值+Math.random()*(最大值-最小值+1))
        //添加订单
        ordersService.addOrder(orders);
        //获取用户的历史地址，
        Address address = addressService.getAddressByPhone(orders.getPhone());
        //若老用户更改地址，则更新地址记录
        if(address != null && (!orders.getRaddress().equals(address.getRaddress()) || !orders.getSaddress().equals(address.getSaddress()))){
            System.out.print("update");
            addressService.updAddress(address);
        }
        //若是新用户，则添加新地址
        else {
            //封装地址
            address = new Address();
            address.setRaddress(orders.getRaddress());
            address.setSaddress(orders.getSaddress());
            address.setPhone(orders.getPhone());
            addressService.addAddress(address);
        }
        return "orderhistory";
    }

    @RequestMapping(value = "/list")
    public @ResponseBody List<Orders> listOrder(String phone){
        List<Orders> orderses = ordersService.getOrderByphone(phone);
        return orderses;
    }
}
