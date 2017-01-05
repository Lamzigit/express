package main.com.express.service.RoutePlanService.RoutePlan.utilentity;

import java.io.Serializable;

public class Goods implements Serializable{

	private int expressId;//���������
	private double LNG;//����
	private double LAT;//γ��
	private int duration;// ����ʱ��
	private double weight;// ����
	public int getExpressId() {
		return expressId;
	}
	public void setExpressId(int expressId) {
		this.expressId = expressId;
	}
	public double getLNG() {
		return LNG;
	}
	public void setLNG(double lNG) {
		LNG = lNG;
	}
	public double getLAT() {
		return LAT;
	}
	public void setLAT(double lAT) {
		LAT = lAT;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Goods(int expressId, double lNG, double lAT, int duration, double weight) {
		super();
		this.expressId = expressId;
		LNG = lNG;
		LAT = lAT;
		this.duration = duration;
		this.weight = weight;
	}	
}
