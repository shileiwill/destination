package chapter2.sortedArrays;
import java.util.ArrayList;
import java.util.Comparator;
/*
Given k sorted integer arrays, merge them into one sorted array.
Have you met this question in a real interview? Yes
Example
Given 3 sorted arrays:
[
  [1, 3, 5, 7],
  [2, 4, 6],
  [0, 8, 9, 10, 11]
]
return [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11].
Challenge
Do it in O(N log k).
N is the total number of integers.
k is the number of arrays.
Tags Expand 
Heap Priority Queue
*/
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArrays {

	public static void main(String[] args) {

	}

	 // Solution 1: Node in Heap
	 public List<Integer> mergekSortedArrays(int[][] arrays) {
		 Comparator<Node> comp = new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return n1.val - n2.val;
			}
		 };

		 PriorityQueue<Node> heap = new PriorityQueue<Node>(arrays.length, comp); // Get min by default
		 List<Integer> res = new ArrayList<Integer>();
		 
		 // We want to use the same strategy with Merge K sorted list, but realize in array, we dont know its siblings
		 for (int i = 0; i < arrays.length; i++) {
			 heap.offer(new Node(arrays[i][0], i, 0));
		 }
		 
		 while (!heap.isEmpty()) {
			 Node node = heap.poll();
			 res.add(node.val);
			 
			 if (node.y + 1 < arrays[node.x].length) {
				 heap.offer(new Node(arrays[node.x][node.y + 1], node.x, node.y + 1));
			 }
		 }
		 
		 return res;
	 }
	 
	 class Node {
		 int val, x, y;
		 Node (int val ,int x, int y) {
			 this.val = val;
			 this.x = x;
			 this.y = y;
		 }
	 }
}
