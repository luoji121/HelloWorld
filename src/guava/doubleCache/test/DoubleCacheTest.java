package guava.doubleCache.test;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class DoubleCacheTest {
	TreeMap<Integer, Double> map = new TreeMap<>();
	Cache<Integer, Double> cache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS)
			.removalListener(new RemovalListener<Integer, Double>() {

				@Override
				public void onRemoval(RemovalNotification<Integer, Double> removal) {
					Integer key = removal.getKey();
					Double value = removal.getValue();
					SortedMap<Integer, Double> submap = map.subMap(key-2, key+3);
					submap.forEach((i,d) -> {
						if(!key.equals(i)) {
							if(Math.abs(d-value)<1) {
								System.out.println("发现碰撞：key1="+i+" key2="+key+" value1="+d+" value2="+value);
							}
						}
					});
					map.remove(removal.getKey());
//					System.out.println("removal: " + removal.getKey() + "/" + removal.getValue());
				}

			}).build();

	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(3000);
		Map<Integer, Double> dataMap = new HashMap<>();
		dataMap.put(0, 0.76);
		dataMap.put(1, 0.86);
		dataMap.put(2, 1.56);
		dataMap.put(3, 1.66);
		dataMap.put(4, 2.76);
		dataMap.put(5, 3.76);
		dataMap.put(6, 6.76);
		dataMap.put(7, 2.76);
		dataMap.put(8, 1.66);
		dataMap.put(9, 1.56);
		DoubleCacheTest dct = new DoubleCacheTest();
		Thread t = new Thread() {
			@Override
			public void run() {
				int count = 0;
				while(count<50) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dct.cache.cleanUp();
					count++;
					if(count%10==0) {
						System.out.println("cache="+dct.cache.asMap());
						System.out.println("map="+dct.map);
					}
				}
			}
		};
		t.start();
		dataMap.forEach((k,v)->{
			dct.map.put(k, v);
			dct.cache.put(k, v);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
//		int count = 0;
//		while(count<20) {
//			Thread.sleep(500);
//			cache.cleanUp();
//			count++;
//		}
		
		
		
		

	}

}
