package main.com.express.dao;

import main.com.express.entity.Orders;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/29.
 */
public interface OrderDao {

    public List<Orders> getOrdersByphone(String phone);

    public List<Orders> getAllOrder();

    public boolean addOrder(Orders orders);

    public boolean delOrder(int id);

    public boolean updOrder(Orders orders);
}
