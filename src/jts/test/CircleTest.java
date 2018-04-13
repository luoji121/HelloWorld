package jts.test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;

public class CircleTest {
	public static GeometryFactory gf = new GeometryFactory(); 
	public void createCircle1() {
		Coordinate[] cs = new Coordinate[4];
		cs[0] = new Coordinate(113.99,39.99);
		Point p = gf.createPoint(cs[0]);
		Geometry g = p.buffer(0.001);
		System.out.println(g);
	}
	public void createCircle2() {
		Coordinate[] cs = new Coordinate[5];
		cs[0] = new Coordinate(113.99,39.99);
		cs[1] = new Coordinate(113.991,39.99);
		cs[2] = new Coordinate(113.991,39.991);
		cs[3] = new Coordinate(113.99,39.991);
		cs[4] = new Coordinate(113.99,39.99);
		Geometry g = gf.createLinearRing(cs);
		System.out.println(g);
	}
	
	public static void main(String[] args) {
		CircleTest ct = new CircleTest();
		ct.createCircle1();
		ct.createCircle2();
	}
}
