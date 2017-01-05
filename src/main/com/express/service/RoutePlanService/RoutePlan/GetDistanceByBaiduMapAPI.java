package main.com.express.service.RoutePlanService.RoutePlan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

public class GetDistanceByBaiduMapAPI {

	//我的百度地图API授权码
	private static final String BAIDU_APP_KEY = "PrpfOjCORz8bZ8mSQtGShC7iiFUGzPzq";

	//获得两点距离请求的JSON
	public static int getDistanceByJSONResponse(double p1LNG,double p1LAT,double p2LNG,double p2LAT){

		URL resjson=null;
		StringBuffer sb=new StringBuffer("");
		int distance=0;
		String origins=""+p1LAT+","+p1LNG;
		String destinations=""+p2LAT+","+p2LNG;
		try {
			resjson=new URL("http://api.map.baidu.com/routematrix/v2/driving?output=json&origins="+origins+"&destinations="+destinations+"&ak="+BAIDU_APP_KEY);
			BufferedReader in=new BufferedReader(new InputStreamReader(resjson.openStream()));
			String res=null;
			while((res=in.readLine())!=null){
				sb.append(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(sb!=null){
			JSONObject jsonObject = JSONObject.fromObject(sb.toString());
			distance=jsonObject.getJSONArray("result").getJSONObject(0).getJSONObject("distance").getInt("value");
		}
		return distance;
	}
}
