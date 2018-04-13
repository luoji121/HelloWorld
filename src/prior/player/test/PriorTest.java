package prior.player.test;

import java.util.Random;

public class PriorTest {
	public final static double Top = 10; 
	public final static double Loops = 1000000;
	Random r = new Random();
	Player p1 = new Player();
	Player p2 = new Player();
	
	public void test_1_2() {
		int count = 0;
		for(int i=0;i<Loops;i++) {
			while(true) {
				count++;
				p1.score+=r.nextDouble();
				if(checkWinAndReset(p1)) {
					break;
				}
				p2.score+=r.nextDouble();
				if(checkWinAndReset(p2)) {
					break;
				}
			}	
		}
		System.out.println("p1获胜场次为"+p1.winCount);
		System.out.println("p2获胜场次为"+p2.winCount);
		System.out.println("平均进行轮次为"+count/Loops);
	}
	
	public void test_1_2_2_1() {
		int count = 0;
		for(int i=0;i<Loops;i++) {
			while(true) {
				count+=2;
				p1.score+=r.nextDouble();
				if(checkWinAndReset(p1)) {
					break;
				}
				p2.score+=r.nextDouble();
				if(checkWinAndReset(p2)) {
					break;
				}
				p2.score+=r.nextDouble();
				if(checkWinAndReset(p2)) {
					break;
				}
				p1.score+=r.nextDouble();
				if(checkWinAndReset(p1)) {
					break;
				}
			}	
		}
		System.out.println("p1获胜场次为"+p1.winCount);
		System.out.println("p2获胜场次为"+p2.winCount);
		System.out.println("平均进行轮次为"+count/Loops);
	}
	
	public boolean checkWinAndReset(Player p) {
		if(p.score>=Top) {
			p.winCount++;
			p1.score=0;
			p2.score=0;
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		PriorTest pt = new PriorTest();
//		pt.test_1_2();
		pt.test_1_2_2_1();
	}
}
