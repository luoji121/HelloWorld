package normal.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import com.google.common.collect.Lists;

public class IntStreamTest {
	public static void main(String[] args) {
		Random r = new Random();
		IntStream is = r.ints(7,9);
		int[] i = is.limit(10).toArray();
		System.out.println(i);
	}
}
