package hc;

import java.util.Map;
import java.util.Random;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class MembreHazelcast2 {

	public static void main(String[] args) {
		Config config = new Config(); // objet Config de Hazelcast (par défaut pour le moment)
		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
		// c'est une instance Hazelcast, soit un membre Hazelcst, un noeud du Cluster Hazelcast
		Map<Integer, String> data = hazelcastInstance.getMap("clients");
		// on cherche une map "clients". S'il n'existe pas, elle est créée
		Random random = new Random();
		for (int i =0; i<1000; i++) {
			int key = random.nextInt(1000000);
			data.put(key, "Client " + key);
			
		}
		data.put(3, "Ahmed " );
		data.put(4, "Mohamed " );
		
		System.out.println("Client 3=>" + data.get(3));
		System.out.println("Client 4=>" + data.get(4));
		System.out.println("Size = " + data.size());
	}

}
