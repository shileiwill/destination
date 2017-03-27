package company.expedia;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ArrayReduction {

	public static void main(String[] args) {
		ArrayReduction ar = new ArrayReduction();
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
//		list.add(4);
		
		int res = ar.reduce(list);
		System.out.println(res);
	}

	int reduce(List<Integer> list) {
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		
		for (int val : list) {
			heap.offer(val);
		}
		
		int cost = 0;
		while (heap.size() > 1) {
			int val1 = heap.poll();
			int val2 = heap.poll();
			
			int sum = val1 + val2;
			heap.offer(sum);
			cost += sum;
		}
		
		return cost;
	}
}
