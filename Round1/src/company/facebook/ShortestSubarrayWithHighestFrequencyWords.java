package company.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ShortestSubarrayWithHighestFrequencyWords {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 2, 2, 1, 4, 1};
		ShortestSubarrayWithHighestFrequencyWords testMain = new ShortestSubarrayWithHighestFrequencyWords();
		int[] res = testMain.findSubarray(arr);
		System.out.println(res[0] + "===" + res[1]);
	}

	/**
	 * 给一个数组，返回带有最高频重复数字的最短sub array
	 * 比如 数组 [1, 2, 3, 2, 2, 1, 4, 1]
	1和 2出现的频率都是 3次， 但是需要你返回 [2,3,2,2] 这个 子区间
	 */
	int[] findSubarray(int[] arr) {
		Map<Integer, Node> map = new HashMap<Integer, Node>();
		int maxCount = 0;
		
		for (int i = 0; i < arr.length; i++) {
			Node node = null;
			
			if (map.containsKey(arr[i])) {
				node = map.get(arr[i]);
				node.count++;
				node.end = i;
			} else {
				node = new Node(1, i, i);
				map.put(arr[i], node);
			}
			
			maxCount = Math.max(maxCount, node.count);
		}
		
		int minLength = Integer.MAX_VALUE;
		int[] res = {-1, -1};
		for (Node node : map.values()) {
			if (node.count == maxCount) {
				if (node.end - node.start + 1 < minLength) {
					minLength = node.end - node.start + 1;
					res[0] = node.start;
					res[1] = node.end;
				}
			}
		}
		
		return res;
	}
	
	class Node {
		int count;
		int start;
		int end;
		
		public Node(int count, int start, int end) {
			super();
			this.count = count;
			this.start = start;
			this.end = end;
		}
	}
	
	class TreeNode {
		int val;
		List<TreeNode> children = new ArrayList<TreeNode>();
	}
	
	/*
	 * 2. 给一个n-nery tree 和它的一个copy，参数传进来的是一个原树上的某的node，让你返回copy tree对应的node：

	这个题，我开始以为是clone graph类似的那个题，他们说的不是很清楚，我开始以为是返回1-1映射的node；后来澄清才发现不是
	Tree中的value不是unique, 不能单纯比较value
	同时进行BFS, Level order traversal 或者别的也都行，只要同时进行就行
	
	我开始也是同样的思路去 traverse tree, 但是面试官提示有更简单的记录路径的方法，然后原路返回即可找到。最后的代码实现了这个建议
	 */
	TreeNode findNodeInCopy(TreeNode root1, TreeNode root2, TreeNode toFind) {
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		stack1.push(root1);
		
		Stack<TreeNode> stack2 = new Stack<TreeNode>();
		stack2.push(root1);
		
		while (!stack1.isEmpty()) {
			TreeNode node1 = stack1.pop();
			TreeNode node2 = stack2.pop();
			
			if (node1 == toFind) {
				return node2;
			}
			
			List<TreeNode> list1 = node1.children;
			for (int i = list1.size() - 1; i >= 0; i--) {
				stack1.push(list1.get(i));
			}
			List<TreeNode> list2 = node2.children;
			for (int i = list2.size() - 1; i >= 0; i--) {
				stack2.push(list2.get(i));
			}
		}
		
		return null;
	}
}
