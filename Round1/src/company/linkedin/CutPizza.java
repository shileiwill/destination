package company.linkedin;

import java.util.Comparator;
import java.util.PriorityQueue;

// 一个pizza 切n刀 问最多能切几块 注意不一定要所有切线都在同一个交点
// 在切第n刀的时候 最多能和之前的n-1刀相交 这个多出来的1刀多制造出了n块pizza 
public class CutPizza {

	public static void main(String[] args) {
		CutPizza cp = new CutPizza();
		System.out.println(cp.max(4));
		
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
	
	int max(int N) {
		int[] hash = new int[N + 1];
		
		hash[0] = 1;
		for (int i = 1; i < N + 1; i++) {
			hash[i] = hash[i - 1] + i;
		}
		
		return hash[N];
	}
}

// 实现一个maxstack 功能有pop peek peekMax popMax, popMax要求O(1) 应该是挂在这上了 最后没想出来popMax O(1)怎么做
class MaxStack {
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
		heap.remove(res); // Remove from heap
		top = top.prev;
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

class Node {
	Node prev = null;
	Node next = null;
	int val = -1;
	
	Node(int val) {
		this.val = val;
	}
}