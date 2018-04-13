package kafka.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

public class CreateTopic {
	public static void main(String[] args) {
		Properties config = new Properties();
		config.put("bootstrap.servers", "c1:9092,c2:9092,c3:9092");

		AdminClient admin = AdminClient.create(config);

		Map<String, String> configs = new HashMap<>();
		int partitions = 3;
		short replication = 1; 
		admin.createTopics(Arrays.asList(new NewTopic("test112", partitions, replication).configs(configs)));
	}
}
