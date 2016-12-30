package main.com.express.dao.daoImpl;

import main.com.express.dao.ManagerDao;
import main.com.express.entity.Manager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/29.
 */

@Repository
public class ManagerDaoImpl implements ManagerDao{

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Manager getManagerById(int id) {
        Session session = sessionFactory.getCurrentSession();
        //Session session = sessionFactory.openSession();
        return (Manager)session.createCriteria(Manager.class).add(Restrictions.eq("id",id)).uniqueResult();
    }

    @Override
    public List<Manager> getAllManager() {
        Session session = sessionFactory.getCurrentSession();
        //Session session = sessionFactory.openSession();
        return (List<Manager>)session.createCriteria(Manager.class).list();
    }

    @Override
    public boolean addManager(Manager man) {
        Session session = sessionFactory.getCurrentSession();
        //Session session = sessionFactory.openSession();
        return (session.save(man)!=null);
    }

    @Override
    //@Transactional("transactionManager")
    public boolean delManager(int id) {
        Session session = sessionFactory.getCurrentSession();
        //Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "delete from Manager m where m.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int i = query.executeUpdate();
        transaction.commit();
        return i>0;
    }

    @Override
    public boolean updManager(Manager man) {
        Session session = sessionFactory.getCurrentSession();
        //Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(man);
        transaction.commit();
        return true;
    }
}
