package hc;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;

public class Producer {

	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.setClusterName("hazelcast mancenter");
		HazelcastInstance hzClient = HazelcastClient.newHazelcastClient(clientConfig);
		final ITopic<String> topic = hzClient.getTopic("myTopic");
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
			topic.publish("Hello");
		}
		, 1000, 1000, TimeUnit.MILLISECONDS);
		


	}

}
