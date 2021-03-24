package hc;

import java.util.Map;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MemberAttributeConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class MyHazelcastMemberApp {
	public static void main(String[] args) {
		Config config =new Config();
		ManagementCenterConfig centerConfig = new ManagementCenterConfig();
		centerConfig.setScriptingEnabled(true);

		config.setClusterName("mancenter");
//		ManagementCenterConfig manCenterCfg = new ManagementCenterConfig();
//		manCenterCfg.setEnabled(true).setUrl("http://localhost:8080/mancenter");
		config.setManagementCenterConfig(centerConfig);
		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
		Map<Integer, String> data = hazelcastInstance.getMap("produits");
		for (int i =0; i<=10000; i++) {
			data.put(i, "Produit " + i);
		}

	}
}
