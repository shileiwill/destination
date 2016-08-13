package chapter8.frequent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array.

Find it.

 Notice

There is only one majority number in the array.

Have you met this question in a real interview? Yes
Example
Given [3,1,2,3,2,3,3,4,4,4] and k=3, return 3.
 * @author Lei
 *
 */
public class MajorityElement3 {

	    public int majorityNumber(ArrayList<Integer> nums, int k) {
	        // Maintain a hashmap to hold all possible k - 1 elements
	        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	        
	        for (int num : nums) {
	            if (map.containsKey(num)) { // If it is already in map, just count++
	                map.put(num, map.get(num) + 1);
	            } else {
	                if (map.size() == k - 1) { // Map is full
	                	
	                	// Must use Iterator to avoid ConcurrentModificationException
	                    Iterator it = map.entrySet().iterator();
	                    while (it.hasNext()) {
	                        Entry thisEntry = (Entry) it.next();
	                        int key = (int)thisEntry.getKey();
	                        int value = (int)thisEntry.getValue();
	                      
	                        value--;
	                        if (value == 0) {
	                        	// Must use iterator.remove() to avoid above exception
//	                            map.remove(new Integer(key));
	                        	it.remove();
	                        } else {
	                            map.put(key, value);
	                        }
	                    }

	                    // for (int key : map.keySet()) {
	                    //     int value = map.get(key);
	                    //     value--;
	                    //     if (value == 0) {
	                    //         map.remove(new Integer(key));
	                    //     } else {
	                    //         map.put(key, value);
	                    //     }
	                    // }
	                } else {
	                    map.put(num, 1);
	                }
	            }
	        }
	        
	        
	        // Clear Map's value
	        for (int key : map.keySet()) {
	            map.put(key, 0);
	        }
	        
	        // Find out the real count
	        for (int num : nums) {
	            if (map.containsKey(num)) {
	                map.put(num, map.get(num) + 1);
	            }
	        }
	        
	        // Then determine which is the winner
	        int winnerKey = 0;
	        int winnerValue = 0;
	        for (int key : map.keySet()) {
	            int value = map.get(key);
	            if (value > winnerValue) {
	                winnerKey = key;
	                winnerValue = value;
	            }
	        }
	        
	        return winnerKey;
	}
}
