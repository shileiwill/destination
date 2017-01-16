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
import java.util.Stack;

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
    
    /*
    The idea is to compare the predecessors and successors of the closest node to the target, we can use two stacks to track the predecessors and successors, then like what we do in merge sort, we compare and pick the closest one to the target and put it to the result list.

    As we know, inorder traversal gives us sorted predecessors, whereas reverse-inorder traversal gives us sorted successors.
    */
    public List<Integer> closestKValuesStack(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<Integer> predecessor = new Stack<Integer>();
        Stack<Integer> successor = new Stack<Integer>();
        
        inorder(root, target, k, true, successor);
        inorder(root, target, k, false, predecessor);
        
        while (k > 0) {
            if (predecessor.isEmpty()) {
                res.add(successor.pop());
            } else if (successor.isEmpty()) {
                res.add(predecessor.pop());
            } else if (Math.abs(predecessor.peek() - target) < Math.abs(successor.peek() - target)) {
                res.add(predecessor.pop());
            } else {
                res.add(successor.pop());
            }
            
            k--;
        }
        
        return res;
    }
    
    void inorder(TreeNode node, double target, int k, boolean reverse, Stack<Integer> stack) {
        if (node == null) {
            return;
        }
        
        inorder(reverse ? node.right : node.left, target, k, reverse, stack);
        
        // Stop, no need to go through the whole tree
        // One with =, while the other doeen't. This is to avoid double the target value(if it exists in tree)
        if ((reverse && node.val <= target) || (!reverse && node.val > target)) {
            return;
        }
        stack.push(node.val);
        
        inorder(reverse ? node.left : node.right, target, k, reverse, stack);
    }
}
