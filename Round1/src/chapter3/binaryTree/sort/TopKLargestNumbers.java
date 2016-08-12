package chapter3.binaryTree.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an integer array, find the top k largest numbers in it.
Example
Given [3,10,1000,-99,4,100] and k = 3. Return [1000, 100, 10].
 * @author Lulu
 *
 */
public class TopKLargestNumbers {

	public static void main(String[] args) {
		Integer[] A = {3,10,1000,-99,4,100};
		int k = 3;
		
		int[] res = findTopKLargestNumbers(A, k);
	}
	
	// Sort
	static int[] findTopKLargestNumbers(Integer[] A, int k) {
		Arrays.sort(A, Collections.reverseOrder()); // If want to use Collections.reverseOrder(), must be Integer type
		int[] res = new int[k];
		
		for (int i = 0; i < k; i++) {
			res[i] = A[i];
			System.out.println(i + " --- " + res[i]);
		}
		
		return res;
	}
	
	static int[] findTopKLargestNumbers2(int[] A, int k) {
		int[] res = new int[k];
		
		// Either way
		Comparator<Integer> maxComparator = new Comparator<Integer>(){
			public int compare(Integer a, Integer b) {
				return b - a;
			}
		};
		
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(A.length, Collections.reverseOrder());
		for (int a : A) {
			heap.offer(a);
		}
		
		for (int i = 0; i < k; i++) {
			res[i] = heap.poll();
			System.out.println(i + " --- " + res[i]);
		}
		
		return res;
	}

}
