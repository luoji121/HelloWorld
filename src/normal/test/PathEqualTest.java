package normal.test;

import java.io.File;

public class PathEqualTest {
	public static void main(String[] args) {
		File a = new File("I:\\aaa.txt");
		File b = new File("I:\\aaa.txt");
		System.out.println(a==b);
		System.out.println(a.getPath()==b.getPath());
	}
}
