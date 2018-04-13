package normal.test;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.primitives.Doubles;

public strictfp class Test1 {
	public static double addDouble(double d1, double d2) {
		return d1+d2;
	}
	public  static void main(String[] args) {
		String[] ss = new String[2];
//		List<String> asList = Arrays.asList(ss);
//		System.out.println(asList.size());
//		
//		Joiner commaJoiner = Joiner.on(",");
//		String s = commaJoiner.join(asList);
//		System.out.println(s);
//		System.out.println(ss[1]);
		
//		System.out.println(Double.MIN_NORMAL);
//		
//		
//		double speed = 36.53/Math.abs(1200)*3600;
//		System.out.println(speed);
		double a = addDouble(0.1d, 0.2d);
		double b = 0.3d;
		System.out.println(a);
		System.out.println(b);
		System.out.println(a==b);
	}

}
