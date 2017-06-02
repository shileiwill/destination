package company.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
/**
 * rotated array很简单啊，就是比如1秒钟限制3个request.
我维护一个long int array 长度为3，里面存着最近3个request的访问时间戳，一个指针指向这3个中第一个进来的request。
有新的请求，我就检查指针指向的request是不是已经是1秒以前的，如果是则请求成功，新请求的时间戳覆盖指针指向。然后指针+1再mod，就rotated了。
 */
public class RateLimiter {
	int[] arr = null;
	int pos = -1;
	
	// Max Query per second is N.
	RateLimiter(int N) {
		arr = new int[N];
		Arrays.fill(arr, -1);
		pos = 0;
	}
	
	boolean query(int timestamp) {
		if (arr[pos] == -1) { // 还没满呢，继续往前推进
			arr[pos] = timestamp;
			pos = (pos + 1) % arr.length;
			return true;
		} else { // Pos is the oldest
			if (timestamp - arr[pos] > 60) {
				arr[pos] = timestamp; // Too old, update
				pos = (pos + 1) % arr.length;
				return true;
			} else {
				// Reject
				return false;
			}
		}
	}
	
	public static void main(String[] args) {
		RateLimiter limiter = new RateLimiter(3);
		
		limiter.query(1);
		limiter.query(5);
		limiter.query(8);
		limiter.query(10);
		limiter.query(16);
		limiter.query(19);
		limiter.query(50);
		limiter.query(68);
		
		limiter.query(78);
		limiter.query(88);
		limiter.query(98);
	}
}
/**
 * 359. Design a logger system that receive stream of messages along with its timestamps, 
 * each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.
 */
class Logger {

    Map<String, Integer> map = new HashMap<String, Integer>();
    /** Initialize your data structure here. */
    public Logger() {
        
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message)) {
            map.put(message, timestamp);
            return true;
        } else {
            int last = map.get(message);
            if (timestamp - last >= 10) {
                map.put(message, timestamp);
                return true;
            } else {
                return false;
            }
        }
    }
    
    /** 这个貌似是hit counter啊
     * 问的是dropbox经典的那道log request rate的题。就是让写一个function来决定一个IP address在过去一秒/分钟里是否made more than 100 requests
我就傻白甜地先说了比较intuitive的queue的方法，在需要optimize的时候又写了circular buffer的方法
     */
    Map<String, Queue<Integer>> map1 = new HashMap<String, Queue<Integer>>();
    Map<String, List<int[]>> map2 = new HashMap<String, List<int[]>>(); // Use Circular Array to save space
    // 1 times array, and 1 counts array. refer to HitCounter
    
    void add(String IP, int timestamp) {
    	if (!map1.containsKey(IP)) {
    		map1.put(IP, new LinkedList<Integer>());
    	}
    	
    	map1.get(IP).offer(timestamp);
    }
    
    void add2(String IP, int timestamp) { //时间戳是一直变大的
    	if (!map2.containsKey(IP)) {
    		List<int[]> list = new ArrayList<int[]>();
    		list.add(new int[60]);
    		list.add(new int[60]);
    		map2.put(IP, list); // We care only 60 seconds, which are 60 buckets. How many request in this second
    	}
    	
    	int[] times = map2.get(IP).get(0);
    	int[] counts = map2.get(IP).get(1);
    	
    	int pos = timestamp % 60;
    	
    	if (times[pos] != timestamp) {
    		times[pos] = timestamp;
    		counts[pos] = 1;
    	} else {
    		counts[pos]++;
    	}
    }
    
    boolean isMoreThan100(String IP, int now) {
    	if (!map1.containsKey(IP)) {
    		return false;
    	}
    	
    	Queue<Integer> queue = map1.get(IP);
    	while (!queue.isEmpty()) {
    		if (now - queue.peek() > 60) {
    			queue.poll(); // Throw away
    		}
    	}
    	
    	return queue.size() > 100;
    }
    
    boolean isMoreThan100_2(String IP, int now) {
    	if (!map2.containsKey(IP)) {
    		return false;
    	}
    	
    	int[] times = map2.get(IP).get(0);
    	int[] counts = map2.get(IP).get(1);
    	
    	int count = 0;
    	for (int i = 0; i < 60; i++) {
    		if (now - times[i] < 60) {
    			count += counts[i];
    		}
    	}
    	
    	return count > 100;
    }
}