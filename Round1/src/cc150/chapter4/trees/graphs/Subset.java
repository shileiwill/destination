package cc150.chapter4.trees.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subset {

	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>();
		set.add(4);
		set.add(3);
		set.add(7);
		set.add(1);
		set.add(14);
		
		Subset s = new Subset();
		List<List<Integer>> res = s.subset(set);
		for (List<Integer> list : res) {
			for (int i : list) {
				System.out.print(i + " - ");
			}
			System.out.println();
		}
	}
	
	List<List<Integer>> subset(Set<Integer> set) {
		List<Integer> arr = new ArrayList<Integer>(set);
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		helper(0, list, res, arr);
		return res;
	}
	
	void helper(int pos, List<Integer> list, List<List<Integer>> res, List<Integer> arr) {
		res.add(new ArrayList<Integer>(list));
		if (pos == arr.size()) {
			return;
		}
		
		for (int i = pos; i < arr.size(); i++) {
			list.add(arr.get(i));
			helper(i + 1, list, res, arr);
			list.remove(list.size() - 1);
		}
	}
}
