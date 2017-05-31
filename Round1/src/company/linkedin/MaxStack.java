package company.linkedin;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

//实现一个maxstack 功能有pop peek peekMax popMax, popMax要求O(1) 应该是挂在这上了 最后没想出来popMax O(1)怎么做
public class MaxStack {
	public static void main(String[] args) {
		MaxStack ms = new MaxStack();
		ms.push(4);
		System.out.println(ms.peek());
		ms.push(1);
		System.out.println(ms.peekMax());
		ms.push(24);
		System.out.println(ms.popMax());
		System.out.println(ms.pop());
		ms.push(43);
		System.out.println(ms.peek());
		ms.push(14);
		System.out.println(ms.peekMax());
	}
	
	Node top = new Node(-1);
	PriorityQueue<Node> heap = new PriorityQueue<Node>(new Comparator<Node>(){
		public int compare(Node n1, Node n2) {
			return n2.val - n1.val; // Max Heap
		}
	});
	
	boolean isEmpty() {
		return heap.isEmpty(); // Or use top.prev == null
	}
	
	void push(int val) {
		Node newNode = new Node(val);
		
		top.next = newNode;
		newNode.prev = top;
		top = newNode;
		
		heap.offer(newNode);
	}
	
	Integer pop() {
		if (isEmpty()) {
			return null;
		}
		
		int res = top.val;
		heap.remove(res); // Remove from heap. It takes O(N) to find the element and O(logN) to heapify again. 
		top = top.prev; // To make this faster, add an auxiliary HashMap to remember <Object, Index> by implementing your own Heap
		top.next = null;
		
		return res;
	}
	
	Integer peek() {
		if (isEmpty()) {
			return null;
		}
		return top.val;
	}
	
	Integer peekMax() {
		if (isEmpty()) {
			return null;
		}
		return heap.peek().val;
	}
	
	Integer popMax() {
		if (isEmpty()) {
			return null;
		}
		
		Node maxNode = heap.poll();
		
		Node prev = maxNode.prev;
		Node next = maxNode.next;
		
		if (prev == null) {
			
		} else {
			prev.next = maxNode.next;
		}
		
		if (next == null) {
			top = maxNode.prev;
		} else {
			next.prev = maxNode.prev;
		}
		
		return maxNode.val;
	}
}

class Node implements Comparable<Node> {
	Node prev = null;
	Node next = null;
	int val = -1;
	
	Node(int val) {
		this.val = val;
	}
	
	public int compareTo(Node that) {
		return that.val - this.val; // Sort from big to small
	}
}

// You can replace PriorityQueue with TreeSet, which also provides O(1) getMax
class MaxStackTreeSet {
	TreeSet<Node> treeSet = new TreeSet<Node>();
	
	void add(int val) {
		Node node = new Node(val);
		treeSet.add(node);
	}
	
	int popMax() { // First is the biggest
		Node max = treeSet.pollFirst();
		return max.val;
	}
}
