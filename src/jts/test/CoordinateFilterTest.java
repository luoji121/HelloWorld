package jts.test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateFilter;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPoint;

public class CoordinateFilterTest {
	public static GeometryFactory gf = new GeometryFactory();
	public static void main(String[] args) {
		Coordinate[] cs = new Coordinate[4];
		cs[0] = new Coordinate(113.99,39.99);
		cs[1] = new Coordinate(113.991,39.99);
		cs[2] = new Coordinate(113.991,39.991);
		cs[3] = new Coordinate(113.99,39.991);
		MultiPoint mp = gf.createMultiPoint(cs);
		mp.apply(new CoordinateFilter() {
			
			@Override
			public void filter(Coordinate arg0) {
				// TODO Auto-generated method stub
				arg0.x+=1;
				arg0.y+=1;
			}
		});
		System.out.println(mp);
	}

}
