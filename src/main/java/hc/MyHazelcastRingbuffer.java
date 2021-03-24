package hc;

import java.util.Map;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MemberAttributeConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.ringbuffer.Ringbuffer;

public class MyHazelcastRingbuffer {
	public static void main(String[] args) throws Exception {
		Config config =new Config();
		ManagementCenterConfig centerConfig = new ManagementCenterConfig();
		centerConfig.setScriptingEnabled(true);

		config.setClusterName("mancenter");
//		ManagementCenterConfig manCenterCfg = new ManagementCenterConfig();
//		manCenterCfg.setEnabled(true).setUrl("http://localhost:8080/mancenter");
		config.setManagementCenterConfig(centerConfig);
		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
//		Map<Integer, String> data = hazelcastInstance.getMap("produits");
//		for (int i =0; i<=10000; i++) {
//			data.put(i, "Produit " + i);
//		}
		Ringbuffer<String> ringbuffer = hazelcastInstance.getRingbuffer("rb");
		ringbuffer.add("Item 1");
		ringbuffer.add ("Item 2");
		long sequence = ringbuffer.headSequence();
		while (true) {
			String item = ringbuffer.readOne(sequence);
			sequence++;
		}
	}
}
