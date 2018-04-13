package normal.test;

import java.util.TreeSet;

public class TreeSetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeSet<String> ts = new TreeSet<>();
		ts.add("2");
		ts.add("6");
		ts.add("4");
		ts.add("8");
		ts.add("9");
		System.out.println(ts.subSet("3", "7"));
	}

}
