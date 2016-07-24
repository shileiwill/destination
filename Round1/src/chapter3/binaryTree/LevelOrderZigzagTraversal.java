package chapter3.binaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderZigzagTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean isEven = true;
        
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	
        	for (int i = 0; i < size; i++) {
        		TreeNode node = queue.poll();
        		list.add(node.val);
        		
        		if (node.left != null) {
        			queue.offer(node.left);
        		}
        		if (node.right != null) {
        			queue.offer(node.right);
        		}
        	}
        	
        	if (!isEven) {
        	    Collections.reverse(list);
        	}
        	
        	isEven = !isEven;
        	
        	res.add(list);
        	list = new ArrayList<Integer>();
        }
        
        return res;
    }
}
