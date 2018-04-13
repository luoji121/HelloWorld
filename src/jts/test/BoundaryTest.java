package jts.test;

import java.util.ArrayList;
import java.util.Random;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPoint;

public class BoundaryTest {
	public static GeometryFactory gf = new GeometryFactory();
	public static Random r = new Random();
	public void doIt() {
		double x = 113.80;
		double y = 39.98;
		
		ArrayList<Coordinate> c0 = new ArrayList<>();
		for(int j=0;j<2;j++) {
			c0.add(new Coordinate(x+r.nextDouble(), y+r.nextDouble()));
		}
		Geometry total=gf.createMultiPoint((Coordinate[])c0.toArray(new Coordinate[0]));
		System.out.println(total);
		System.out.println(total.convexHull());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoundaryTest bt = new BoundaryTest();
		bt.doIt();
	}

}
