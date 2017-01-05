package main.com.express.service.serviceImpl;

import main.com.express.dao.OrderDao;
import main.com.express.entity.Orders;
import main.com.express.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/31.
 */
@Service
public class OrderServiceImpl implements OrdersService {

    @Autowired
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Orders> getOrdersByphone(String phone) {
        return orderDao.getOrdersByphone(phone);
    }

    @Override
    public List<Orders> getOrdersBystate(String state) {
        return orderDao.getOrdersBystate(state);
    }

    @Override
    public List<Orders> getAllOrder() {
        return orderDao.getAllOrder();
    }

    @Override
    public boolean addOrder(Orders orders) {
        return orderDao.addOrder(orders);
    }

    @Override
    public boolean delOrder(int id) {
        return orderDao.delOrder(id);
    }

    @Override
    public boolean updOrder(Orders orders) {
        return orderDao.updOrder(orders);
    }
}
