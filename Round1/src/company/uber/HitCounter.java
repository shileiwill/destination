package company.uber;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 362. Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order 
(ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:
HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301); 
Follow up:
What if the number of hits per second could be very large? Does your design scale?
 */
class HitCounter2 {
    Queue<Integer> queue = null;
    /** Initialize your data structure here. */
    public HitCounter2() {
        queue = new LinkedList<Integer>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        queue.offer(timestamp);
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
            queue.poll();
        }
        return queue.size();
    }
}

public class HitCounter {
    int[] times = null;
    int[] counts = null;
    public HitCounter() {
        times = new int[300];
        counts = new int[300]; // 300 seconds buckets
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int index = timestamp % 300;
        
        if (times[index] != timestamp) {
            times[index] = timestamp;
            counts[index] = 1;
        } else {
            counts[index]++;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param now - The current timestamp (in seconds granularity). */
    public int getHits(int now) {
        int res = 0;
        for (int i = 0; i < 300; i++) {
            if (now - times[i] < 300) {
                res += counts[i];
            }
        }
        return res;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */