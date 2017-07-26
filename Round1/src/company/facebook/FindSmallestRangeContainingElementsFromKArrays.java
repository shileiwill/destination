package company.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given k sorted lists of integers of size n each, find the smallest range that includes at least element from each of the k lists. 
 * If more than one smallest ranges are found, print any one of them.
 * Input:
K = 3
arr1[] : [4, 7, 9, 12, 15]
arr2[] : [0, 8, 10, 14, 20]
arr3[] : [6, 12, 16, 30, 50]
Output:
The smallest range is [6 8] 
Explanation: Smallest range is formed by 
number 7 from first list, 8 from second
list and 6 from third list.


Input: 
k = 3
arr1[] : [4, 7, 30]
arr2[] : [1, 2]
arr3[] : [20, 40]
The smallest range is [2 20]  

这是一个好题
 */
public class FindSmallestRangeContainingElementsFromKArrays {

	public static void main(String[] args) {
//		Integer[] arr1 = {4, 7, 9, 12, 15};
//		Integer[] arr2 = {0, 8, 10, 14, 20};
//		Integer[] arr3 = {6, 12, 16, 30, 50};
		Integer[] arr1 = {4, 7, 30};
		Integer[] arr2 = {1, 2};
		Integer[] arr3 = {20, 40};
		
		List<List<Integer>> source = new ArrayList<List<Integer>>();
		source.add(Arrays.asList(arr1));
		source.add(Arrays.asList(arr2));
		source.add(Arrays.asList(arr3));
		
		FindSmallestRangeContainingElementsFromKArrays find = new FindSmallestRangeContainingElementsFromKArrays();
		find.findRange(source);
	}

	void findRange(List<List<Integer>> source) {
		int k = source.size();
		int start = -1;
		int end = -1;
		int range = Integer.MAX_VALUE;
		
		PriorityQueue<Node> heap = new PriorityQueue<Node>(k, new Comparator<Node>(){
			public int compare(Node n1, Node n2) {
				return n1.val - n2.val;
			}
		}); // Heap 正好横跨了所有K个list
		
		for (List<Integer> list : source) {
			Iterator<Integer> it = list.iterator();
			Node node = new Node(null, it);
			
			if (node.it.hasNext()) {
				node.val = it.next();
			}
			
			if (node.val != null) {
				start = Math.min(start, node.val);
				end = Math.max(end, node.val);
				heap.offer(node);
			}
		}
		
		int min = start;
		int max = end;
		range = end - start + 1;
		
		while (!heap.isEmpty()) {
			Node now = heap.poll();
			
			min = now.val; // min一直在变
			
			if (max - min + 1 < range) { // 每次有变化，都要比较
				range = max - min + 1;
				start = min;
				end = max;
			}
			
			if (now.it.hasNext()) {
				now.val = now.it.next();
				if (now.val > max) { // 也要记得跟踪max, 在加入的时候
					max = now.val;
				}
				heap.offer(now);
			} else { // 只要有一个list穷尽了，就结束了
				System.out.println(start + "===" + end);
				break;
			}
		}
	}
	
	class Node {
		Integer val;
		Iterator<Integer> it;
		
		Node(Integer val, Iterator<Integer> it) {
			this.val = val;
			this.it = it;
		}
	}
}
