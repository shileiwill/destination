package amazon;

public class CustomStack<K> {
	K[] arr;
	int size;
	int top;
	
	CustomStack(int size) {
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
	
	public static void main(String[] args) {
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(2);
		
		int i3 = 3;
		int i4 = 4;
		
		System.out.println(i1.equals(i2));
//		System.out.println(i3.equals(i4));
	}
}
