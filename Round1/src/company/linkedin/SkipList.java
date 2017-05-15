package company.linkedin;

import java.util.Random;
import java.util.Stack;

/**
 * All of the values are integers, larger than 0.
 * Values for each layer are guaranteed to be in ascending order and be integers, larger than 0.
 *
 * H --------> 8 ------------------------------------------------------------> null
 * |           |
 * H --------> 8 ----------> 28 ---------> 52 -------------------------------> null
 * |           |             |             |
 * H --------> 8 --> 25  --> 28 ---------> 52 ---------------->  81----------> NULL
 * |           |     |       |             |                     |
 * H ----> 5-> 8 --> 25  --> 28 --> 33 --> 52 --> 55 --> 70 -->  81 --> 83 --> NULL
 *
 * Grid List :
 *   go from home toward right or down side.
 *   value of each layer is ascending
 *   each node's below points to the node on the blow layer with the same value
 * Benefit:
 *   search quickly
 */
public class SkipList {

	public static void main(String[] args) {

	}

	static int hops = 0;
	static int searchNodes(Node start, int target) {
		if (start == null || start.val >= target) {
			return hops; // Either to the end, or next is >= target
		}
		
		// Go right
		if (start.right != null && start.right.val <= target) {
			hops++;
			return searchNodes(start.right, target);
		}
		
		if (start.below != null && start.below.val <= target) {
			hops++;
			return searchNodes(start.below, target);
		}
		
		return hops;
	}
	
	static class Node {
		int val;
		Node right;
		Node below;
	}
}

class CustomizedSkipList {
	static class Node {
		Node prev, next;
		Node up, down;
		boolean isHead;
		boolean isTail;
		int val;
		
		Node(int val) {
			this.val = val;
		}
	}
	
	int height;
	Node head, tail;
	Random ran;
	
	CustomizedSkipList() {
		this.height = 1;
		this.head = new Node(Integer.MAX_VALUE);
		this.tail = new Node(Integer.MAX_VALUE);
		this.head.isHead = true;
		this.tail.isTail = true;
		this.ran = new Random();
	}
	
	// Result will always be in the bottom line
	Node search(int target) {
		Node mover = head;
		while (true) {
			while (!mover.next.isTail && mover.next.val <= target) {
				mover = mover.next;
			} // Next will be too big, stop here
			
			// Go down
			if (mover.down != null) {
				mover = mover.down;
			} else {
				break; // Last line, done, no matter find or not
			}
		}
		
		if (!mover.isHead && !mover.isTail && mover.val == target) {
			return mover;
		} else {
			return null;
		}
	}
	
	void remove(int target) {
		Node node = search(target);
		if (node == null) {
			return;
		}
		
		while (node != null) {
			// Dont worry, it will always have prev and next, since there are head and tail
			node.prev.next = node.next;
			node.next.prev = node.prev;
			node.next = null;
			node.prev = null;
			node.down = null;
			
			node = node.up;
		}
	}
	
	// Most important and difficult, insert
	void insert(int target) {
		Node node = search(target);
		
		if (node != null) { // If it already exists, easy, just update the value
			while (node != null) {
				node.val = target;
				node = node.up;
			}
			return;
		}
		
		// Search again, but remember all last vertical nodes
		Stack<Node> stack = new Stack<Node>();
		Node mover = head;
		
		while (true) {
			while (!mover.next.isTail && mover.next.val <= target) {
				mover = mover.next; // Mover horizontally
			}
			
			stack.push(mover);
			if (mover.down != null) {
				mover = mover.down;
			} else {
				break;
			}
		}
		
		// Eventually, mover is in the bottom
		int level = 1;
		Node lastNewNode = new Node(target);
		Node lastNode = stack.pop();
		insertNode(lastNode, lastNewNode); // Must, at least add at the bottom line
		
		while (ran.nextInt(2) == 0) {
			if (level >= height) { //Need to add a new line on top
				Node newHead = new Node(Integer.MAX_VALUE);
				Node newTail = new Node(Integer.MAX_VALUE);
				
				newHead.isHead = true;
				newTail.isTail = true;
				
				newHead.next = newTail;
				newTail.prev = newHead;
				
				head.up = newHead;
				newHead.down = head;
				
				tail.up = newTail;
				newTail.down = tail;
				
				head = newHead;
				tail = newTail;
				
				stack.push(head);
				height++;
			}
			
			Node newNode = new Node(target);
			newNode.down = lastNewNode;
			lastNewNode.up = newNode;
			
			lastNode = stack.pop();
			insertNode(lastNode, newNode);
			
			lastNewNode = newNode;
			level++; // 升高
		}
	}
	
	void insertNode(Node lastNode, Node newNode) {
		Node prev = lastNode.prev;
		Node next = lastNode.next;
		
		prev.next = newNode;
		newNode.prev = prev;
		next.prev = newNode;
		newNode.next = next;
	}
}