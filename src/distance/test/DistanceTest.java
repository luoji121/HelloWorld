package distance.test;

import com.easymap.ezDBaaSManager.util.SpatialIndexUtil;

public class DistanceTest {
	// 计算两坐标点之间的距离，单位：米
	public static double D_jw(double wd1, double jd1, double wd2, double jd2) {
		double x, y, out;
		double PI = 3.14159265;
		double R = 6.371229 * 1e6;

		x = (jd2 - jd1) * PI * R * Math.cos(((wd1 + wd2) / 2) * PI / 180) / 180;
		y = (wd2 - wd1) * PI * R / 180;
		out = Math.hypot(x, y);
		return out;
	}
	
	public static double getChinaSpatialUtilByMinDist(double minDist){
		//用于跳过超出九宫格距离的spatialLevel计算，要求网格足够大（任一位置边长大于minDist，spatialLevel尽量小）
		//由于接近两级区域网格会无限小，所以无法应用于World坐标
//		SpatialIndexUtil siu = new SpatialIndexUtil();
		double basicX=73.6;
		double basicY=53.6;
		double totalLongitude=61.5;
		double totalLatitude=49.8;
		double minDistPerOneLng = distPerLng(basicY);
		//计算经度最短处一行最多不能超过多少个格子
		double maxColumn = Math.floor(minDistPerOneLng*totalLongitude/minDist);
		double spatialLevel = Math.floor(Math.log(maxColumn)/Math.log(2));//可以不用Math.floor?
		System.out.println(spatialLevel);
		return spatialLevel;
		
	}
	private static double distPerLng(double lat){
	    return 0.0003121092*Math.pow(lat, 4)
	           +0.0101182384*Math.pow(lat, 3)
	               -17.2385140059*lat*lat
	           +5.5485277537*lat+111301.967182595;
	  }
	public static void main(String[] args) {
//		System.out.println(D_jw(29.55406486988068,106.54158532619476,29.554107785224918,106.54168725013733));
		getChinaSpatialUtilByMinDist(200);
	}
}
