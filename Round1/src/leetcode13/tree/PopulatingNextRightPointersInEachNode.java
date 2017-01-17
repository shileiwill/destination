package leetcode13.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 116. Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
 */
public class PopulatingNextRightPointersInEachNode {
    public void connectRecursion(TreeLinkNode root) {
        if (root == null) return;
        helper(root.left, root.right);
    }
    
    void helper(TreeLinkNode left, TreeLinkNode right) {
        if (left == null) {
            return;
        }
        left.next = right;
        
        helper(left.left, left.right);
        helper(left.right, right.left);
        helper(right.left, right.right);
    }
    
    public void connect(TreeLinkNode root) {
        TreeLinkNode levelStart = root; 
        while (levelStart != null) { // Line by line
            TreeLinkNode cur = levelStart;
            while (cur != null) { // Each item in a line
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.next != null && cur.right != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            
            levelStart = levelStart.left;
        }
    }
    
    // Level order traversal
    public void connect2(TreeLinkNode root) {
        if (root == null) return;
        
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            TreeLinkNode prev = null;
            for (int i = 0; i < size; i++) {
                TreeLinkNode cur = queue.poll();
                
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                
                if (prev != null) {
                    prev.next = cur;
                }
                prev = cur;
            }
        }
    }
}
class TreeLinkNode {
     int val;
     TreeLinkNode left, right, next;
     TreeLinkNode(int x) { val = x; }
}