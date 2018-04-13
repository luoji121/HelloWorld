package normal.test;

import java.util.Random;

public class ExpectationTest {

	public static void main(String[] args) {
		int times = 1024;
		Random r = new Random();
		double total =0;
		double maxCur=2;
		for(int i=0;i<times;i++) {
			double cur=2;
			while(r.nextBoolean()) {
				total+=cur;
				cur*=2;
			}
			if(cur>maxCur) {
				maxCur=cur;
			}
		}
		System.out.println(total/times);
		System.out.println(maxCur);
	}

}
