package main.com.express.service.RoutePlanService.RoutePlan.utilentity;

import java.io.Serializable;
import java.util.ArrayList;

public class Route implements Serializable {

	private Integer rid;//·�߱��
	private Deport deport;//��������
	private Double length;//·�߳���
	private ArrayList<Goods> goodsList=new ArrayList<Goods>();//���·����
	private Double weight=0.0;
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Double getLength() {
		return length;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	public ArrayList<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(ArrayList<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Deport getDeport() {
		return deport;
	}
	public void setDeport(Deport deport) {
		this.deport = deport;
	}
}
