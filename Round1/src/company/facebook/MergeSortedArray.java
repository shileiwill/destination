package company.facebook;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 88. Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
The number of elements initialized in nums1 and nums2 are m and n respectively.
 */
public class MergeSortedArray {

	public static void main(String[] args) {

	}

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int pos1 = m - 1;
		int pos2 = n - 1;
		
		while (pos1 >= 0 && pos2 >= 0) {
			if (nums1[pos1] > nums2[pos2]) {
				nums1[pos1 + pos2 + 1] = nums1[pos1];
				pos1--;
			} else {
				nums1[pos1 + pos2 + 1] = nums2[pos2];
				pos2--;
			}
		}
		
		while (pos2 >= 0) {
			nums1[pos2] = nums2[pos2--];
		}
	}
	
	//合并k数据流的迭代器
	PriorityQueue<Node> heap = null;
	
	public MergeSortedArray(List<List<Integer>> source) {
		heap = new PriorityQueue<Node>(source.size(), new Comparator<Node>(){
			public int compare(Node n1, Node n2) {
				return n1.val - n2.val;
			}
		});
		
		for (List<Integer> list : source) {
			Iterator<Integer> it = list.iterator();
			Node node = new Node(it, null);
			
			if (it.hasNext()) {
				node.val = it.next();
			}
			
			if (node.val != null) {
				heap.offer(node);
			}
		}
	}
	
	boolean hasNext() {
		return !heap.isEmpty();
	}
	
	int next() {
		Node now = heap.poll();
		int res = now.val;
		
		if (now.it.hasNext()) {
			now.val = now.it.next();
			heap.offer(now);
		}
		
		return res;
	}
	
	class Node{
		Iterator<Integer> it;
		Integer val;
		
		Node(Iterator<Integer> it, Integer val) {
			this.it = it;
			this.val = val;
		}
	}
}
