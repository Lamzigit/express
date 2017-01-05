package main.com.express.service.serviceImpl;

import main.com.express.dao.OrderDao;
import main.com.express.dao.daoImpl.OrderDaoImpl;
import main.com.express.entity.Orders;
import main.com.express.service.RoutePlanService.RoutePlan.MDVRP;
import main.com.express.service.RoutePlanService.RoutePlan.utilentity.Plan;
import main.com.express.service.RoutePlanService.RoutePlan.utilentity.Plans;
import org.moeaframework.Executor;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linzhijie on 2017/1/3.
 */
@Service
public class RoutePlanService {
    private final int MaxEvaluations = 100;

    @Autowired
    public OrderDao orderDao = new OrderDaoImpl();

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Plans getRoutePlan(String ToOrFrom){
        String state = "waiting";

        if(ToOrFrom.equals("收货"))
            state = "dispatching";

        List<Orders> ordersList = orderDao.getOrdersBystate(state);
        System.out.print("\n\n\n+添加完成");
        System.out.print("\n\n\n长度："+ordersList.size());
        NondominatedPopulation result;
        result = new Executor().withProblemClass(MDVRP.class,0,ordersList)
               .withAlgorithm("NSGA2").withMaxEvaluations(MaxEvaluations).distributeOnAllCores().run();
        MDVRP mdvrp = new MDVRP(0,ordersList);
        Plans plans = new Plans();
        int chrom[] = new int[mdvrp.getnorderes()];
        int newChrom[] = new int[mdvrp.getnorderes() + 1];
        for(int i = 0;i<result.size();i++){
            Solution solution = result.get(i);
            chrom = EncodingUtils.getPermutation(solution.getVariable(0));
            newChrom[0] = 0;
            for(int j = 1;j < newChrom.length;i++){
                newChrom[j] = chrom[j-1] + 1;
            }
            mdvrp.decoding(newChrom);
            Plan plan = mdvrp.getPlan();
            plan.setNo(i);
            plans.getPlans().add(plan);
        }
        System.out.print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("plans");
        return plans;

    }
}
