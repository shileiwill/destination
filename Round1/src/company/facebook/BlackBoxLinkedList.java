package company.facebook;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 给一个linkedlist，里面的element都排序好了，但是是一个blackbox，
 * 有三个function可以调用。pop()随机pop出最前面或最后面的element，
 * peek()随机偷看最前面或最后面的element，
 * isEmpty()回传linkedlist是不是空了。
 * 问设计一个资料结构，list或是array都可以，把linkedlist里面所有的element都拿出来，并保持他们的排序。followup是如果不能用peek()该怎么做。
	My thinking: if I got element A, and next element B is smaller than A, then A is from the tail of the list; 
	otherwise, A is from the head of the list.
 */
public class BlackBoxLinkedList {

	public static void main(String[] args) {
		int[] arr = {4, 2, 1, 6, 3, 8, 9};
		boolean res = increasing(arr);
		System.out.println(res);
	}
	
	List<Integer> blackList(BlackList<Integer> black) {
		LinkedList<Integer> left = new LinkedList<Integer>();
		LinkedList<Integer> right = new LinkedList<Integer>();
		
		while (!black.isEmpty()) {
			Integer now = black.pop();
			
			if (!black.isEmpty()) {
				if (now < black.peek()) { // now is from left of black list
					left.addLast(now);
				} else { // now is from right, need to wait
					right.addFirst(now);
				}
			} else {
				left.addLast(now);
			}
		}
		
		return left + right;
	}

	/**
	 * 一个数组内要是存在至少三个升序的数（array[x] < array[y] < array[z], x < y < z）就返回true
	// Brute Force using 3 for loops
	 */
	static boolean increasing(int[] arr) {
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		for (int i = 0; i < arr.length; i++) {
			Map.Entry<Integer, Integer> floorEntry = map.floorEntry(arr[i]);
			
			if (floorEntry == null) {
				map.put(arr[i], 0);
			} else {
				int smallerCount = floorEntry.getValue();
				if (smallerCount >= 1) {
					return true;
				} else {
					map.put(arr[i], smallerCount + 1);
				}
			}
		}
		
		return false;
	}
}
