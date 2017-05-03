package company.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 合并邮件列表（后来才知道也是个面经题）
Given 1 million email list:
list 1: a@a.com, b@b.com
list 2: b@b.com, c@c.com
list 3: e@e.com
list 4: a@a.com
...
Combine lists with identical emails, and output tuples:
(list 1, list 2, list 4) (a@a.com, b@b.com, c@c.com)
(list 3) (e@e.com)
 */
public class MergeEmails {

	public static void main(String[] args) {
		MergeEmails me = new MergeEmails();
		
		String s1 = "a@a.com";
		String s2 = "b@b.com";
		String s3 = "c@c.com";
		String s4 = "e@e.com";

		List<String> list1 = new ArrayList<String>();
		list1.add(s1);
		list1.add(s2);
		
		List<String> list2 = new ArrayList<String>();
		list2.add(s3);
		list2.add(s2);
		
		List<String> list3 = new ArrayList<String>();
		list3.add(s4);
		
		List<String> list4 = new ArrayList<String>();
		list4.add(s1);
		
		List<List<String>> list = new ArrayList<List<String>>();
		list.add(list1);
		list.add(list2);
		list.add(list3);
		list.add(list4);
		
		List<Set<Integer>> res = me.merge(list);
		for (Set<Integer> set : res) {
			for (int val : set) {
				System.out.print(val + " - ");
			}
			System.out.println();
		}
	}

	/**
	 * Use Map<String, List<Integer>> and UnionFind
	 * @param list {aaa, bbb}, {aaa}, {ccc}
	 * @return {1, 2, 4}, {0}
	 */
	List<Set<Integer>> merge(List<List<String>> list) {
		// email, indexes
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		
		for (int i = 0; i < list.size(); i++) {
			for (String s : list.get(i)) {
				if (!map.containsKey(s)) {
					map.put(s, new ArrayList<Integer>());
				}
				map.get(s).add(i);
			}
		}
		
		UnionFind uf = new UnionFind(list.size());
		
		for (List<Integer> group : map.values()) {
			for (int i = 1; i < group.size(); i++) {
				uf.union(group.get(i), group.get(i - 1));
			}
		}
		
		return uf.findGroup();
	}
}

class UnionFind {
	int[] parent = null;
	
	UnionFind(int size) {
		parent = new int[size];
		
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
	}
	
	void union(int i, int j) {
		int p1 = find(i);
		int p2 = find(j);
		
		if (p1 != p2) {
			parent[p1] = p2;
		}
	}
	
	int find(int i) {
		while (parent[i] != i) {
			parent[i] = parent[parent[i]];
			i = parent[i];
		}
		return i;
	}
	
	List<Set<Integer>> findGroup() {
		List<Set<Integer>> res = new ArrayList<Set<Integer>>();
		Set<Integer>[] groups = new Set[parent.length];
		
		for (int i = 0; i < parent.length; i++) {
			int pos = find(i);
			if (groups[pos] == null) {
				groups[pos] = new HashSet<Integer>();
			}
			
			groups[pos].add(i);
		}
		
		for (Set<Integer> set : groups) {
			if (set != null) {
				res.add(set);
			}
		}
		
		return res;
	}
}
