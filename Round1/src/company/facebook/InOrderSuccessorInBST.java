package company.facebook;

import java.util.Stack;

import chapter3.binaryTree.TreeNode;

/**
 * 285. Inorder Successor in BST
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
 */
public class InOrderSuccessorInBST {

	// 这个通用于任何binary tree，没有利用上BST的特点
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            
            TreeNode node = stack.pop();
            if (pre != null && pre == p) { // 直接 pre != p?
                return node;
            }
            
            pre = node;
            cur = node.right;
        }
        
        return null;
    }
    
    // Successor use next, Precessor use prev node. BST is good, remember it.
    TreeNode next = null;
    public TreeNode inorderSuccessorBST(TreeNode root, TreeNode p) {
    	while (root != null) {
    		if (root.val < p.val) {
    			root = root.right;
    		} else if (root.val > p.val) {
    			next = root;
    			root = root.left;
    		} else {
    			// Find successor
    			if (root.right != null) {
    				TreeNode node = root.right;
    				while (node.left != null) {
    					node = node.left;
    				}
    				return node;
    			}
    			
    			return next;
    		}
    	}
    	
    	return null;
    }
    
    public TreeNode inorderSuccessorBSTRecursion(TreeNode root, TreeNode p) {
    	if (root == null) {
    		return null;
    	}
    	
    	if (root.val < p.val) {
    		return inorderSuccessorBSTRecursion(root.right, p);
    	} else if (root.val > p.val) {
    		next = root;
    		return inorderSuccessorBSTRecursion(root.left, p);
    	} else {
			if (root.right != null) {
				TreeNode node = root.right;
				while (node.left != null) {
					node = node.left;
				}
				return node;
			}
			
    		return next;
    	}
    }
    
    public static void main(String[] args) {
		TreeNode node0 = new TreeNode(0);
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		TreeNode node10 = new TreeNode(10);
		
		node6.left = node3;
		node6.right = node8;
		node3.left = node1;
		node3.right = node5;
		node5.left = node4;
		
		InOrderSuccessorInBST in = new InOrderSuccessorInBST();
		TreeNode res = in.inorderSuccessorBSTRecursion(node6, node5);
		System.out.println(res.val);
		
	}
    
    // Remember this guy!!! without using extra space. This one takes advantage of Binary Search Tree
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            TreeNode node = p.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        
        TreeNode n = root;
        TreeNode pre = null;
        while (n != null) {
            if (p.val > n.val) {
                n = n.right;
            } else if (p.val < n.val) {
                pre = n;
                n = n.left;
            } else {
                break;
            }
        }
        
        return pre;
    }

}
