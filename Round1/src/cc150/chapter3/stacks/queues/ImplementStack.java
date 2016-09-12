package cc150.chapter3.stacks.queues;

import java.util.EmptyStackException;

// Implement a stack using linkedlist
public class ImplementStack<T> {

	public static void main(String[] args) {

	}

	class StackNode<T> {
		T data;
		StackNode<T> next;
		
		public StackNode(T data) {
			this.data = data;
		}
	}
	
	StackNode top = null;
	
	boolean isEmpty() {
		return top == null;
	}
	
	T peek() {
		if (top == null) {
			throw new EmptyStackException();
		}
		return (T)top.data;
	}
	
	T pop() {
		if (top == null) {
			throw new EmptyStackException();
		}
		T res = (T) top.data;
		top = top.next;
		return res;
	}
	
	void push(T data) {
		StackNode newNode = new StackNode(data);
		newNode.next = top;
		top = newNode;
	}
}
