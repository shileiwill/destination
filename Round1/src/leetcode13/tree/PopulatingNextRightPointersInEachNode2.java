package leetcode13.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 117. Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
 */
public class PopulatingNextRightPointersInEachNode2 {
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
    
    public void connect(TreeLinkNode root) {
        TreeLinkNode prev = null; // cur and prev are 1 level above, to overview left and right nodes
        TreeLinkNode head = null; // head of next level
        TreeLinkNode cur = root;
        
        while (cur != null) {
            
            while (cur != null) {
                if (cur.left != null) {
                    if (prev != null) {
                        prev.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    prev = cur.left;
                }
                
                if (cur.right != null) {
                    if (prev != null) {
                        prev.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    prev = cur.right;
                }
                
                cur = cur.next;
            }
            
            cur = head;
            prev = null;
            head = null;
        }
    }
}
