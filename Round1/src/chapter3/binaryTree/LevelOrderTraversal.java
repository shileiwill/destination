package chapter3.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * 102. Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
 */
public class LevelOrderTraversal {

	public static void main(String[] args) {
// [3,9,20,null,null,15,7]
		TreeNode n3 = new TreeNode(3);
		TreeNode n9 = new TreeNode(9);
		TreeNode n20 = new TreeNode(20);
		TreeNode n15 = new TreeNode(15);
		TreeNode n7 = new TreeNode(7);
		
		n3.left = n9;
		n3.right = n20;
		n20.left = n15;
		n20.right = n7;
		
		LevelOrderTraversal lot = new LevelOrderTraversal();
		lot.levelOrder(n3);
	}

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (root == null) {
            return res;
        }
        
        // Here we are using currentLineCount and nextLineCount to track levels. We can also have 2 queues, one for curLevel, the other for nextLevel.
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int curLineCount = 1;
        int nextLineCount = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curLineCount--;
            list.add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
                nextLineCount++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLineCount++;
            }
            // If the end of line, add to res, and create a new list
            if (curLineCount == 0) {
                res.add(list);
                list = new ArrayList<Integer>();
                curLineCount = nextLineCount;
                nextLineCount = 0;
            }
        }
        
        return res;
    }
    
    // A better way without lineCount
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

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
        	
        	res.add(list);
        	list = new ArrayList<Integer>();
        }
        
        return res;
    }
    
    // DFS
    public ArrayList<ArrayList<Integer>> levelOrderDFS(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        int maxLevelSoFar = 0;
        while (true) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            dfs(root, list, 0, maxLevelSoFar);
            if (list.size() == 0) {
                break;
            }
            res.add(list); // Add current level
            maxLevelSoFar++; // Go to next level
        }
        
        return res;
    }
    
    void dfs(TreeNode root, ArrayList<Integer> list, int curLevel, int maxLevelSoFar) {
        if (root == null || curLevel > maxLevelSoFar) {
            return;
        }
        if (curLevel == maxLevelSoFar) {
            list.add(root.val);
            return;
        }
        
        dfs(root.left, list, curLevel + 1, maxLevelSoFar); // Next Level
        dfs(root.right, list, curLevel + 1, maxLevelSoFar);
    }
}
