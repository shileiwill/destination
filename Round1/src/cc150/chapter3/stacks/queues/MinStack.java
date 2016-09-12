package cc150.chapter3.stacks.queues;

import java.util.Stack;

public class MinStack {

	public static void main(String[] args) {

	}

	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>(); // min
	
	void push(int val) {
		stack1.push(val);
		if (stack2.isEmpty()) {
			stack2.push(val);
		} else {
			int prev = stack2.peek();
			stack2.push(Math.min(prev, val));
		}
	}
	
	int pop() {
		stack2.pop();
		return stack1.pop();
	}
	
	int min() {
		return stack2.peek();
	}
}

// More space efficient
class MinStack2 {

	Stack<Integer> stack1 = new Stack<Integer>();
	Stack<Integer> stack2 = new Stack<Integer>(); // min
	
	void push(int val) {
		if (val <= min()) {
			stack2.push(val);
		}
		stack1.push(val);
	}
	
	int pop() {
		int res = stack1.pop();
		if (res == min()) {
			stack2.pop();
		}
		return res;
	}
	
	int min() {
		if (stack2.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return stack2.peek();
		}
	}
}
