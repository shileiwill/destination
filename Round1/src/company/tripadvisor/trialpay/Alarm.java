package company.tripadvisor.trialpay;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * alarm(boolean success, long timestamp, String requestName),意思是当一分钟之
内某个request的failure比例达到一定程度时fire alarm。先跟大叔讲了思路，大叔表
示赞同，然后开始撸，用了priorityqueue存Request(success,timestamp, name)的对
象，维护一个60秒的queue
 */
public class Alarm {
	// Very similar to LRU. The difference is rather than least recent used, it is sorted by time, so use a queue, first in first out
	
	LinkedList<Request> queue = new LinkedList<Request>(); // Save last 60 seconds
	Map<String, Integer> map = new HashMap<String, Integer>(); // Save only failed request
	int alarm = 10;
	
	void addRequest(Request req) {
		queue.addLast(req);
		if (!req.success) {
			map.put(req.requestName, map.getOrDefault(req.requestName, 0) + 1);
			if (map.get(req.requestName) >= alarm) {
				System.out.println(req.requestName + " failed too often");
			}
		}
	}
	
	void monitoring() {
		while (!queue.isEmpty()) {
			long curTime = System.currentTimeMillis();
			if (curTime - queue.peekFirst().timestamp > 60) { // Be careful with unit
				Request req = queue.peekFirst();
				if (!req.success) {
					map.put(req.requestName, map.get(req.requestName) - 1);
					
					if (map.get(req.requestName) == 0) {
						map.remove(req.requestName);
					}
				}
				
				queue.removeFirst();
			} else {
				break; // It doesn't need to go to latter part in queue
			}
		}
	}
	
	// Use a cron job to start monitoring method every second.
}

class Request {
	boolean success;
	long timestamp;
	String requestName;
	
	public int hashCode() {
		return requestName.hashCode();
	}
	
	boolean equals(Request req) {
		return this.requestName.equals(req.requestName);
	}
}