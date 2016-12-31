package main.com.express.service;

import main.com.express.entity.Orders;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/31.
 */
public interface OrdersService {

    public Orders getOrderById(int id);

    public List<Orders> getAllOrder();

    public boolean addOrder(Orders orders);

    public boolean delOrder(int id);

    public boolean updOrder(Orders orders);
}
