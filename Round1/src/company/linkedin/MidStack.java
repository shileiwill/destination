package company.linkedin;

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
		for (int i = 0; i <= mid; i++) {
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
}

class Node {
	Node prev;
	Node next;
	int val;
	
	Node(int val) {
		this.val = val;
	}
}