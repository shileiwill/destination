package cc150.chapter3.stacks.queues;

import java.util.EmptyStackException;
import java.util.Stack;

public class SortStack {

	public static void main(String[] args) {

	}

	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>();
	
	boolean isEmpty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}
	
	int peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		shift(stack2, stack1);
		return stack1.peek();
	}
	
	void shift(Stack<Integer> from, Stack<Integer> to) {
		while (!from.isEmpty()) {
			to.push(from.pop());
		}
	}
	
	int pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		shift(stack2, stack1);
		return stack1.pop();
	}
	
	void push(int val) {
		if (stack1.isEmpty()) { // Just push
			stack1.push(val);
		} else {
			while (!stack1.isEmpty() && val > stack1.peek()) {
				stack2.push(stack1.pop());
			}
			stack1.push(val);
		}
	}
}
