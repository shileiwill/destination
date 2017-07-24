package company.uber;

import java.util.HashMap;
import java.util.Map;

/**
 * design a data structure which suport 
    insert(key, value).
    delete(key)
    update(key, value)
    max()  // 取出这个data structure 中最大的元素..
    所有的操作都是用O(1).  
 */
public class AllO1 {

	public static void main(String[] args) {

	}
	// Doesn't work
	Map<Integer, Node> map1 = new HashMap<Integer, Node>();
	Map<Integer, Node> map2 = new HashMap<Integer, Node>();
	Node dummy1 = new Node(-1, Integer.MIN_VALUE);
	Node top1 = dummy1;
	Node dummy2 = new Node(-1, Integer.MIN_VALUE);
	Node top2 = dummy2;
	
	void insert(int key, int value) {
		if (map1.containsKey(key)) {
			return; // No reInsert
		}
		
		Node newNode1 = new Node(key, value);
		top1.next = newNode1;
		newNode1.prev = top1;
		top1 = top1.next;
		map1.put(key, newNode1);
		
		Node newNode2 = top2.value > newNode1.value ? new Node(top2.value) : new Node(newNode1.value);
		top2.next = newNode2;
		newNode2.prev = top2;
		top2 = top2.next;
		map2.put(key, newNode2);
	}
	
	void delete(int key) {
		if (!map1.containsKey(key)) {
			return;
		}
		
		Node node1 = map1.get(key);
		Node node2 = map2.get(key);
		
		map1.remove(key); // 不行啊，不能只delete他自己，别的node的最大值可能依赖他
		map2.remove(key);
	}
	
	class Node {
		int key;
		int value;
		Node next;
		Node prev;
		
		Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
		
		Node(int value) {
			this.value = value;
		}
	}
	
	/*
	 * design 一个hashHeap. 具体的scenario 忘了， 好像是一堆task, 每个 task 有一个priority，
    当需要update 一个task 的priority 的时候， 用 hashHeap 来access 某一个element, 然后重新siftUp or siftDown, 来维持整个堆的特性。
	 */
}