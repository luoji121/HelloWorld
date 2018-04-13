package normal.test;

import java.util.Random;

public class TeamBattleTest {
	int teamAwins = 0;
	int teamBwins = 0;
	double teamApow = 1;
	double teamBpow = 1;
	Random r = new Random();
	int battleTimes = 1000;
	
	public void battle(){
		double percentage = teamApow/(teamApow+teamBpow);
		for(int i=0;i<battleTimes;i++){
			int teamAnum = 1000;
			int teamBnum = 1000;
			while(teamAnum>0 && teamBnum>0){
				if(percentage>r.nextDouble()){
					teamBnum--;
				}else{
					teamAnum--;
				}
			}
			if(teamAnum>0){
				System.out.println("teamA wins, left "+ teamAnum);
				teamAwins++;
			}else{
				System.out.println("teamB wins, left "+ teamBnum);
				teamBwins++;
			}
		}
		System.out.println("teamA wins "+teamAwins+" times!");
		System.out.println("teamB wins "+teamBwins+" times!");
		
	}
	
	public static void main(String[] args) {
		TeamBattleTest tbt = new TeamBattleTest();
		tbt.battle();
	}
}
