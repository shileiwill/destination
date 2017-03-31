package company.tripadvisor.trialpay;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

	public static void main(String[] args) {
		LRUCache cache = new LRUCache( 2 /* capacity */ );

		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println("1 : " + cache.get(1));       // returns 1
		cache.put(3, 3);    // evicts key 2
		System.out.println("-1 : " + cache.get(2));       // returns -1 (not found)
		cache.put(4, 4);    // evicts key 1
		System.out.println("-1 : " + cache.get(1));       // returns -1 (not found)
		System.out.println("3 : " + cache.get(3));       // returns 3
		System.out.println("4 : " + cache.get(4));       // returns 4
	}

	int capacity = 0;
	Node2 most = null;
	Node2 least = null;
	Map<Integer, Node2> map = new HashMap<Integer, Node2>();
	
	LRUCache(int capacity) {
		this.capacity = capacity;
	}
	
	int get(int key) {
		if (!map.containsKey(key)) {
			return -1; // Doesn't exist
		}
		Node2 node = map.get(key);
		remove(node);
		addToMostRecent(node);
		return node.val;
	}
	
	void put(int key, int val) {
		if (map.containsKey(key)) {
			Node2 node = map.get(key);
			remove(node);
			node.val = val;
			addToMostRecent(node);
		} else {
			if (map.size() == capacity) {
				map.remove(least.key);
				remove(least);
			}
			Node2 node = new Node2(key, val);
			map.put(key, node);
			addToMostRecent(node);
		}
	}
	
	void remove(Node2 node) {
		Node2 prev = node.prev;
		Node2 next = node.next;
		
		if (prev == null) {
			least = node.next;
		} else {
			prev.next = node.next;
		}
		
		if (next == null) {
			most = node.prev;
		} else {
			next.prev = node.prev;
		}
	}
	
	void addToMostRecent(Node2 node) {
		node.prev = null;
		node.next = null;
		
		if (most == null) {
			most = node;
			least = node;
		} else {
			most.next = node;
			node.prev = most;
			most = node;
		}
	}
}
class Node2 {
	Node2 prev;
	Node2 next;
	int key;
	int val;
	
	Node2(int key, int val) {
		this.key = key;
		this.val = val;
	}
}