package chapter3.binaryTree;
/**
 * 449. Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeAndDeBST {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        helper1(root, sb);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    void helper1(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.val + ",");
        helper1(node.left, sb);
        helper1(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] arr = data.split(",");
        TreeNode root = helper2(arr, 0, arr.length - 1);
        return root;
    }
    
    TreeNode helper2(String[] arr, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return new TreeNode(Integer.parseInt(arr[left]));
        }
        
        TreeNode root = new TreeNode(Integer.parseInt(arr[left]));
        int pos = findBigger(arr, left, right);
        root.left = helper2(arr, left + 1, pos);
        root.right = helper2(arr, pos + 1, right);
        
        return root;
    }
    
    int findBigger(String[] arr, int left, int right) {
        
        int target = Integer.parseInt(arr[left]);
        int i = left + 1;
        for (; i <= right; i++) {
            if (Integer.parseInt(arr[i]) > target) {
                return i - 1;
            }
        }
        // If there is no bigger
        return right;
    }
    
	public static void main(String[] args) {
		TreeNode n5 = new TreeNode(5);
		TreeNode n3 = new TreeNode(3);
		TreeNode n8 = new TreeNode(8);
		TreeNode n1 = new TreeNode(1);
		TreeNode n6 = new TreeNode(6);
		TreeNode n4 = new TreeNode(4);
		
		n5.left = n3;
		n5.right = n8;
		n3.left = n1;
		n8.left = n6;
		n3.right = n4;
		
		SerializeAndDeBST lot = new SerializeAndDeBST();
		String str = lot.serialize(n5);
		System.out.println(str);
		
		TreeNode root = lot.deserialize(str);
		System.out.println(root.val);
	}

}
