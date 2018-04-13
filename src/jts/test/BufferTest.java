package jts.test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPoint;

public class BufferTest {
	public static GeometryFactory gf = new GeometryFactory();
	public static void main(String[] args) {
		Coordinate[] cs = new Coordinate[4];
		cs[0] = new Coordinate(113.99,39.99);
		cs[1] = new Coordinate(113.991,39.99);
		cs[2] = new Coordinate(113.991,39.991);
		cs[3] = new Coordinate(113.99,39.991);
		MultiPoint mp = gf.createMultiPoint(cs);
		Geometry buffer = mp.buffer(0.001);
		System.out.println(mp);
		System.out.println(buffer);
	}
}
