package main.com.express.dao.daoImpl;

import main.com.express.dao.UserDao;
import main.com.express.entity.User;
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
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Serializable i = session.save(user);
        transaction.commit();
        return i != null;
    }

    @Override
    public boolean delUser(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "delete from User u where u.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int i = query.executeUpdate();
        transaction.commit();
        return i > 0;
    }

    @Override
    public List<User> getAllUser() {
        Session session = sessionFactory.openSession();
        return (List<User>)session.createCriteria(User.class).list();
    }

    @Override
    public User getUserByPhone(String phone) {
        Session session = sessionFactory.openSession();
        return (User)session.createCriteria(User.class).add(Restrictions.eq("phone",phone)).uniqueResult();
    }

    @Override
    public boolean updUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Object object = session.merge(user);
        transaction.commit();
        return (object != null);
    }
}
