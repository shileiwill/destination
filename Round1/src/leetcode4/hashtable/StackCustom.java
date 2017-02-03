package leetcode4.hashtable;

public class StackCustom<K> {
	K[] arr;
	int size;
	int top;
	
	StackCustom(int size) {
		arr = (K[])new Object[size]; // Generic array is not supported, so do it in this way
		this.size = size;
		top = 0; // Next available spot
	}
	
	boolean isFull() {
		return top == size;
	}
	
	boolean isEmpty() {
		return top == 0;
	}
	
	void push(K newVal) {
		if (isFull()) {
			throw new RuntimeException("Full");
		}
		arr[top++] = newVal;
	}
	
	K pop() {
		if (isEmpty()) {
			throw new RuntimeException("Empty");
		}
		return arr[--top];
	}
}
