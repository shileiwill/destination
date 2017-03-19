package cc150.chapter3.stacks.queues;

import java.util.Stack;

public class QueueViaStack2 {

	public static void main(String[] args) {
//		QueueViaStack2 queue = new QueueViaStack2();
//		queue.enqueue(1);
//		queue.enqueue(2);
//		System.out.println(queue.dequeue());
//		queue.enqueue(3);
//		System.out.println(queue.dequeue());
//		queue.enqueue(4);
//		queue.enqueue(5);
//		queue.enqueue(6);
//		System.out.println(queue.dequeue());
		String s1 = "a";
		String s2 = "a"; // String literal can be interned
		String s3 = new String("a");
		String s4 = new String("a");
		System.out.println(s1 == s3);
		System.out.println(s3 == s4);
		
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(1);
		int i3 = 1;
		System.out.println(i1 == i2);
		System.out.println(i1 == i3);
		
		QueueViaStack2 q = new QueueViaStack2();
		int[] N = {3, 1, 8, 9, 6, 10, 13};
		int res = q.findPeak(N);
		System.out.println(res);
	}
	
	int findPeak(int[] N) {
		int max = N[0];
		
		for (int i = 1; i < N.length; i++) {
			if (N[i] > max) {
				max = N[i];
				if (checkTailing(N, i)) {
					return max;
				}
			}
		}
		
		return -1;
	}
	
	boolean checkTailing(int[] N, int pos) {
		int candidate = N[pos];
		for (int i = pos + 1; i < N.length; i++) {
			if (N[i] <= candidate) {
				return false;
			}
		}
		
		return true;
	}
	
	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();

	void enqueue(int val) {
		stack1.push(val);
	}
	
	int dequeue() {
		if (stack2.isEmpty()) {
			//shiftToStack2();
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}
}
