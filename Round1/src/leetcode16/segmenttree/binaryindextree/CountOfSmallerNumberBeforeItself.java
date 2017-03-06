package leetcode16.segmenttree.binaryindextree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/*
Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) . 
For each element Ai in the array, count the number of element before this element Ai is smaller than 
it and return count number array.
Example
For array [1,2,7,8,5], return [0,1,2,3,2]
Note
We suggest you finish problem Segment Tree Build, Segment Tree Query II and 
Count of Smaller Number before itself I first.
Tags Expand 
LintCode Copyright Binary Tree Segment Tree
*/

/*
	Thoughts:
	Just like Count of Smaller Number (in all given array A):
	Create segment tree on index (0 ~ 10000)
	Modify it to store count of equal or smaller numbers comparing to itself.
	However, do query on every A[i] before calling 'modify'!!!!This is the trick. BEFORE ITSELF
	Every time, before adding a new count information into the tree, do a query and return result. This way, it's always checking numbers before itself.
*/
public class CountOfSmallerNumberBeforeItself {

	// Brute Force
	public ArrayList<Integer> countOfSmallerNumberII(int[] A) {
		// write your code here
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < A.length; i++) {
			int count = count(A, i);
			res.add(count);
		}

		return res;
	}

	int count(int[] A, int pos) {
		int val = A[pos];
		int count = 0;
		for (int i = 0; i < pos; i++) {
			if (A[i] < val) {
				count++;
			}
		}

		return count;
	}
}

class Solution2 {
	/**
	 * @param A:
	 *            An integer array
	 * @return: Count the number of element before this element 'ai' is smaller
	 *          than it and return count number array
	 */

	Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // To deal with
																	// duplicates
	Node root = null;

	public ArrayList<Integer> countOfSmallerNumberII(int[] A) {

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int val : A) {
			min = Math.min(min, val);
			max = Math.max(max, val);

			if (!map.containsKey(val)) {
				map.put(val, 1);
			} else {
				map.put(val, map.get(val) + 1);
			}
		}

		root = build(min, max);

		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < A.length; i++) {
			int query = A[i];
			int count = 0;
			if (i == 0 || query - 1 < min) {
				count = 0;
			} else {
				count = query(root, query - 1); // Smaller, not include, so
												// minus 1
			}
			res.add(count);
			modify(root, query, 1);
		}
		return res;
	}

	void modify(Node root, int index, int count) {
		if (root.start == index && root.end == index) {
			root.count += count;
			return;
		}

		int mid = (root.start + root.end) / 2;

		if (index <= mid) {
			modify(root.left, index, count);
		} else {
			modify(root.right, index, count);
		}

		root.count = root.left.count + root.right.count;
	}

	// Ignore count when building tree
	Node build(int start, int end) {
		if (start > end) {
			return null;
		}

		Node node = new Node(start, end, 0);
		if (start == end) {
			return node;
		}

		int mid = (start + end) / 2;
		node.left = build(start, mid);
		node.right = build(mid + 1, end);

		return node;
	}

	int query(Node node, int query) {
		if (query >= node.end) {
			return node.count;
		}

		int mid = (node.start + node.end) / 2;
		int count = 0;

		if (query < mid) {
			count += query(node.left, query);
		} else if (query == mid) {
			count += node.left.count;
		} else {
			count += node.left.count;
			count += query(node.right, query);
		}

		return count;
	}
}