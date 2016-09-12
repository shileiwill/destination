package cc150.chapter3.stacks.queues;

import java.util.Stack;

public class QueueViaStack2 {

	public static void main(String[] args) {
		QueueViaStack2 queue = new QueueViaStack2();
		queue.enqueue(1);
		queue.enqueue(2);
		System.out.println(queue.dequeue());
		queue.enqueue(3);
		System.out.println(queue.dequeue());
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(6);
		System.out.println(queue.dequeue());
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
