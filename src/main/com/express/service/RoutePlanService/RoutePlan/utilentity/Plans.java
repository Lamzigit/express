package main.com.express.service.RoutePlanService.RoutePlan.utilentity;

import java.io.Serializable;
import java.util.ArrayList;

public class Plans implements Serializable {

	private ArrayList<Plan> plans=new ArrayList<Plan>();

	public ArrayList<Plan> getPlans() {
		return plans;
	}

	public void setPlans(ArrayList<Plan> plans) {
		this.plans = plans;
	}
	
}
