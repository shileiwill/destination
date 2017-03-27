package company.tripadvisor.trialpay;

import java.util.ArrayList;
import java.util.List;

public class IntersectionOf3Arrays {

	public static void main(String[] args) {
		IntersectionOf3Arrays inter = new IntersectionOf3Arrays();
		
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		
		List<Integer> list0 = new ArrayList<Integer>();
		list0.add(1);
		list0.add(2);
		list0.add(3);
		list0.add(4);
		list0.add(5);
		
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(0);
		list1.add(2);
		list1.add(3);
		list1.add(5);
		list1.add(6);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(0);
		list2.add(2);
		list2.add(5);
		list2.add(6);
		list2.add(9);
		
		list.add(list0);
		list.add(list1);
		list.add(list2);
		
		List<Integer> res = inter.intersection(list);
		for (int val : res) {
			System.out.println(val);
		}
	}

	// Arrays are sorted
	List<Integer> intersection(List<List<Integer>> list) {
		List<Integer> list0 = list.get(0);
		
		for (int i = 1; i < list.size(); i++) {
			List<Integer> list1 = list.get(i);
			
			List<Integer> next = new ArrayList<Integer>();
			
			int p1 = 0, p2 = 0;
			while (p1 < list0.size() && p2 < list1.size()) {
				int num1 = list0.get(p1);
				int num2 = list1.get(p2);
				if (num1 == num2) {
					next.add(num1);
					p1++;
					p2++;
				} else if (num1 < num2) {
					p1++;
				} else {
					p2++;
				}
			}
			
			list0 = next;
		}
		
		return list0;
	}
}
