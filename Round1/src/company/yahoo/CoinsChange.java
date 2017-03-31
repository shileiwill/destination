package company.yahoo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1.给你三个长度d，l，s。让你用l和s组成d， 返回可能不可能。先backtracking写了，时间复杂度有点儿高，然后又写了dp版本。
 */
public class CoinsChange {

	public static void main(String[] args) {
		// Use 3 and 2 to build 23
		CoinsChange cc = new CoinsChange();
//		Set<List<Integer>> res = cc.build(7, 5, 11);
//		
//		for (List<Integer> list : res) {
//			for (int val : list) {
//				System.out.print(val + "--");
//			}
//			System.out.println();
//		}
		
		boolean res = cc.can(7, 5, 11);
		System.out.println(res);
	}
	
	boolean can(int a, int b, int c) {
		boolean[] hash = new boolean[c + 1];
		hash[0] = true;
		
		for (int i = 1; i <= c; i++) {
			if (i - a >= 0) {
				hash[i] |= hash[i - a];
			}
			if (i - b >= 0) {
				hash[i] |= hash[i - b];
			}
		}
		
		return hash[c];
	}

	Set<List<Integer>> build(int a, int b, int c) {
		Set<List<Integer>> res = new HashSet<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		
		dfs(res, list, a, b, c);
		return res;
	}
	
	void dfs(Set<List<Integer>> res, List<Integer> list, int a, int b, int c) {
		if (c == 0) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		if (c < 0) {
			return;
		}
		
		list.add(a);
		dfs(res, list, a, b, c - a);
		list.remove(list.size() - 1);
		
		list.add(b);
		dfs(res, list, a, b, c - b);
		list.remove(list.size() - 1);
	}
}
