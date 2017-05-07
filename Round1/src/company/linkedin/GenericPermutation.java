package company.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Generic Type is important in LinkedIn
public class GenericPermutation<T extends Comparable<T>> {

	public static void main(String[] args) {

	}

	List<List<T>> permutate(T[] arr) {
		List<List<T>> res = new ArrayList<List<T>>();
		List<T> list = new ArrayList<T>();
		
		Arrays.sort(arr, new Comparator<T>() { // Actually, no need to use this comparator
			public int compare(T t1, T t2) {
				return t1.compareTo(t2);
			}
		});
		
		Set<Integer> visited = new HashSet<Integer>();
		helper(res, list, visited, arr);
		return res;
	}
	
	void helper(List<List<T>> res, List<T> list, Set<Integer> visited, T[] arr) {
		if (arr.length == list.size()) {
			res.add(new ArrayList<T>(list));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (visited.contains(i) || (i != 0 && arr[i - 1] == arr[i] && !visited.contains(i - 1))) {
				continue;
			}
			
			visited.add(i);
			list.add(arr[i]);
			helper(res, list, visited, arr);
			list.remove(list.size() - 1);
			visited.remove(i);
		}
	}
}
