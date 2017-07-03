package company.facebook;

import java.util.ArrayList;
import java.util.List;

import chapter3.binaryTree.TreeNode;
/**
 * 257
 */
public class PrintRootToLeaf {

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		
		n2.left = n5;
		n2.right = n4;
		n4.right = n3;
		n3.left = n1;
		
		PrintRootToLeaf pr = new PrintRootToLeaf();
		List<List<TreeNode>> res = pr.printPathMyStyle(n2);
		
		for (List<TreeNode> list : res) {
			for (TreeNode node : list) {
				System.out.print(node.val + "--");
			}
			System.out.println();
		}
	}

	List<List<TreeNode>> printPath(TreeNode root) {
		List<List<TreeNode>> res = new ArrayList<List<TreeNode>>();
		List<TreeNode> list = new ArrayList<TreeNode>();
		list.add(root);
		helper(res, list, root);
		return res;
	}

	private void helper(List<List<TreeNode>> res, List<TreeNode> list, TreeNode root) {
		if (root.left == null && root.right == null) {
			res.add(new ArrayList<TreeNode>(list));
			return;
		}
		
		if (root.left != null) {
			list.add(root.left);
			helper(res, list, root.left);
			list.remove(list.size() - 1);
		}
		
		if (root.right != null) {
			list.add(root.right);
			helper(res, list, root.right);
			list.remove(list.size() - 1);
		}
	}
	
	// MyStyle works
	List<List<TreeNode>> printPathMyStyle(TreeNode root) {
		List<List<TreeNode>> res = new ArrayList<List<TreeNode>>();
		List<TreeNode> list = new ArrayList<TreeNode>();
		helperMyStyle(res, list, root);
		return res;
	}

	private void helperMyStyle(List<List<TreeNode>> res, List<TreeNode> list, TreeNode root) {
		if (root == null) {
			return;
		}
		
		if (root.left == null && root.right == null) {
			list.add(root);
			res.add(new ArrayList<TreeNode>(list));
			list.remove(list.size() - 1);
			return;
		}
		
		list.add(root);
		helperMyStyle(res, list, root.left);
		helperMyStyle(res, list, root.right);
		list.remove(list.size() - 1);
	}
	
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        if (root == null) {
            return res;    
        }
        helperMyStyle(root, "", res);
        return res;
    }
    
    void helper(TreeNode node, String path, List<String> res) {
        if (node.left == null && node.right == null) { // When to end
            res.add(path + node.val);
            return;
        }
        if (node.left != null) {
            helper(node.left, path + node.val + "->", res);
        }
        if (node.right != null) {
            helper(node.right, path + node.val + "->", res);
        }
    }
    
    void helperMyStyle(TreeNode node, String path, List<String> res) {
    	if (node == null) {
    		return;
    	}
    	
        if (node.left == null && node.right == null) { // When to end
            res.add(path + node.val);
            return;
        }
        
        helperMyStyle(node.left, path + node.val + "->", res);
        helperMyStyle(node.right, path + node.val + "->", res);
    }
}
