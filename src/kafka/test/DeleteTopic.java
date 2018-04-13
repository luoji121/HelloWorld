package kafka.test;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;

public class DeleteTopic {

	public static void main(String[] args) {
		Properties config = new Properties();
		config.put("bootstrap.servers", "c1:9092,c2:9092,c3:9092");

		AdminClient admin = AdminClient.create(config);
		admin.deleteTopics(Arrays.asList("test113"));

	}

}
