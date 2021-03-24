package hc;

import java.util.Map;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;


public class ClientHazelcast {

	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
		HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		Map<Integer, String> data = client.getMap("clients");
		System.out.println("Client 4=>" + data.get(4));
		System.out.println("Map Size:" + data.size ());

	}

}
