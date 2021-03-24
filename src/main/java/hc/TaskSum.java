package hc;

import java.io.Serializable;
import java.util.concurrent.Callable;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.map.IMap;

public class TaskSum implements Callable<Integer>, Serializable, HazelcastInstanceAware  {
	public String inputData;
	private transient HazelcastInstance hazelcastInstance;
	@Override
	public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
		this.hazelcastInstance = hazelcastInstance;		
	}

	@Override
	public Integer call() throws Exception {
		IMap<Integer, Integer> iMap = hazelcastInstance.getMap(inputData);
		int result = 0;
		for (Integer key : iMap.localKeySet()) {
			System.out.println("Calculating for keys : " + key);
			result += iMap.get(key);
		}
		System.out.println("Local Result : " + result);
		return result;
	}

}
