package company.linkedin;

import java.util.HashMap;
import java.util.Map;

// Mid is mid of index, not median
public class MidStack {

	public static void main(String[] args) {
		MidStack ms = new MidStack();
		ms.push(1);
		System.out.println("1 : " + ms.peekMid());
		ms.push(2);
		System.out.println("2 : " + ms.popMid());
		ms.push(3);
		ms.push(4);
		System.out.println("3 : " + ms.pop());
		System.out.println("4 : " + ms.peekMid());
		ms.push(5);
		ms.push(6);
		System.out.println("5 : " + ms.popMid());
		ms.push(7);
		ms.push(8);
		System.out.println("6 : " + ms.peekMid());
		
		System.out.println("================");
		
		MidStack2 ms2 = new MidStack2();
		ms2.push(1);
		System.out.println("1 : " + ms2.peekMid());
		ms2.push(2);
		System.out.println("2 : " + ms2.popMid());
		ms2.push(3);
		ms2.push(4);
		System.out.println("3 : " + ms2.pop());
		System.out.println("4 : " + ms2.peekMid());
		ms2.push(5);
		ms2.push(6);
		System.out.println("5 : " + ms2.popMid());
		ms2.push(7);
		ms2.push(8);
		System.out.println("6 : " + ms2.peekMid());
	}

	Node dummy = new Node(-1);
	Node cur = dummy;
	int len = 0;
	
	void push(int val) {
		Node newNode = new Node(val);
		newNode.prev = cur;
		cur.next = newNode;
		cur = newNode;
		len++;
	}
	
	int pop() {
		if (len == 0) {
			throw new RuntimeException("Empty Stack");
		}
		
		int res = cur.val;
		
		Node prev = cur.prev;
		prev.next = null;
		cur = prev;
		
		len--;
		return res;
	}
	
	int peekMid() {
		if (len == 0) {
			throw new RuntimeException("Empty Stack");
		}
		
		int mid = len / 2;
		Node node = dummy;
		for (int i = 0; i <= mid; i++) { // PeekMid and PopMid are O(N), why not use a HashMap<Integer, Node>
			node = node.next;
		}
		
		return node.val;
	}
	
	int popMid() {
		if (len == 0) {
			throw new RuntimeException("Empty Stack");
		}
		
		int mid = len / 2;
		Node node = dummy;
		for (int i = 0; i <= mid; i++) {
			node = node.next;
		}
		
		int res = node.val;
		Node prev = node.prev;
		Node next = node.next;
		
		if (prev == null) {
			// Impossible, since there is dummy node
		} else {
			prev.next = node.next;
		}
		
		if (next == null) {
			// Nothing to do, NO! move cursor/top
			cur = cur.prev;
		} else {
			next.prev = node.prev;
		}
		
		len--;
		return res;
	}
	
	static class Node {
		Node prev;
		Node next;
		int val;
		
		Node(int val) {
			this.val = val;
		}
	}
}

// 这一招行不通啊，因为各种pop以后，Map里的index不是最开始的有序的index了
class MidStack2 {
	Map<Integer, Node> map = new HashMap<Integer, Node>();
	int index = 0;
	Node top = null;
	
	void push(int val) {
		Node newNode = new Node(val);
		map.put(index, newNode);
		index++;
		
		if (top == null) {
			top = newNode;
		} else {
			top.next = newNode;
			newNode.prev = top;
			top = top.next;
		}
	}
	
	int pop() {
		if (top == null) {
			return -1;
		}
		
		int res = top.val;
		index--;
		map.remove(index);
		
		Node prev = top.prev;
		prev.next = null;
		top = prev;
		
		return res;
	}
	
	int peekMid() {
		int midIndex = map.size() / 2;
		System.out.println(midIndex);
		return map.get(midIndex).val;
	}
	
	int popMid() {
		int midIndex = map.size() / 2;
		Node midNode = map.get(midIndex);
		
		Node prev = midNode.prev;
		Node next = midNode.next;
		
		if (prev != null) {
			prev.next = next;
		}
		
		if (next != null) {
			next.prev = prev;
		} else {
			top = prev;
		}
		
		map.remove(midIndex);
		
		return midNode.val;
	}
}