package random.test;

import java.util.Random;

public class BrainWinner {
	public static Random r = new Random();
	public static float rate = 0.5f;
	public static int[] totalContest = {1,2,3,3,3,5,5,5,5,7,7,7,7};
	public int score = 0;
	public int count = 0;
	public void test() {
		for(int curContest:totalContest) {
			while(score<curContest) {
				if(r.nextFloat()<rate) {
					score++;
				}else {
					if(score>0)
					score--;
				}
				count++;
			}
			score=0;
		}
		
		System.out.println("总共进行"+count+"场");
	}
	public static void main(String[] args) {
		BrainWinner bw = new BrainWinner();
		bw.test();
	}
}
