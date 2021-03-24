package hc;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class MembreHazelcast {

	public static void main(String[] args) {
		Config config = new Config(); // objet Config de Hazelcast (par défaut pour le moment)
		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
		// c'est une instance Hazelcast, soit un membre Hazelcst, un noeud du Cluster Hazelcast
		Map<Integer, String> data = null;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println(">");
			String command = scanner.nextLine();
			String[] items = command.split(" ");
			if (items[0].equals("map")) {
				data = hazelcastInstance.getMap("map");
				System.out.println("map => " +items[1]);
			}
			else if (items[0].equals("set")) {
				data.put(Integer.parseInt(items[1]), items[2]);
			}
			else if (items[0].equals("get")) {
				System.out.println(data.get(Integer.parseInt(items[1])));

			}
			else if (items[0].equals("size")) {
				System.out.println(data.size());

			}
		}
	}

}
