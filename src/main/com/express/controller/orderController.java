package main.com.express.controller;

import main.com.express.entity.Address;
import main.com.express.entity.Orders;
import main.com.express.entity.User;
import main.com.express.service.AddressService;
import main.com.express.service.OrdersService;
import main.com.express.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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

    /**
     * 异步提交订单
     * @param orders
     * @param response
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    public String addOrder(Orders orders , HttpServletResponse response) throws IOException {
        //返回给前端的json
        JSONObject jsonObject = new JSONObject();
        String phone = orders.getPhone();
        //前端页面无法获取并传送用户id,唯有重新查询用户信息
        User user = userService.getUserByPhone(phone);
        int id = user.getId();
        orders.setUid(id);
        //生成随机数作为单号
        orders.setTransnum(String.valueOf((100000+Math.random()*(999999-100000+1))));//随机数范围：(最小值+Math.random()*(最大值-最小值+1))
        //添加订单
        if(ordersService.addOrder(orders)){
            jsonObject.put("hasAdd","true");
            jsonObject.put("transnum",orders.getTransnum());
        }

        //获取用户的历史地址，
        Address address = addressService.getAddressByPhone(orders.getPhone());

        //若老用户更改地址，则更新地址记录
        //if(address != null && (!orders.getRaddress().equals(address.getRaddress()) || !orders.getSaddress().equals(address.getSaddress()))){
        if(address != null && address.getId() != null){
            address.setRaddress(orders.getRaddress());
            address.setSaddress(orders.getSaddress());
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
        System.out.println(address.getId());
        return jsonObject.toString();
    }

    /**
     * 异步获取历史订单列表
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public List<Orders> listOrder(String phone){
        List<Orders> orderses = ordersService.getOrdersByphone(phone);
        return orderses;
    }
}
