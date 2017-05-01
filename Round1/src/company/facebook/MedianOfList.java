package company.facebook;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * list of sorted integer arrays，要求找所有的数的median. e.g. [1,3,6,7,9], [2,4, 8], [5], return 5
 */
public class MedianOfList {

	public static void main(String[] args) {
		int[] arr1 = {1,3,6,7,9};
		int[] arr2 = {2,4, 8};
		int[] arr3 = {5};
		
//		int[] arr1 = {1};
//		int[] arr2 = {2};
//		int[] arr3 = {5};
		
		List<int[]> list = new ArrayList<int[]>();
		list.add(arr1);
		list.add(arr2);
		list.add(arr3);
		
		MedianOfList ml = new MedianOfList();
		double res = ml.findMedianFromStream(list);
		System.out.println(res);
	}

	// This will take advantage the fact that arrays are sorted already
	double findMedian(List<int[]> list) {
		int size = 0;
		for (int[] arr : list) {
			size += arr.length;
		}
		
		if (size % 2 == 0) {
			// Find size / 2 - 1 and size / 2
			return (helper(list, (size / 2) - 1) + helper(list, size / 2)) / 2.0;
		} else {
			// Find size / 2
			return helper(list, size / 2);
		}
	}
	
	int helper(List<int[]> list, int pos) {
		Comparator<Wrapper> com = new Comparator<Wrapper>() {
			public int compare(Wrapper w1, Wrapper w2) {
				return w1.val - w2.val;
			}
		};
		
		PriorityQueue<Wrapper> heap = new PriorityQueue<Wrapper>(list.size(), com);
		
		for (int i = 0; i < list.size(); i++) {
			int[] arr = list.get(i);
			
			if (arr != null && arr.length != 0) {
				heap.offer(new Wrapper(arr[0], i, 0));
			}
			
			if (heap.size() - 1 == pos) {
				return arr[0];
			}
		}
		
		int index = list.size(); // Index is next available
		while (!heap.isEmpty()) {
			Wrapper now = heap.poll();
			
			// it has next
			if (now.indexInMachine != list.get(now.machineId).length - 1) {
				int val = list.get(now.machineId)[now.indexInMachine + 1];
				int machineId = now.machineId;
				int indexInMachine = now.indexInMachine + 1;
				heap.offer(new Wrapper(val, machineId, indexInMachine));
				
				if (index == pos) {
					// Get largest in heap. It is the result
					return getLargestInHeap(heap);
				}
				index++;
			}
		}
		
		return -1;
	}
	
	int getLargestInHeap(PriorityQueue<Wrapper> heap) {
		while (heap.size() > 1) {
			heap.poll();
		}
		
		return heap.poll().val;
	}
	
	// Find median from data stream, using 2 heaps
	double findMedianFromStream(List<int[]> list) {
		for (int[] arr : list) {
			for (int val : arr) {
				addNum(val);
			}
		}
		
		return getMedian();
	}
	
	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
		@Override
		public int compare(Integer val1, Integer val2) {
			return val2 - val1;
		}
	});
	
	void addNum(int val) {
		maxHeap.offer(val);
		minHeap.offer(maxHeap.poll());
		
		// maxHeap is always >= minHeap
		if (maxHeap.size() < minHeap.size()) {
			maxHeap.offer(minHeap.poll());
		}
	}
	
	double getMedian() {
		if (maxHeap.size() > minHeap.size()) {
			return maxHeap.peek();
		} else {
			return (maxHeap.peek() + minHeap.peek()) / 2.0;
		}
	}
}

class Wrapper {
	int val;
	int machineId;
	int indexInMachine;
	
	public Wrapper(int val, int machineId, int indexInMachine) {
		super();
		this.val = val;
		this.machineId = machineId;
		this.indexInMachine = indexInMachine;
	}
}