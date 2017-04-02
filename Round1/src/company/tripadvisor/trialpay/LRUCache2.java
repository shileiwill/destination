package company.tripadvisor.trialpay;

import java.util.HashMap;
import java.util.Map;

public class LRUCache2 {
	public static void main(String[] args) {
		int[] arr1 = {3, 5, 6, 8, 9};
		int[] arr2 = {1, 2, 4, 5, 8};
		
		int pos1 = 0, pos2 = 0;
		while (pos1 < arr1.length && pos2 < arr2.length) {
			int val1 = arr1[pos1];
			int val2 = arr2[pos2];
			
			if (val1 == val2) {
				pos1++;
				pos2++;
			} else if (val1 < val2) {
				System.out.println(val1);
				pos1++;
			} else {
				System.out.println(val2);
				pos2++;
			}
		}
		
		while (pos1 < arr1.length) {
			int val1 = arr1[pos1];
			System.out.println(val1);
			pos1++;
		}
		
		while (pos2 < arr2.length) {
			int val2 = arr2[pos2];
			System.out.println(val2);
			pos2++;
		}
	}
	Map<Integer, Node2> map = new HashMap<Integer, Node2>();
	Node2 least = null;
	Node2 recent = null;
	int capacity = 0;
	
	LRUCache2(int capacity) {
		this.capacity = capacity;
	}
	
	void put(int key, int val) {
		if (map.containsKey(key)) {
			// Already exist
			Node2 node = map.get(key);
			removeNode(node);
			node.val = val;
			setRecentUsed(node);
		} else {
			Node2 node = new Node2(key, val);
			if (map.size() == capacity) {
				removeNode(least);
				map.remove(least.key);
			}
			map.put(key, node);
			setRecentUsed(node);
		}
	}
	
	int get(int key) {
		if (!map.containsKey(key)) {
			return -1;
		}
		
		Node2 node = map.get(key);
		removeNode(node);
		setRecentUsed(node);
		return node.val;
	}
	
	void removeNode(Node2 node) {
		Node2 prev = node.prev;
		Node2 next = node.next;
		
		if (prev == null) { // First element
			least = node.next;
		} else {
			prev.next = node.next;
		}
		
		if (next == null) {
			recent = node.prev;
		} else {
			next.prev = node.prev;
		}
	}
	
	void setRecentUsed(Node2 node) {
		node.next = null;
		node.prev = null;
		
		if (recent == null) {
			least = node;
			recent = node;
		} else {
			recent.next = node;
			node.prev = recent;
			recent = node;
		}
	}
}
