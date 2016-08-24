package chapter3.binaryTree.BST;
/**
 * 272. Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target. 

Note:

•Given target value is a floating point.
•You may assume k is always valid, that is: k ≤ total nodes.
•You are guaranteed to have only one unique set of k values in the BST that are closest to the target.


Follow up:
 Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)? 

Hint:
 1.Consider implement these two helper functions: i.getPredecessor(N), which returns the next smaller node to N.
ii.getSuccessor(N), which returns the next larger node to N.

2.Try to assume that each node has a parent pointer, it makes the problem much easier.
3.Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
4.You would need two stacks to track the path in finding predecessor and successor node separately.

 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import chapter3.binaryTree.TreeNode;

public class ClosestBSTValue2 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Comparator<TreeNode> comp = new Comparator<TreeNode>(){
            public int compare(TreeNode node1, TreeNode node2) {
                double diff1 = Math.abs(target - node1.val);
                double diff2 = Math.abs(target - node2.val);
                
                if (diff2 - diff1 == 0) {
                    return 0;
                }
                return (diff2 - diff1) < 0 ? -1 : 1; //Throw out the big one first
            }
        };
        PriorityQueue<TreeNode> heap = new PriorityQueue<TreeNode>(k, comp);
        heap.offer(root);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            if (node.left != null) {
                queue.offer(node.left);
                if (heap.size() == k) {
                    TreeNode top = heap.peek();
                    if (Math.abs(target - top.val) > Math.abs(target - node.left.val)) {
                        heap.poll();
                        heap.offer(node.left);
                    }
                } else {
                    heap.offer(node.left);
                }
            }
            if (node.right != null) {
                queue.offer(node.right);
                if (heap.size() == k) {
                    TreeNode top = heap.peek();
                    if (Math.abs(target - top.val) > Math.abs(target - node.right.val)) {
                        heap.poll();
                        heap.offer(node.right);
                    }
                } else {
                    heap.offer(node.right);
                }
            }
        }
        
        List<Integer> res = new ArrayList<Integer>();
        while (!heap.isEmpty()) {
            res.add(heap.poll().val);
        }
        
        return res;
    }
}
