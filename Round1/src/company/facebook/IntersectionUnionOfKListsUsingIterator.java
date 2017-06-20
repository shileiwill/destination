package company.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class IntersectionUnionOfKListsUsingIterator {
	public static void main(String[] args) {
		Integer[] arr1 = {3, 4, 8, 9, 12, 14, 18};
		Integer[] arr2 = {1, 4, 7, 8, 10, 14, 19};
		Integer[] arr3 = {1, 2, 4, 6, 12, 13, 17};
		Integer[] arr4 = {7, 14, 17, 18, 120, 124, 129};
		List<Integer> list1 = Arrays.asList(arr1);
		List<Integer> list2 = Arrays.asList(arr2);
		List<Integer> list3 = Arrays.asList(arr3);
		List<Integer> list4 = Arrays.asList(arr4);
		
		IntersectionUnionOfKListsUsingIterator inK = new IntersectionUnionOfKListsUsingIterator();
		
		List<List<Integer>> source = new ArrayList<List<Integer>>();
		source.add(list1);
		source.add(list2);
		source.add(list3);
		source.add(list4);
		
		inK.union(source);
		inK.intersection(source);
	}
	
	List<Integer> union(List<List<Integer>> source) {
		int k = source.size();
		
		PriorityQueue<Node> heap = new PriorityQueue<Node>(k, new Comparator<Node>(){
			public int compare(Node node1, Node node2) {
				return node1.val - node2.val;
			}
		});
		
		for (List<Integer> list : source) {
			Iterator<Integer> it = list.iterator();
			Node node = new Node(null, it);
			
			if (node.it.hasNext()) {
				node.val = it.next();
			}
			
			if (node.val != null) {
				heap.offer(node);
			}
		}
		
		List<Integer> res = new ArrayList<Integer>();
		
		if (heap.isEmpty()) {
			return res;
		}
		
		while (!heap.isEmpty()) {
			Node node = heap.poll();
			
			if (res.isEmpty() || res.get(res.size() - 1) != node.val) {
				res.add(node.val); // 去重， 添加不一样的
			}
			
			if (node.it.hasNext()) {
				node.val = node.it.next();
				heap.offer(node);
			} // If there is no next(), don't add back to heap
		}
		
		for (int val : res) {
			System.out.print(val + "--");
		}
		System.out.println();
		return res;
	}
	
	List<Integer> intersection(List<List<Integer>> source) {
		int k = source.size();
		
		PriorityQueue<Node> heap = new PriorityQueue<Node>(k, new Comparator<Node>(){
			public int compare(Node node1, Node node2) {
				return node1.val - node2.val;
			}
		});
		
		for (List<Integer> list : source) {
			Iterator<Integer> it = list.iterator();
			Node node = new Node(null, it);
			
			if (node.it.hasNext()) {
				node.val = it.next();
			}
			
			if (node.val != null) {
				heap.offer(node);
			}
		}
		
		List<Integer> res = new ArrayList<Integer>();
		
		if (heap.isEmpty()) {
			return res;
		}
		
		Integer prev = null;
		while (!heap.isEmpty()) {
			Node node = heap.poll();
			
			if (prev == null) {
				prev = node.val;
			} else {
				if (node.val == prev) {
					if (res.isEmpty() || res.get(res.size() - 1) != node.val) {
						res.add(prev); // 总是滞后一步，当发现duplicate时，添加prev
					}
				} else {
					prev = node.val;
				}
			}
			
			if (node.it.hasNext()) {
				node.val = node.it.next();
				heap.offer(node);
			} // If there is no next(), dont add back to heap
		}
		
		for (int val : res) {
			System.out.print(val + "--");
		}
		return res;
	}
	
	class Node {
		Integer val = null;
		Iterator<Integer> it = null;
		
		Node(Integer val, Iterator<Integer> it) {
			this.val = val;
			this.it = it;
		}
	}
}


