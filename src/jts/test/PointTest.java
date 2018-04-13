package jts.test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class PointTest {

	public static void main(String[] args) {
		GeometryFactory gf = new GeometryFactory();
		Geometry p = gf.createPoint(new Coordinate(113.8, 40));
		Coordinate[] coordinates = p.getCoordinates();
		System.out.println(coordinates);
	}

}
