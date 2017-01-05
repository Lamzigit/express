package main.com.express.dao.daoImpl;

import main.com.express.dao.OrderDao;
import main.com.express.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linzhijie on 2016/12/30.
 */
@Repository
public class OrderDaoImpl implements OrderDao{

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Orders> getOrdersByphone(String phone) {
        Session session = sessionFactory.openSession();
        return (List<Orders>)session.createCriteria(Orders.class).add(Restrictions.eq("phone",phone)).list();
    }

    @Override
    public List<Orders> getOrdersBystate(String state) {
        Session session = sessionFactory.openSession();
        return (List<Orders>)session.createCriteria(Orders.class).add(Restrictions.eq("state",state)).list();
    }

    @Override
    public List<Orders> getAllOrder() {
        Session session = sessionFactory.openSession();
        return (List<Orders>)session.createCriteria(Orders.class).list();
    }

    @Override
    public boolean addOrder(Orders orders) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Serializable i= session.save(orders);
        transaction.commit();
        return (i != null);
    }

    @Override
    public boolean delOrder(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "delete from Orders o where o.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int i = query.executeUpdate();
        transaction.commit();
        return i > 0;
    }

    @Override
    public boolean updOrder(Orders orders) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Object object = session.merge(orders);
        transaction.commit();
        return object != null;
    }
}
