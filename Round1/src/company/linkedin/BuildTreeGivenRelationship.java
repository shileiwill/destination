package company.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import chapter3.binaryTree.TreeNode;

/**
 * Build a tree given a list of relationship (parent -> child, 以及一个boolean isLeft)。 用一个hashmap做的。 
 */
public class BuildTreeGivenRelationship {

	public static void main(String[] args) {
		List<Integer[]> relation = new ArrayList<Integer[]>();
		List<Boolean> list = new ArrayList<Boolean>();
		
		Integer[] pair1 = {2, 5};
		Integer[] pair2 = {2, 4};
		Integer[] pair3 = {4, 3};
		Integer[] pair4 = {3, 1};
		
		relation.add(pair1);
		relation.add(pair2);
		relation.add(pair3);
		relation.add(pair4);
		
		list.add(true);
		list.add(false);
		list.add(false);
		list.add(true);
		
		TreeNode root = new BuildTreeGivenRelationship().buildTree(relation, list);
		System.out.println(root.val);
	}

	// parent, child, isLeft
	TreeNode buildTree(List<Integer[]> relation, List<Boolean> list) {
		Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
		Set<TreeNode> hasParent = new HashSet<TreeNode>();
		
		for (int i = 0; i < relation.size(); i++) {
			int parent = relation.get(i)[0];
			int child = relation.get(i)[1];
			boolean isLeft = list.get(i);
			
			TreeNode parentNode = null;
			if (map.containsKey(parent)) {
				parentNode = map.get(parent);
			} else {
				parentNode = new TreeNode(parent);
				map.put(parent, parentNode);
			}
			
			TreeNode childNode = null;
			if (map.containsKey(child)) {
				childNode = map.get(child);
			} else {
				childNode = new TreeNode(child);
				map.put(child, childNode);
			}
			
			if (isLeft) {
				parentNode.left = childNode;
			} else {
				parentNode.right = childNode;
			}
			
			hasParent.add(childNode);
		}
		
		// map.size() == hasParent.size() + 1
		for (TreeNode node : map.values()) {
			if (!hasParent.contains(node)) {
				return node;
			}
		}
		
		return null;
	}
}
