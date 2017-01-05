package main.com.express.service.RoutePlanService.RoutePlan.utilentity;

import java.io.Serializable;

public class Deport implements Serializable {

	private Integer no;
	private Double LNG;
	private Double LAT;
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Double getLNG() {
		return LNG;
	}
	public void setLNG(Double lNG) {
		LNG = lNG;
	}
	public Double getLAT() {
		return LAT;
	}
	public void setLAT(Double lAT) {
		LAT = lAT;
	}
	public Deport(Integer no, Double lNG, Double lAT) {
		this.no = no;
		LNG = lNG;
		LAT = lAT;
	}
	
}
