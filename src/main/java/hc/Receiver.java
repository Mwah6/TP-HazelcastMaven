package hc;

import com.hazelcast.config.Config;
import com.hazelcast.config.ListenerConfig;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.TopicConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;


public class Receiver {

	public static void main(String[] args) {
		ManagementCenterConfig managementCenterConfig = new ManagementCenterConfig();
		ManagementCenterConfig centerConfig = new ManagementCenterConfig();
		centerConfig.setScriptingEnabled(true);


		Config cfg = new Config();
		cfg.setClusterName("hazelcast mancenter");
		cfg.setManagementCenterConfig(managementCenterConfig);
		TopicConfig topicConfig = new TopicConfig();
		topicConfig.setName ("myTopic");
		topicConfig.setGlobalOrderingEnabled(true);
		topicConfig.setStatisticsEnabled(true);
		topicConfig.addMessageListenerConfig(new ListenerConfig(new MessageListener<String>() {

			public void onMessage(Message<String> message) {
				System.out.println("Message " + message.getMessageObject());			
			}
		}));
		cfg.addTopicConfig(topicConfig);
		HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);

	}

}
