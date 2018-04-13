package kafka.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "c1:9092,c2:9092,c3:9092");
		//以下两个参数取决于key,value类型，即ProducerRecord的泛型
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("compression.type", "gzip");
		//可选参数
//		props.put("acks", "all");
//		props.put("retries", 0);
//		props.put("batch.size", 16384);
//		props.put("linger.ms", 1);
//		props.put("buffer.memory", 33554432);
		
		Producer<String, String> producer = new KafkaProducer<>(props);
		String topic = "Test117";
		for(int i = 0; i < 100; i++) {
			//ProducerRecord构造函数中，四个参数分别为topic，partition编号，key，value
		     producer.send(new ProducerRecord<String, String>(topic, 0, "1", Integer.toString(i)));
		}
//		String params ="{'streamName':'stream01',"
//				+ "'hbaseTableName':'Test0528_0',"
//				+ "'hbaseInstance':'HBase01',"
//				+ "'dataType':'kvData',"
//				+ "'streams':[{'GPSID':'10001','TIME':'20131110200202','GPS-X':'113.98','GPS-Y':'39.99','SPEED':'100'},"
//				+ "{'GPSID':'10001','TIME':'20131110200203','GPS-X':'113.981','GPS-Y':'39.991','SPEED':'150'},"
//				+ "{'GPSID':'10002','TIME':'20131110200205','GPS-X':'113.982','GPS-Y':'39.992','SPEED':'200'}]}";
//		producer.send(new ProducerRecord<String, String>(topic, i%3, "1", params));
		
		producer.close();

	}
}