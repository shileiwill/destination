package company.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class UnionIntersectionOfLists {

	public static void main(String[] args) {
		UnionIntersectionOfLists unionIntersect = new UnionIntersectionOfLists();
		
		Integer[] arr1 = {1, 1, 1, 2};
		Integer[] arr2 = {1, 1, 3};
		List<Integer> list1 = Arrays.asList(arr1);
		List<Integer> list2 = Arrays.asList(arr2);
		
		List<Integer> res = unionIntersect.intersection(list1, list2);
		for (int val : res) {
			System.out.print(val + "==");
		}
	}

	// If the List is LinkedList, get() will be O(N)
	// Here we are assuming there is no duplicate in each list
	List<Integer> union(List<Integer> list1, List<Integer> list2) { // 并集
		List<Integer> res = new ArrayList<Integer>();
		Collections.sort(list1);
		Collections.sort(list2);
		
		int pos1 = 0;
		int pos2 = 0;
		
		while (pos1 < list1.size() && pos2 < list2.size()) {
			if (list1.get(pos1) < list2.get(pos2)) {
				res.add(list1.get(pos1));
				pos1++;
			} else if (list1.get(pos1) > list2.get(pos2)) {
				res.add(list2.get(pos2));
				pos2++;
			} else { // equal
				res.add(list2.get(pos2));
				pos1++;
				pos2++;
			}
		}
		
		while (pos1 < list1.size()) {
			res.add(list1.get(pos1));
			pos1++;
		}
		
		while (pos2 < list2.size()) {
			res.add(list2.get(pos2));
			pos2++;
		}
		
		return res;
	}
	
	// Iterator will be O(1) guaranteed
	List<Integer> unionIterator(List<Integer> list1, List<Integer> list2) {
		List<Integer> res = new ArrayList<Integer>();
		Collections.sort(list1);
		Collections.sort(list2);
		
		Iterator<Integer> it1 = list1.iterator();
		Iterator<Integer> it2 = list2.iterator();
		
		Wrapper w1 = new Wrapper(null, it1);
		Wrapper w2 = new Wrapper(null, it2);

		if (w1.iterator.hasNext()) {
			w1.val = w1.iterator.next();
		}
		
		if (w2.iterator.hasNext()) {
			w2.val = w2.iterator.next();
		}
		
		while (w1.val != null && w2.val != null) {
			if (w1.val < w2.val) {
				res.add(w1.val);
				getNext(w1);
			} else if (w1.val > w2.val) {
				res.add(w2.val);
				getNext(w2);
			} else {
				res.add(w1.val);
				getNext(w1);
				getNext(w2);
			}
		}
		
		while (w1.val != null) {
			res.add(w1.val);
			getNext(w1);
		}
		
		while (w2.val != null) {
			res.add(w2.val);
			getNext(w2);
		}
		
		return res;
	}
	
	// If the List is LinkedList, get() will be O(N)
	List<Integer> intersection(List<Integer> list1, List<Integer> list2) { // 交集
		List<Integer> res = new ArrayList<Integer>();
		Collections.sort(list1);
		Collections.sort(list2);
		
		int pos1 = 0;
		int pos2 = 0;
		
		while (pos1 < list1.size() && pos2 < list2.size()) {
			if (list1.get(pos1) == list2.get(pos2)) {
				res.add(list1.get(pos1));
				pos1++;
				pos2++;
			} else if (list1.get(pos1) < list2.get(pos2)) {
				pos1++;
			} else {
				pos2++;
			}
		}
		
		return res;
	}
	
	List<Integer> intersectionIterator(List<Integer> list1, List<Integer> list2) {
		List<Integer> res = new ArrayList<Integer>();
		Collections.sort(list1);
		Collections.sort(list2);
		
		Iterator<Integer> it1 = list1.iterator();
		Iterator<Integer> it2 = list2.iterator();
		
		Wrapper w1 = new Wrapper(null, it1);
		Wrapper w2 = new Wrapper(null, it2);
		
		if (w1.iterator.hasNext()) {
			w1.val = w1.iterator.next();
		}
		
		if (w2.iterator.hasNext()) {
			w2.val = w2.iterator.next();
		}
		
		while (w1.val != null && w2.val != null) {
			if (w1.val == w2.val) {
				res.add(w1.val);
				getNext(w1);
				getNext(w2);
			} else if (w1.val < w2.val) {
				getNext(w1);
			} else {
				getNext(w2);
			}
		}
		
		return res;
	}
	
	void getNext(Wrapper w) {
		if (w.iterator.hasNext()) {
			w.val = w.iterator.next();
		} else {
			w.val = null;
		}
	}
}

class Wrapper {
	Integer val;
	Iterator<Integer> iterator;
	
	Wrapper(Integer val, Iterator<Integer> it) {
		this.val = val;
		this.iterator = it;
	}
}
