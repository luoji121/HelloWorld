package guava.test;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class CacheTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException{
		Cache<String, String> cache = CacheBuilder.newBuilder()
		        .expireAfterWrite(2, TimeUnit.SECONDS)
		        .removalListener(new RemovalListener<String, String>() {
		          public void onRemoval(RemovalNotification<String, String> removal) {
		            System.out.println("removal: "+removal.getKey()+"/"+removal.getValue());
		          }          
		        })
		        .build();

		ConcurrentMap<String, String> inmap = cache.asMap();
		inmap.put("1", "a");
		
		Thread.sleep(100);
		
		
		inmap.put("2", "b");
		Thread.sleep(100);
		inmap.put("3", "c");
		Thread.sleep(2500);
		cache.cleanUp();//如何做到removal: 1/a在前，emoval: 2/b在后

		Thread.sleep(1000);
	}

}
