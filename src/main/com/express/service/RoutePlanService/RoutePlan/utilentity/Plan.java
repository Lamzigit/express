package main.com.express.service.RoutePlanService.RoutePlan.utilentity;

import java.io.Serializable;
import java.util.ArrayList;

public class Plan implements Serializable {

	private Integer no;
	private ArrayList<Route> Route=new ArrayList<Route>();//方案包含路径
	private Double length;
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public ArrayList<Route> getRoute() {
		return Route;
	}
	public void setRoute(ArrayList<Route> route) {
		Route = route;
	}
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}

}
