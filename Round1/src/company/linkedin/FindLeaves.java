package company.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chapter3.binaryTree.TreeNode;

/**
 * Coding  每一轮所有叶子脱落，直到root最后脱落，要求打印所有轮；
followup： 每个node还是有left，right指针，但是结构并非tree，而是graph怎么算， 考虑有/无circular dependency 两种情况；
方法是用hashmap 记录计算过的node level 和 一个Set 记录dependent node
 */
public class FindLeaves {

	public static void main(String[] args) {

	}

	List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		helper(res, root);
		return res;
	}
	
	int helper(List<List<Integer>> res, TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		int height = Math.max(helper(res, root.left),  helper(res, root.right)) + 1;
		
		if (height > res.size()) {
			res.add(new ArrayList<Integer>());
		}
		
		res.get(height - 1).add(root.val);
		
		return height;
	}
	
	// Level Order Traversal using DFS
	List<List<Integer>> levelOrderDFS(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		dfs(res, 1, root);
		return res;
	}
	
	void dfs(List<List<Integer>> res, int level, TreeNode root) {
		if (level > res.size()) {
			res.add(new ArrayList<Integer>());
		}
		
		res.get(level - 1).add(root.val);
		
		if (root.left != null) {
			dfs(res, level + 1, root.left);
		}
		
		if (root.right != null) {
			dfs(res, level + 1, root.right);
		}
	}
	
	// It is graph, rather than tree
	/**
	 * One big difference is, tree only has 1 path between 2 nodes, while graph can have several paths, which forms a circle
	 * @param graph could be isolated, but in our case, it is a connected graph
	 * @return
	 * 只用一个Set<Node> visited 不行，因为graph里边可以重复visit 但是不能有环。 所以用Map, 上次还没计算出结果来呢，又来了， 说明有环
	 */
	List<List<Integer>> findLeavesInGraph(TreeNode graph) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		// Use a map to remember not only the height, but also track loop
		Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
		helper(res, map, graph);
		return res;
	}
	
	int helper(List<List<Integer>> res, Map<TreeNode, Integer> map, TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		if (map.containsKey(node)) {
			if (map.get(node) == -1) {
				throw new RuntimeException("There is loop, done");
			}
			return map.get(node);
		}
		
		map.put(node, -1); // Seen this node, but not finalized yet, it is still too high
		int height = Math.max(helper(res, map, node.left), helper(res, map, node.right)) + 1;
		if (height > res.size()) {
			res.add(new ArrayList<Integer>());
		}
		
		res.get(height - 1).add(node.val);
		
		map.put(node, height);
		return height;
	}
}