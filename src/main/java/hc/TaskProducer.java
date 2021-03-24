package hc;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.cluster.Member;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.map.IMap;

public class TaskProducer {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient();
		String inputData = "input data";
		IMap<Integer, Integer> data = hazelcastInstance.getMap(inputData);
		for (int i =0; i<10000; i++) {
			data.put(i, i);
		}
		IExecutorService executorService = hazelcastInstance.getExecutorService("default");
		TaskSum taskSum = new TaskSum();
		taskSum.inputData = inputData;
		Map<Member, Future<Integer>> response = executorService.submitToAllMembers(taskSum);
		double reduceSum =0;
		for (Member member : response.keySet()) {
			System.out.println("************************");
			System.out.println(member.getAddress());
			System.out.println(member.getUuid());
			System.out.println(response.get(member).get());
			reduceSum += response.get(member).get();
			System.out.println("************************");
		}
		System.out.println("Total SUM = " + reduceSum);
	}
}
