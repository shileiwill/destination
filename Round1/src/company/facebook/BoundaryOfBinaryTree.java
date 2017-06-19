package company.facebook;

import java.util.LinkedList;
import java.util.List;

import chapter3.binaryTree.TreeNode;

/**
 * 545. Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. 
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.

Left boundary is defined as the path from root to the left-most node. 
Right boundary is defined as the path from root to the right-most node. 
If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. 
Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. 
If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1
Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].
Example 2
Input:
    ____1_____
   /          \
  2            3
 / \          / 
4   5        6   
   / \      / \
  7   8    9  10  
       
Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].

顺时针的print binary tree boundary, 就是从根开始，先打右边界，再打叶子，最后打左边界。
 */
public class BoundaryOfBinaryTree {

	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		
		n1.left = n2;
		n1.right = n7;
		n2.left = n3;
		n2.right = n5;
		n3.left = n4;
		n7.right = n6;

		BoundaryOfBinaryTree boundary = new BoundaryOfBinaryTree();
		List<Integer> res = boundary.boundaryOfBinaryTree(n1);
		
		for (int val : res) {
			System.out.print(val + "==");
		}
	}


    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<Integer>();
        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        if (root == null) {
            return res;
        }
        
        // Left Edge
        TreeNode node = root;
        while (node != null) {
            nodes.addLast(node);
            
            if (node.left != null) {
                node = node.left;
            } else if (root != node) {
                node = node.right;
            } else {
                node = null;
            }
        }
        
        // Bottom
        node = root;
        helper(node, nodes);
        
        // Right Edge
        node = root.right;
        int index = nodes.size();
        while (node != null) {
            nodes.add(index, node);
            
            if (node.right != null) {
                node = node.right;
            } else if (root != node) {
                node = node.left;
            } else {
                node = null;
            }
        }
        
        res.add(nodes.getFirst().val);
        for (int i = 1; i < nodes.size(); i++) {
            if (nodes.get(i) == nodes.get(i - 1)) {
                continue;
            }
            res.add(nodes.get(i).val);
        }
        
        return res;
    }

    void helper(TreeNode node, LinkedList<TreeNode> nodes) {
    	if (node.left == null && node.right == null) {
    		nodes.add(node);
    		return;
    	}
    	
    	if (node.left != null) {
    		helper(node.left, nodes);
    	}
    	
    	if (node.right != null) {
    		helper(node.right, nodes);
    	}
    }
}
