package jts.test;

import java.util.ArrayList;
import java.util.Random;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequences;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPoint;

public class MergePointsToPolygon {
	public static GeometryFactory gf = new GeometryFactory();
	public static Random r = new Random();
	public void doIt() {
		double x = 113.80;
		double y = 39.98;
		
		ArrayList<ArrayList<Coordinate>> c0 = new ArrayList<>();
		for(int j=0;j<1;j++) {
			ArrayList<Coordinate> c1 = new ArrayList<>();
			for(int i=0;i<1;i++) {
				c1.add(new Coordinate(x+r.nextDouble(), y+r.nextDouble()));
			}
			c0.add(c1);
		}
		Geometry total=gf.createMultiPoint(new Coordinate[0]);
		for(ArrayList<Coordinate> c:c0) {
			Geometry g = gf.createMultiPoint((Coordinate[])c.toArray(new Coordinate[0]));
			g = g.convexHull();//将多个点转化成一个外凸多边形。
			total = total.union(g);
		}
		
		Geometry g2 = total.buffer(0.01);
		System.out.println(g2);
	}
	public static void main(String[] args) {
		MergePointsToPolygon mptp = new MergePointsToPolygon();
		mptp.doIt();
	}
//	LINESTRING (11.021 48.00750000435239, 11.021 48.00500000435244, 11.021 48.00250000435247, 11.02 48, 11.01 48, 11.01000009662766 48.000000000004206, 11.009000000003027 48.00000003917263, 11.007000000003533 48.00000009140281)
//	LINESTRING (11.021 48.00750000435239, 11.021 48.00500000435244, 11.021 48.00250000435247, 11.02 48, 11.01 48, 11.01000009662766 48.000000000004206, 11.009000000003027 48.00000003917263, 11.007000000003533 48.00000009140281)
//	LINESTRING (11.021 48.00750000435239, 11.021 48.00500000435244, 11.021 48.00250000435247, 11.02 48, 11.01 48, 11.01000009662766 48.000000000004206, 11.009000000003027 48.00000003917263, 11.007000000003533 48.00000009140281)
//	LINESTRING (11.021 48.00750000435239, 11.021 48.00500000435244, 11.021 48.00250000435247, 11.02 48, 11.01 48, 11.01000009662766 48.000000000004206, 11.009000000003027 48.00000003917263, 11.007000000003533 48.00000009140281)

}
