package chapter7.dataStructure;

public class ImplementQueueByLinkedList {
	public static void main(String[] args) throws Exception {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		
		Queue queue = new Queue();
		queue.enqueue(1);
		queue.enqueue(2);
		int val1 = queue.dequeue();
		System.out.println(val1);
		
		int val2 = queue.dequeue();
		System.out.println(val2);
		
		queue.enqueue(3);
		queue.enqueue(4);
		
		int val3 = queue.dequeue();
		System.out.println(val3);
	}
	
	// My own solution is a little complicated
	Node dummy = new Node(0);
	Node head = dummy;
	public void enqueue(int item) {
		Node node = new Node(item);
		head.next = node;
		head = head.next;
	}
	
	// Need to change head if last element is to be deleted
	public int dequeue() throws Exception {
		Node delNode = dummy.next;

		if (delNode == null) {
			throw new Exception("No element to delete");
		}

		dummy.next = dummy.next.next;
		
		if (delNode.next == null) {
			head = findLastElement();
		}
		
		return delNode.val;
	}
	
	Node findLastElement() {
		Node cursor = dummy;
		while (cursor.next != null) {
			cursor = cursor.next;
		}
		return cursor;
	}
}	

// An easier way is to maintain a head and a tail, change head when dequeue
class Queue {
	Node first, last;
	public void enqueue(int item) {
		if (first == null) { // Queue is empty
			first = new Node(item);
			last = first;
		} else {
			last.next = new Node(item);
			last = last.next;
		}
	}
	
	public int dequeue() {
		if (first == null) {
			return -1; // There is no element to delete
		}
		int item = first.val;
		first = first.next; // This is smart
		return item;
	}
}

class Node {
    int val;
    Node next;
    Node(int val) {
        this.val = val;
        this.next = null;
    }
}