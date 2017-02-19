package amazon;
/*
 * A binary heap is a heap data structure created using a binary tree. 
 * It can be seen as a binary tree with two additional constraints:
i) The shape property: the tree is a complete binary tree; that is, all levels of the tree, 
except possibly the last one (deepest) are fully filled and if the last level of the tree is not complete, 
the nodes of that level are filled from left to right.
ii) The heap property: each node is greater than or equal to each of its children 
according to a comparison predicate defined for the data structure.
 */
public class CustomPriorityQueue {
	int[] arr; // This implementation is using array
	int size;
	int pos; // Position is next available spot
	
	CustomPriorityQueue (int size) {
		arr = new int[size];
		this.size = size;
		this.pos = pos;
	}
	
	boolean isEmpty() {
		return pos == 0;
	}
	
	boolean isFull() {
		return pos == size;
	}
	
	int poll() {
		if (isEmpty()) {
			throw new RuntimeException("Create a customized Exception, saying empty heap exception");
		}
		return arr[--pos]; // The smaller numbers, which have higher priority, are on the right side of array
	}
	
	void offer(int newVal) {
		if (isFull()) {
			throw new RuntimeException("Full");
		}
		
		int i;
		for (i = pos - 1; i >= 0; i--) {
			if (newVal > arr[i]) {
				arr[i + 1] = arr[i]; // Move one step to the right, make the ith position available
			} else {
				break; // Found
			}
		}
		
		arr[++i] = newVal;
		pos++; // pos is not changed before, here just increase
	}
}
