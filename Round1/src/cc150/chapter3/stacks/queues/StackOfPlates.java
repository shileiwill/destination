package cc150.chapter3.stacks.queues;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class StackOfPlates {

	public static void main(String[] args) {

	}

	int capacity = 0;
	List<Stack<Integer>> list = new ArrayList<Stack<Integer>>();
	
	StackOfPlates(int capacity) {
		this.capacity = capacity;
	}
	
	void push(int item) {
		if (list.size() == 0 || list.get(list.size() - 1).size() == capacity) {
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(item);
			list.add(stack);
		} else {
			Stack<Integer> stack = list.get(list.size() - 1);
			stack.push(item);
		}
	}
	
	int pop() {
		if (list.size() == 0 || list.get(0).isEmpty()) {
			throw new EmptyStackException();
		}
		
		Stack<Integer> stack = list.get(list.size() - 1);
		int res = stack.pop();
		
		if (stack.isEmpty()) {
			list.remove(list.size() - 1);
		}
		
		return res;
	}
	
	int popAt(int index) {
		if (list.size() == 0 || index >= list.size() || list.get(index).isEmpty()) {
			throw new EmptyStackException();
		}
		
		Stack<Integer> stack = list.get(index);
		int res = stack.pop();
		
		if (stack.isEmpty()) {
			list.remove(index);
		}
		
		return res;
	}
}
