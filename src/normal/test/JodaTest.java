package normal.test;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JodaTest {

	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyyMMddHHmmss");
		/*try {
			dtf.parseDateTime("00000000");
		} catch(IllegalArgumentException e) {
			System.out.println("aaaaaa");
		}*/
		DateTime dt0 = dtf.parseDateTime("20171009170000");
		DateTime dt1 = dtf.parseDateTime("20171009183000");
		System.out.println(dt0.getMillis()-dt1.getMillis());
		System.out.println(dt0.getMillis());
		
	}

}
