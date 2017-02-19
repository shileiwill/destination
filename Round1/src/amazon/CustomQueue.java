package amazon;

public class CustomQueue {
	int[] arr;
	int size;
	int number;
	int rear = 0; // Elements to be added to rear
	int front = 0; // Elements will be removed from front
	
	CustomQueue (int size) {
		this.size = size;
		arr = new int[size];
		number = 0;
	}
	
	boolean isFull() {
		return number == size;
	}
	
	boolean isEmpty() {
		return number == 0;
	}
	
	void insert(int newVal) {
		if (isFull()) {
			throw new RuntimeException("Full");
		}
		
		if (rear == size) {
			rear = 0; // Wrap around
		}
		
		arr[rear++] = newVal;
		number++;
	}
	
	int remove() {
		if (isEmpty()) {
			throw new RuntimeException("Empty");
		}
		
		int deletedVal = arr[front++];
		if (front == size) {
			front = 0; // Wrap around
		}
		
		number--;
		return deletedVal;
	}
}
