package hc;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MemberAttributeConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class SimpleHazelcastMember {

	public static void main(String[] args) {
//		Config config =new Config();
//		ManagementCenterConfig centerConfig = new ManagementCenterConfig();
//		centerConfig.setScriptingEnabled(true);
//		MemberAttributeConfig memberAttributeConfig = new MemberAttributeConfig();
//
//		config.setClusterName("mancenter");
////		ManagementCenterConfig manCenterCfg = new ManagementCenterConfig();
////		manCenterCfg.setEnabled(true).setUrl("http://localhost:8080/mancenter");
//		config.setManagementCenterConfig(centerConfig);
//		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

	}

}
