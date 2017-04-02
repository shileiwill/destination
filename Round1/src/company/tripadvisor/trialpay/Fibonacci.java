package company.tripadvisor.trialpay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import chapter4.linkedlist.ListNode;

public class Fibonacci {

	public static void main(String[] args) {
//		Fibonacci fib = new Fibonacci();
//		int res = fib.getFib(6);
//		System.out.println(res);
		
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		
//		Fibonacci rd = new Fibonacci();
//		rd.removeFibonacciIndexedNode(node4);
		
		FibIterator fi = new FibIterator();
		for (int i = 0; i < 10; i++) {
			System.out.print(fi.next() + "===");
		}
	}

	// Get Nth Fibonacci number
	int getFib(int N) {
		if (N == 0) {
			return 0;
		}
		
		if (N == 1) {
			return 1;
		}
		
		return getFib(N - 1) + getFib(N - 2);
	}
	
	ListNode removeFibonacciIndexedNode(ListNode node) {
		int len = getLength(node);
		
		List<Integer> res = getFibIndex(len);
		
		ListNode root = removeFib(node, res, len);
		
		while (root != null) {
			System.out.println(root.val);
			root = root.next;
		}
		
		return root;
	}
	
	ListNode removeFib(ListNode node, List<Integer> res, int len) {
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		
		for (int i = 0; i < len; i++) {
			if (!res.contains(i)) {
				cur.next = node;
				cur = cur.next;
			}
			node = node.next;
		}
		cur.next = null;
		
		return dummy.next;
	}
	
	List<Integer> getFibIndex(int len) {
		List<Integer> res = new ArrayList<Integer>();
		
		res.add(0);
		if (len <= 1) {
			return res;
		}
		
//		res.add(1);
//		if (len <= 2) {
//			return res;
//		}
		
		int next = 1;
		while (next < len) {
			res.add(next);
			next = res.get(res.size() - 1) + res.get(res.size() - 2);
		}
		
		return res;
	}
	
	int getLength(ListNode node) {
		int len = 0;
		while (node != null) {
			len++;
			node = node.next;
		}
		return len;
	}
}

class FibIterator {
	int num1 = -1;
	int num2 = -1;

	public int next() {
		if (num1 == -1) {
			num1 = 0;
			return num1;
		} else if (num2 == -1) {
			num2 = 1;
			return num2;
		} else {
			int num3 = num1 + num2;
			num1 = num2;
			num2 = num3;
			return num3;
		}
	}
	
}