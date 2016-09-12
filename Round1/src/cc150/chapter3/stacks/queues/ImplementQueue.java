package cc150.chapter3.stacks.queues;

import java.util.EmptyStackException;

public class ImplementQueue<T> {

	public static void main(String[] args) {

	}

	class QueueNode<T> {
		T data;
		QueueNode<T> next;
		
		public QueueNode(T data) {
			this.data = data;
		}
	}
	
	QueueNode<T> first = null;
	QueueNode<T> last = null;
	
	boolean isEmpty() {
		return first == null;
	}
	
	T peek() {
		if (first == null) {
			throw new EmptyStackException();
		}
		return first.data;
	}
	
	void offer(T data) {
		QueueNode<T> node = new QueueNode<T>(data); 
		if (first == null) { // Nothing yet
			first = node;
			last = node;
		} else {
			last.next = node;
			last = node;
		}
	}
	
	T poll() {
		if (first == null) {
			throw new EmptyStackException();
		}
		T res = first.data;
		first = first.next;
		
		if (first == null) {
			last = null;
		}
		
		return res;
	}
}
