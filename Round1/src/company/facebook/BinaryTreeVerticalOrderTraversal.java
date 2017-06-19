package company.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import chapter3.binaryTree.TreeNode;
/**
 * 314. Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:

Given binary tree [3,9,20,null,null,15,7],
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,8,4,0,1,7],
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
return its vertical order traversal as:
[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
 */
public class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        if (root == null) {
            return res;
        }
        
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<Integer> colQueue = new LinkedList<Integer>();
        int max = 0, min = 0;
        
        nodeQueue.offer(root);
        colQueue.offer(0); // 以根为0列
        
        // Queue is level order traversal
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int col = colQueue.poll();
            
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>());
            }
            map.get(col).add(node.val);
            
            if (node.left != null) {
                nodeQueue.offer(node.left);
                colQueue.offer(col - 1);
                min = Math.min(min, col - 1);        
            }
            
            if (node.right != null) {
                nodeQueue.offer(node.right);
                colQueue.offer(col + 1);
                max = Math.max(max, col + 1);
            }
        }
        
        for (int col = min; col <= max; col++) {
            res.add(map.get(col));
        }
        
        return res;
    }
    
    // As TreeNode and Column matters (Row doesn't matter, since we use level order traversal), 
    // we can build a Java class with these 2 attributes. In this way, we don't need to use 2 queues
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        if (root == null) {
            return res;
        }
        
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        // This Queue contains both node and col
        Queue<TreeColumnNode> queue = new LinkedList<TreeColumnNode>();
        queue.offer(new TreeColumnNode(root, 0));
        
        // 记录一下两头
        int min = 0, max = 0;
        
        // Level order traversal
        while (!queue.isEmpty()) {
            TreeColumnNode cNode = queue.poll();
            TreeNode node = cNode.node;
            int col = cNode.col;
            
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>());
            }
            map.get(col).add(node.val);
            
            if (node.left != null) {
                TreeColumnNode leftCNode = new TreeColumnNode(node.left, col - 1);
                queue.offer(leftCNode);
                min = Math.min(min, col - 1);
            }
            
            if (node.right != null) {
                TreeColumnNode rightCNode = new TreeColumnNode(node.right, col + 1);
                queue.offer(rightCNode);
                max = Math.max(max, col + 1);
            }
        }
        
        for (int col = min; col <= max; col++) {
            res.add(map.get(col));
        }
        
        return res;
    }
    
    class TreeColumnNode {
        TreeNode node;
        int col;
        
        public TreeColumnNode(TreeNode node, int col) {
            this.col = col;
            this.node = node;
        }
    }
}