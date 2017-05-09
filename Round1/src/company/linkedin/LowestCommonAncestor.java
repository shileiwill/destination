package company.linkedin;

import java.util.HashSet;
import java.util.Set;

import chapter3.binaryTree.TreeNode;

public class LowestCommonAncestor {

	public static void main(String[] args) {

	}

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) {
            return root;
        }
        
        if (left != null) {
            return left;
        }
        
        if (right != null) {
            return right;
        }
        
        return null;
    }
    
    // What if the tree node has parent pointer
    public Node lowestCommonAncestorWithParent(Node root, Node p, Node q) {
    	int depthP = getDepth(p);
    	int depthQ = getDepth(q);
    	
    	// Make them in the same level from root
    	while (depthP > depthQ) {
    		p = p.parent;
    		depthP--;
    	}
    	// No need to check who is bigger, just use 2 while loops
    	while (depthQ > depthP) {
    		q = q.parent;
    		depthQ--;
    	}
    	
    	// p, q move upward together
    	while (p != null && q != null) {
    		if (p == q) {
    			return p;
    		}
    		
    		p = p.parent;
    		q = q.parent;
    	}
    	
    	return null;
    }
    
    int getDepth(Node node) {
    	int depth = 0;
    	
    	while (node != null) {
    		depth++;
    		node = node.parent;
    	}
    	
    	return depth;
    }
    
    static class Node {
    	Node left;
    	Node right;
    	Node parent;
    	int val;
    }
    
    public Node lowestCommonAncestorWithParentUsingHashMap(Node root, Node p, Node q) {
    	Set<Node> visited = new HashSet<Node>();
    	
    	while (p != null) {
    		visited.add(p);
    		p = p.parent;
    	}
    	
    	while (q != null) {
    		if (visited.contains(q)) {
    			return q;
    		}
    		
    		q = q.parent;
    	}
    	
    	return null;
    }
}
