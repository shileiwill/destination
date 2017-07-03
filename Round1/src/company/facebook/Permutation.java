package company.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * 1. all permutations ， 给个 “ABC”--》
abc
acb
bac
bca
cab
cba

follow up 是 如果有重复的怎么去重， 我用的hashset， 他问有没有 不用额外空间的， 当时没想出来， 他提醒了可以sort， 就很快给了答案，没时间写这个。。

问了java pass by value／referecne，  recursion vs dfs ， 这个基本问题。 关键是边写边解释。
 */
public class Permutation {

	public static void main(String[] args) {
		Permutation perm = new Permutation();
		List<String> res = perm.findAllPermutations2("ABAC");
		for (String s : res) {
			System.out.println(s);
		}
		
//		int[] arr = {3, 1, 1};
//		perm.getPermutation(arr);
	}

	// If there is no duplicate
	List<String> findAllPermutations(String s) {
		List<String> res = new ArrayList<String>();
		helper(res, "", s.toCharArray());
		return res;
	}

	private void helper(List<String> res, String now, char[] arr) {
		if (now.length() == arr.length) {
			res.add(now);
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (now.indexOf(arr[i]) == -1) {
				helper(res, now + arr[i], arr);
			}
		}
	}
	
	List<String> findAllPermutations2(String s) {
		List<String> res = new ArrayList<String>();
		Set<Integer> visited = new HashSet<Integer>();
		char[] arr = s.toCharArray();
		Arrays.sort(arr);
		
		helper2(res, new StringBuilder(), arr, visited);
		return res;
	}

	// String, StringBuilder doesnt matter
	private void helper2(List<String> res, StringBuilder now, char[] arr, Set<Integer> visited) {
		if (now.length() == arr.length) {
			res.add(now.toString());
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (i != 0 && arr[i] == arr[i - 1] && !visited.contains(i - 1)) {
				continue;
			}
			
			if (visited.contains(i)) {
				continue;
			}
			
			visited.add(i);
			now.append(arr[i]);
			helper2(res, now, arr, visited);
			now.setLength(now.length() - 1);
			visited.remove(i);
		}
	}
	
	List<List<Integer>> getPermutation(int[] arr) {
		Set<List<Integer>> resSet = new HashSet<List<Integer>>();
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		
		Arrays.sort(arr); // If using Set, no need to sort
		helperWithDuplicates(res, list, arr, visited);
		
		for (List<Integer> aList : res) {
			for (int val : aList) {
				System.out.print(val + "--");
			}
			System.out.println();
		}
		
		return res;
	}
	
	void helper(List<List<Integer>> res, List<Integer> list, int[] arr) {
		if (list.size() == arr.length) {
			for (int val : list) {
				System.out.print(val + "--");
			}
			System.out.println();
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (list.contains(arr[i])) {
				continue;
			}
			list.add(arr[i]);
			helper(res, list, arr);
			list.remove(list.size() - 1);
		}
	}
	
	// What if there is duplicate?
	void helperWithDuplicates(Set<List<Integer>> res, List<Integer> list, int[] arr, Set<Integer> visited) {
		if (list.size() == arr.length) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (visited.contains(i)) {
				continue;
			}
			visited.add(i);
			list.add(arr[i]);
			helperWithDuplicates(res, list, arr, visited);
			list.remove(list.size() - 1);
			visited.remove(i);
		}
	}
	
	// What if Set is not allowed. You need to figure out the duplicates
	void helperWithDuplicates(List<List<Integer>> res, List<Integer> list, int[] arr, Set<Integer> visited) {
		if (list.size() == arr.length) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (visited.contains(i) || (i != 0 && arr[i] == arr[i - 1] && !visited.contains(i - 1))) { // Here is the trick, you have to use this!
				continue;
			}
			visited.add(i);
			list.add(arr[i]);
			helperWithDuplicates(res, list, arr, visited);
			list.remove(list.size() - 1);
			visited.remove(i);
		}
	}
}
