package chapter3.binaryTree;
/**
 * 297. Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
import java.util.Stack;

public class SerializeAndDeTree {

	public static void main(String[] args) {
		TreeNode n3 = new TreeNode(3);
		TreeNode n9 = new TreeNode(9);
		TreeNode n20 = new TreeNode(20);
		TreeNode n15 = new TreeNode(15);
		TreeNode n7 = new TreeNode(7);
		
		n3.left = n9;
		n3.right = n20;
//		n20.left = n15;
//		n20.right = n7;
		
		SerializeAndDeTree lot = new SerializeAndDeTree();
		String str = lot.serialize(n3);
		System.out.println(str);
		
		TreeNode root = lot.deserialize(str);
		System.out.println(root.val);
	}

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
        StringBuilder res = new StringBuilder();
        
        if (root == null) {
            return res.toString();
        }
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
        	TreeNode node = stack.pop();

        	if (node == null) {
        		res.append("#,");
        	} else {
            	res.append(node.val + ",");
            	stack.push(node.right);
        		stack.push(node.left);
        	}
        }
        // This is pre-order traversal
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
    	data = data.substring(0, data.length() - 1);
        String[] arr = data.split(",");
        // Must use wrapper, if not, the value will fluid/fly
        int[] wrapper = {0};
        
        return helper(arr, wrapper);
//        return helper(arr, 0);
    }
    
    TreeNode helper(String[] arr, int[] wrapper) {
    	if (arr[wrapper[0]].equals("#")) {
    		return null;
    	}
        
    	TreeNode node = new TreeNode(Integer.parseInt(arr[wrapper[0]]));
    	wrapper[0] = wrapper[0] + 1; 
        node.left = helper(arr, wrapper);
        wrapper[0] = wrapper[0] + 1;
        node.right = helper(arr, wrapper);
        
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));