package leetcode4.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 170. Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
 */
public class TwoSum {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    // Add the number to an internal data structure.
	public void add(int number) {
	    map.put(number, map.getOrDefault(number, 0) + 1);
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for (int key : map.keySet()) {
    	    int toFind = value - key;
    	    if (toFind == key && map.getOrDefault(toFind, 0) >= 2) { // Make sure there are at least 2
    	        return true;
    	    }
    	    if (toFind != key && map.containsKey(toFind)) {
    	        return true;
    	    }
	    }
	    
	    return false;
	}
}