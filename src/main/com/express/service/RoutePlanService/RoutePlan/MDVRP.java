package main.com.express.service.RoutePlanService.RoutePlan;

import main.com.express.entity.Orders;
import main.com.express.service.RoutePlanService.RoutePlan.utilentity.Deport;
import main.com.express.service.RoutePlanService.RoutePlan.utilentity.Goods;
import main.com.express.service.RoutePlanService.RoutePlan.utilentity.Plan;
import main.com.express.service.RoutePlanService.RoutePlan.utilentity.Plans;
import main.com.express.service.RoutePlanService.RoutePlan.utilentity.Route;

import org.moeaframework.core.Problem;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * NSGA3算法求解Mdvrp问题
 *
 * @author ZJ_bin
 *
 *         Modified by VP on 8/29/2016
 *
 *         Reconstructed by LuoXuan on 12/26/2016
 *
 */

public class MDVRP implements Problem {

	/* 优化目标的个数 */
	public static final int NUMOBJECTS = 4;
	// 标记方案可行性
	private boolean isValid;
	// 设置每个车场的车辆数
	private int nCars = 10;
	// 设置车场数（即派件中心数，此处选个广医的番禺和越秀两个校区作为收发点）
	private int nDeports = 2;
	// 设置的限制条件1（汽车运行时间限制），此处不限制，设为最大Integer值
	private int cDuration = Integer.MAX_VALUE;
	// 设置的限制条件1（汽车容量限制）
	private int cCapacity = 4000;
	// 未知参数
	private int capacityRange;
	// 记录一次规划的订单数
	private int norderes;
	// 实际使用的车辆数
	private int totalCars;
	// 实际总路径长度
	private double totalCost;
	// 记录所有客户平均等待时间
	private double totalTime;
	// 每条路径上的顾客平均等待时间
	private double averageTime[];
	// 保存解码结果数组，下标代表车号，数组值代表第i号车访问的顾客数
	private int numService[] = new int[101];// 多留出了些空位，无妨
	// 记录每条路径的里程数
	private double routeCost[];
	// 加入实际车场每条路径的里程数
	private double deportCost[];
	// 保存当前路径的实际车场编号
	private int curD[];
	// 记录每条路径
	private StringBuffer results[];
	// 记录每条路径的实际货量
	private int sumCapacity[];
	// 记录每条路径的长度
	private int lenOfRt[];
	// 记录车场、货物点的两两间的真实距离
	private double[][] distance;
	// 记录读取的货物集合
	private ArrayList<Goods> goodsList = new ArrayList<Goods>();
	// 记录车场
	private ArrayList<Deport> Deports = new ArrayList<Deport>();
	// 设置一个虚拟车场
	private Deport vd = new Deport(3, 113.347772, 23.94219);
	// 设置一个总方案对象
	private Plan plan;

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public int getnDeports() {
		return nDeports;
	}

	public void setnDeports(int nDeports) {
		this.nDeports = nDeports;
	}

	public int getnorderes() {
		return norderes;
	}

	public void setnorderes(int norderes) {
		this.norderes = norderes;
	}

	public int getTotalCars() {
		return totalCars;
	}

	public void setTotalCars(int totalCars) {
		this.totalCars = totalCars;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}

	public MDVRP() {
	}

	public MDVRP(int flag, List<Orders> ordersList) {

		// 以广医番禺校区和越秀校区为基本的两个车场
		Deports.add(new Deport(0, 113.432337, 23.048174));
		Deports.add(new Deport(1, 113.263207, 23.140265));

		// 将快递订单数据计入goodsList中,根据flag判断是求揽件(0)还是派件(1)
		goodsList.add(new Goods(0, 113.347772, 23.94219, 0, 0));// 把虚拟车场作为第一个plot子，因为plot是从第二个开始
		if (flag == 0) {
			int i = 1;
			for (Orders order : ordersList) {
				JSONObject saddress = JSONObject.fromObject(order.getSaddress());
				JSONObject point = JSONObject.fromObject(saddress.getString("point"));
				goodsList.add(new Goods(order.getId(), point.getDouble("lng"), point.getDouble("lat"), 0, order.getWeight()));
				i++;
			}
		} else if (flag == 1) {
			int i = 1;
			for (Orders order : ordersList) {
				JSONObject raddress = JSONObject.fromObject(order.getRaddress());
				JSONObject point = JSONObject.fromObject(raddress.getString("point"));
				goodsList.add(new Goods(order.getId(), point.getDouble("lng"), point.getDouble("lat"), 0, order.getWeight()));
				i++;
			}
		}
		// -----------------------------------------------------------------------------------------------------------
		norderes = ordersList.size();
		distance = new double[norderes + 1 + nDeports][norderes + 1 + nDeports];
		// 计算点间的距离
		for (int i = 0; i <= norderes; i++) {
			for (int j = i + 1; j <= norderes; j++) {
				distance[i][j] = GetDistanceByBaiduMapAPI.getDistanceByJSONResponse(goodsList.get(i).getLNG(),
						goodsList.get(i).getLAT(), goodsList.get(j).getLNG(), goodsList.get(j).getLAT());
				distance[j][i] = distance[i][j];
			}
		}
		// 把实际收发点与各个点的距离也写入二维数组中
		for (int j = 0; j <= norderes; j++) {
			distance[norderes + 1][j] = GetDistanceByBaiduMapAPI.getDistanceByJSONResponse(Deports.get(0).getLNG(),
					Deports.get(0).getLAT(), goodsList.get(j).getLNG(), goodsList.get(j).getLAT());
			distance[j][norderes + 1] = distance[norderes + 1][j];
			distance[norderes + 2][j] = GetDistanceByBaiduMapAPI.getDistanceByJSONResponse(Deports.get(1).getLNG(),
					Deports.get(1).getLAT(), goodsList.get(j).getLNG(), goodsList.get(j).getLAT());
			distance[j][norderes + 2] = distance[norderes + 2][j];
		}
		// ----------------------------------------------------------------------------------------------------------
	}

	@Override
	public String getName() {
		return "MDVRP";
	}

	@Override
	public int getNumberOfVariables() {
		return 1;
	}

	@Override
	public int getNumberOfObjectives() {
		return NUMOBJECTS;
	}

	@Override
	public int getNumberOfConstraints() {
		return 1;
	}

	@Override
	public synchronized void evaluate(Solution solution) {

		System.out.println("Before evaluate");

		int[] chrom = EncodingUtils.getPermutation(solution.getVariable(0));
		for(int i : chrom){
			System.out.print(" " +i);
		}
		System.out.println("\nAfter evaluate");
		// 先将得到整数序列进行转换
		int length = chrom.length;
		int[] newChrom = new int[length + 1];
		newChrom[0] = 0;
		for (int i = 1; i < newChrom.length; i++) {
			newChrom[i] = chrom[i - 1] + 1;
		}

		System.out.println("Before decoding");

		// 调用解码算子
		this.decoding(newChrom);

		System.out.println("After decoding");

		// 目标函数值初始化
		double f[] = new double[NUMOBJECTS];
		f[0] = totalCost;
		f[1] = totalTime;
		f[2] = totalCars;
		f[3] = this.capacityRange;

		solution.setObjectives(f);
		// 限制条件在解码算子中体现
		// solution.setConstraints(g);
		System.out.println("After evaluate");
	}

	/**
	 * 解码算子，在运行NSGA2算法前每一个方案采用整数编码，运行后得到的整数序列用该算子进行解码
	 *
	 * @param chrom
	 *            染色体--即算法运行后的pareto解集中的一个方案
	 */
	public void decoding(int[] chrom) {

		plan = new Plan();
		double load;// 存放当前汽车的载重
		double cost;// 存放临时的路径里程值
		int duration;// 存放服务时间
		double v[] = new double[norderes + 1];// 存放虚拟车场下的路径值
		int pred[] = new int[norderes + 1];// 用于转换路径
		int j;
		int index;
		int start, end;// 路径的起点下标和终点下标

		this.isValid = true;

		// 以下为plot解码算子
		v[0] = 0.0;// 初始化第一个位置的值
		pred[0] = 0;
		for (int i = 1; i < norderes + 1; i++)
			v[i] = Double.MAX_VALUE;

		for (int i = 1; i < norderes + 1; i++) {
			load = 0.0;
			cost = 0.0;
			j = i;
			duration = 0;

			do {
				load = load + goodsList.get(chrom[j]).getWeight();
				if (load > cCapacity) {
					break;
				}
				duration = duration + goodsList.get(chrom[j]).getDuration();
				if (j == i) {
					cost = distance[0][chrom[j]] + distance[chrom[j]][0];
				} else {
					cost = cost - distance[chrom[j - 1]][0] + distance[chrom[j - 1]][chrom[j]] + distance[chrom[j]][0];
				}
				// 修改为plot+
				if (duration <= cDuration) {
					// v[i－1]保存的是到chrom中第i-1个客户为止路径的总长度。chrom中第i-1个客户是上一路径的最后一个客户节点。
					// cost对应的是从chrom中第i个客户开始的路径长度，是当前尝试构造的路径尝试直到超过容量或没有客户为止。
					double vNew = v[i - 1] + cost;
					// 尝试以当前j作为新路径的第一个节点，对应if为真。v[j]对应的是原有的方案。if为真，意味着新方案路径更短，或路径相等
					// 但能使前一个路径服务更多的客户
					// 如果if为假，意味着上一条路径还可以进一步延长，当前节点j可以并入上一条路径
					if (vNew < v[j] || ((vNew == v[j]) && (pred[i - 1] + 1 < pred[j]))) {
						v[j] = vNew;
						// pred保存当前节点的路径是从第几号客户开始的。pred＝x,则路径从第chrom[x+1]号客户开始
						pred[j] = i - 1;
					}
					j = j + 1;
				}
			} while (load <= cCapacity && duration <= cDuration && j <= norderes);
		}
		// 以下是根据Plot算法得来的pred数组进行转化，从后向前解码得到每辆车服务的客户数numService(倒序！)。从最后chrom最后一个客户开始解码。
		totalCars = 0;
		start = pred[pred.length - 1] + 1;
		end = pred.length - 1;
		int serviced = 0;
		int numService[] = new int[101];
		int[] deportcount = new int[nDeports];
		for (int i : deportcount) {
			deportcount[i] = 0;
		}
		// while循环取出方案线完成
		while (serviced < norderes) {
			totalCars++;
			// System.out.println(" " + start1 + " " + end1);
			numService[totalCars] = end - start + 1;
			serviced += numService[totalCars];
			Route route = new Route();
			route.setRid(totalCars);
			// 将路径的点对象存入路径list中
			for (int i = start; i < end + 1; i++) {
				route.getGoodsList().add(goodsList.get(i));
				route.setWeight(route.getWeight() + goodsList.get(i).getWeight());// 存入该路径的负重
			}
			route.setLength(v[end] - v[start - 1]);// 记录该路径的总长度(到虚拟车场的)
			double[] tempdistance = new double[nDeports];// 创建一个对象临时保存各收发点到路径的实际距离，用于比较
			// 循环计算出路径与各收发点的实际距离
			double mindistance = Double.MAX_VALUE;// 记录最小的实际距离
			int mindistance_i = 0;// 记录最小距离所在收发点号
			for (int i = 0; i < nDeports; i++) {
				tempdistance[i] = route.getLength() - distance[0][start] - distance[end][0]
						+ distance[norderes + i + 1][start] + distance[end][norderes + i + 1];
				// 如果比最小距离小，且收发点有空车，则更新最小距离
				if (tempdistance[i] < mindistance && deportcount[i] < nCars) {
					mindistance_i = i;
					mindistance = tempdistance[i];
				}
			}
			// 记录并更新收发点
			deportcount[mindistance_i]++;
			route.setDeport(Deports.get(mindistance_i));
			route.setLength(mindistance);
			// 将路径对象记录入当前规划计划中
			plan.getRoute().add(route);
			end = start - 1;
			start = pred[end] + 1;
		}
		// 计算实际路径长度并写入对象中
		totalCost = 0;
		for (Route r : plan.getRoute()) {
			totalCost += r.getLength();
		}
		plan.setLength(totalCost);
	}

	@Override
	public Solution newSolution() {

		Solution solution = new Solution(1, NUMOBJECTS);
		solution.setVariable(0, EncodingUtils.newPermutation(norderes));
		return solution;
	}

	@Override
	public void close() {

		// do nothing
	}

}
