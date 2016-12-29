package main.com.express.dao;

import main.com.express.entity.Order;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/29.
 */
public interface OrderDao {

    public Order getOrderById(int id);

    public List<Order> getAllOrder();

    public boolean addOrder(Order order);

    public boolean delOrder(int id);

    public boolean updOrder(Order order);
}
