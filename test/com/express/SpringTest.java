package com.express;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by linzhijie on 2016/12/29.
 */
public class SpringTest {
    private static ApplicationContext context = null;

    @BeforeClass
    public static void setUpBeforeClass() throws  Exception{
       context =  new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test(){
        Date date =(Date)context.getBean("date");
        System.out.println(date);
    }

}
