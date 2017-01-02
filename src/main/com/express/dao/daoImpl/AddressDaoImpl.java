package main.com.express.dao.daoImpl;import main.com.express.dao.AddressDao;import main.com.express.entity.Address;import org.hibernate.Session;import org.hibernate.SessionFactory;import org.hibernate.Transaction;import org.hibernate.criterion.Restrictions;import org.hibernate.query.Query;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Repository;import org.springframework.transaction.annotation.Transactional;import java.util.List;/** * Created by linzhijie on 2016/12/29. */@Repositorypublic class AddressDaoImpl implements AddressDao{    public void setSessionFactory(SessionFactory sessionFactory) {        this.sessionFactory = sessionFactory;    }    @Autowired    private SessionFactory sessionFactory;    public Address getAddressByPhone(String phone) {        Session session = sessionFactory.openSession();        //Session session = sessionFactory.openSession();//测试时用openSession        return (Address)session.createCriteria(Address.class).add(Restrictions.eq("phone",phone)).uniqueResult();    }    public List<Address> getAllAddress() {        Session session = sessionFactory.openSession();        //Session session = sessionFactory.openSession();//测试时用openSession        return (List<Address>)session.createCriteria(Address.class).list();    }    public boolean addAddress(Address address) {        Session session = sessionFactory.openSession();        //Session session = sessionFactory.openSession();//测试时用openSession        return ((Integer)session.save(address)>0);    }    public boolean updAddress(Address address) {        Session session = sessionFactory.openSession();        //Session session = sessionFactory.openSession();//测试时用openSession        Transaction transaction = session.beginTransaction();        session.merge(address);        transaction.commit();        return true;    }    public boolean delAddress(int id) {        Session session = sessionFactory.openSession();        //Session session = sessionFactory.openSession();//测试时用openSession        Transaction transaction = session.beginTransaction();        String hql = "delete from Address a where a.id= :id";        Query query = session.createQuery(hql);        query.setParameter("id",id);        int i= query.executeUpdate();        transaction.commit();        return i>0;    }}