package chapter3.binaryTree.sort;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 实现一个数据结构，提供下面两个接口
1.add(number) 添加一个元素
2.topk() 返回前K大的数
 * @author Lulu
 *
 */
public class TopKLargestNumbers2 {

	public static void main(String[] args) {
		BetterTopK t2 = new BetterTopK(3);
		t2.add(3);
		t2.add(10);
		t2.topK();
		t2.add(1000);
		t2.add(-99);
		t2.topK();
		t2.add(4);
		t2.topK();
		t2.add(100);
		t2.topK();
	}

	private int k = 0;
	private Queue<Integer> heap = new PriorityQueue<Integer>(10, Collections.reverseOrder());
	
	TopKLargestNumbers2 (int k) {
		this.k = k;
	}
	
	void add (int num) {
		heap.offer(num);
	}
	
	int[] topK () {
		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = heap.poll();
			System.out.print(res[i] + " -- ");
		}
		for (int i = 0; i < k; i++) {
			heap.offer(res[i]);
		}
		
		System.out.println();
		
		return res;
	}
}

class BetterTopK {
	private int k = 0;
	private Queue<Integer> heap = null;
	
	BetterTopK (int k) {
		this.k = k;
		heap = new PriorityQueue<Integer>(k);
	}
	
	void add (int num) {
		if (heap.size() == k) {
			if (heap.peek() >= num) { // If the new element is the smallest, ignore
				return;
			}
			heap.poll(); //　Remove the smallest
			heap.offer(num);
		} else {
			heap.offer(num);
		}
	}
	
	int[] topK () {
		int j = heap.size(); // j <= k
		int[] res = new int[j];
		for (int i = j - 1; i >= 0; i--) {
			res[i] = heap.poll();
		}
		for (int i = 0; i < j; i++) {
			System.out.print(res[i] + " -- ");
			heap.offer(res[i]);
		}
		System.out.println();
		
		return res;
	}
}
