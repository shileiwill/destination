package chapter8.frequent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class TwoSum3 {

	public static void main(String[] args) {
		TwoSum3 ts3 = new TwoSum3();
		ts3.add(1); 
		ts3.add(3); 
		ts3.add(5);
		System.out.println(ts3.find(4));
		System.out.println(ts3.find(7));
	}

    ListNode first = null;
    ListNode last = null;
    
    // Add the number to an internal data structure.
	public void add(int number) {
	    ListNode node = new ListNode(number);
	    if (first == null) {
	        first = node;
	        last = node;
	    } else {
	        ListNode cur = first;
	        while (cur != null) {
	            // Find the first element which is larger than node
	            if (cur.val >= number) {
	                ListNode previousNode = cur.prev;
	                if (previousNode == null) {// Will be the first element
	                    node.next = cur;
	                    cur.prev = node;
	                    first = node;
	                } else {
	                    previousNode.next = node;
	                    node.prev = previousNode;
	                    node.next = cur;
	                    cur.prev = node;
	                }
	                break;
	            }
	            cur = cur.next;
	        }
	        
	        if (cur == null) { // number is larger than every one
	            last.next = node;
	            node.prev = last;
	            last = node;
	        }
	    }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    ListNode left = first;
	    ListNode right = last;
	    
	    while (left != right) {
	        int curSum = left.val + right.val;
	        if (curSum == value) {
	            return true;
	        } else if (curSum < value) {
	            left = left.next;
	        } else {
	            right = right.prev;
	        }
	    }
	    
	    return false;
	}
	
	class ListNode {
	    ListNode prev;
	    ListNode next;
	    int val;
	    
	    ListNode(int val) {
	        this.val = val;
	    }
	}
}

class TwoSumHashMapVersion {

    List<Integer> store = new ArrayList<Integer>();
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    // Add the number to an internal data structure.
	public void add(int number) {
        store.add(number);
        if (map.containsKey(number)) {
            map.put(number, map.get(number) + 1);
        } else {
            map.put(number, 1);
        }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    if (store.size() < 2) {
	        return false;
	    }
	    for (int num : store) {
	        int remainder = value - num;

	        if (map.containsKey(remainder)) {
	            if (remainder == num) { // Make sure it contains more than 1
	                if (map.get(remainder) == 1) {
	                    continue; // 不要直接stop, 给后边的数一些机会
	                } else {
	                    return true;
	                }
	            } 
	            return true;
	        }
	    }
	    return false;
	}
}