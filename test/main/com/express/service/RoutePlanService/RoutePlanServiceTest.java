package main.com.express.service.RoutePlanService;

import main.com.express.dao.daoImpl.OrderDaoImpl;
import main.com.express.service.serviceImpl.RoutePlanService;
import main.com.express.service.RoutePlanService.RoutePlan.utilentity.Plans;
import main.com.express.service.serviceImpl.OrderServiceImpl;
import net.sf.json.JSONObject;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by linzhijie on 2017/1/3.
 */
public class RoutePlanServiceTest {
    public static ApplicationContext context;
    public RoutePlanService routePlanService;
    public OrderServiceImpl ordersService;
    public OrderDaoImpl orderDao;
    @BeforeClass
    public static void setContext(){
        context =new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Before
    public void setSessionFactory(){

        SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");

        orderDao = new OrderDaoImpl();
        System.out.print(sessionFactory);
        orderDao.setSessionFactory(sessionFactory);

        routePlanService = new RoutePlanService();
        routePlanService.setOrderDao(orderDao);
    }

    @Test
    public void getRoutePlan() throws Exception {

        System.out.print("\n" +"\n" +"\n" +orderDao +"\n" +
                "\n" +
                "\n开始测试");
        Plans plans = routePlanService.getRoutePlan("发货");
        JSONObject jsonObject = JSONObject.fromObject(plans);
        System.out.println(jsonObject.toString());
    }

}