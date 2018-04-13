package guava.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;


public class TestCacheBuilder {

  public static void main(String[] args) {
    try {
      new TestCacheBuilder();
    }catch (Exception e){      
     e.printStackTrace(); 
    }
  }

  public TestCacheBuilder() {

    Cache<String, String> myCache = CacheBuilder.newBuilder()
        .expireAfterWrite(5, TimeUnit.SECONDS)
        .removalListener(new RemovalListener<String, String>() {
          public void onRemoval(RemovalNotification<String, String> removal) {
            System.out.println("removal: "+removal.getKey()+"/"+removal.getValue());
          }          
        })
        .build();


    Map<String, String> inMap = myCache.asMap();

    inMap.put("MyKey", "FirstValue");

    System.out.println("Initial Insert: "+inMap);

    //Wait 16 seconds

    try {
      Thread.sleep(4000);
    } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
    }

    System.out.println("After 4 seconds: " + inMap);

    inMap.put("MyKey", "SecondValue");

    try {
      Thread.sleep(1000);
    } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
    }

    System.out.println("After 1 more second: " + inMap);

    try {
      Thread.sleep(4000);
    } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
    }


    System.out.println("After 4 more seconds: " + inMap);

  }

}
