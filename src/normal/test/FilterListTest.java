package normal.test;

import org.apache.hadoop.hbase.filter.FilterList;

public class FilterListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL);
		System.out.println(fl.getFilters().size());
	}

}
