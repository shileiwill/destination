package company.tripadvisor.trialpay;

import java.util.Iterator;
import java.util.Random;

import chapter4.linkedlist.ListNode;

public class LinkedListIterator implements Iterator<Integer> {
	ListNode node = null;
	public static void main(String[] args) {
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
		
		LinkedListIterator it = new LinkedListIterator(node1);
		
		System.out.println(it.getRandom());
	}

	LinkedListIterator(ListNode node1) {
		node = node1;
	}
	
	@Override
	public boolean hasNext() {
		return node != null;
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new RuntimeException("Empyt List");
		}
		int res = node.val;
		node = node.next;
		return res;
	}
	
	Random ran = new Random();
	// Reservoir Sampling
	int getRandom() {
		ListNode now = node;
		ListNode res = node;
		int count = 0;
		
		while (now != null) {
			if (ran.nextInt(++count) == 0) {
				res = now;
			}
			now = now.next;
		}
		
		return res.val;
	}
}
