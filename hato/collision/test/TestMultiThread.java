package collision.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestMultiThread {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService THREAD_POOL=Executors.newFixedThreadPool(10);
		String[] ss = {"10001","10003","10005","10006","10007","10008","10011","10013","10014","10015"}; 
		List<String> gpsids = Arrays.asList(ss);
		String startTime = "20120401100000";
		String stopTime = "20120401100500";
		
		Date d0 = new Date();
		gpsids.forEach(e -> {
			THREAD_POOL.submit(new SingleCollisionThread(e, startTime, stopTime));
		});
		THREAD_POOL.shutdown();
		while(!THREAD_POOL.awaitTermination(100, TimeUnit.MILLISECONDS)){

		}
		Date d1 = new Date();
		System.out.println("总共耗时"+(d1.getTime()-d0.getTime())+"毫秒");
	}
}

class SingleCollisionThread extends Thread{
	String gpsid;
	String startTime;
	String stopTime;
	public SingleCollisionThread(String gpsid, String startTime, String stopTime) {
		super();
		this.gpsid = gpsid;
		this.startTime = startTime;
		this.stopTime = stopTime;
	}
	@Override
	public void run() {
		TestCollision tc = new TestCollision();
		tc.testDirectGet(gpsid, startTime, stopTime);
	}	
}