package guava.test;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;

public class TableTest {

	public static void main(String[] args) {
		Table<String, String, Integer> t = HashBasedTable.create();
		t.put("", "", 1);
		DateTime d0 = DateTime.now();
		for (int j = 0; j < 100000000; j++) {
			Integer i = t.get("", "");
			i++;
			t.put("", "", i);
		}
		DateTime d1 = DateTime.now();
		System.out.println(t);
		System.out.println(d1.getMillis()-d0.getMillis());
		
		Map<String,Multiset> msms = new HashMap<>();
		d0 = DateTime.now();
		
		for (int j = 0; j < 100000000; j++) {
			if(msms.get("")==null) {
				msms.put("", HashMultiset.create());
			}
			msms.get("").add("");
		}
		d1 = DateTime.now();
		System.out.println(msms);
		System.out.println(d1.getMillis()-d0.getMillis());
		
		/* 输出结果，multiset快，但对于HBase计数而言意义不大，占大头的还是数据扫描时间
		 * {={=100000001}}
			3664
			{=[ x 100000000]}
			1376
		 */
	}

}
