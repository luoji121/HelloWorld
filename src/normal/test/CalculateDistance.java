package normal.test;

public class CalculateDistance {
	// 计算两坐标点之间的距离
	public static double D_jw(double wd1, double jd1, double wd2, double jd2) {
		double x, y, out;
		double PI = 3.14159265;
		double R = 6.371229 * 1e6;

		x = (jd2 - jd1) * PI * R * Math.cos(((wd1 + wd2) / 2) * PI / 180) / 180;
		y = (wd2 - wd1) * PI * R / 180;
		out = Math.hypot(x, y);
		return out;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double d = CalculateDistance.D_jw(29.581, 106.558, 29.578, 106.561);
		System.out.println(d);
	}

}
