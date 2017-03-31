package company.tripadvisor.trialpay;

public class StackByArray {

	public static void main(String[] args) {
		StackByArray stack = new StackByArray(10);
		
		stack.push(5);
		System.out.println("5 : " + stack.pop());
		stack.push(2);
		stack.push(7);
		System.out.println("7 : " + stack.pop());
		System.out.println("2 : " + stack.pop());
		stack.push(12);
		stack.push(9);
		System.out.println("9 : " + stack.pop());
	}

	int[] arr = null;
	int pos = -1;
	
	StackByArray(int size) {
		arr = new int[size];
		pos = 0;
	}
	
	synchronized void push(int val) {
		if (isFull()) {
			throw new RuntimeException("Full Stack");
		}
		arr[pos++] = val;
	}
	
	boolean isFull() {
		return pos == arr.length;
	}
	
	synchronized int pop() {
		if (isEmpty()) {
			throw new RuntimeException("Empty Stack");
		}
		return arr[--pos];
	}
	
	boolean isEmpty() {
		return pos == -1;
	}
}
