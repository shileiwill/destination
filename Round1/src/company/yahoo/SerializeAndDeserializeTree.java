package company.yahoo;

import java.util.Stack;

import chapter3.binaryTree.TreeNode;
// LC 297
public class SerializeAndDeserializeTree {

	public static void main(String[] args) {

	}

	String serialize(TreeNode node) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(node);
		StringBuilder sb = new StringBuilder();
		
		while (!stack.isEmpty()) {
			TreeNode now = stack.pop();
			
			if (now == null) {
				sb.append("#,");
			} else {
				sb.append(now.val + ",");
				stack.push(now.right);
				stack.push(now.left);
			}
		}
		
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
	
	TreeNode deserialize(String str) {
		String[] strArr = str.split(",");
		
		int[] wrapper = {0};
		return helper(strArr, wrapper);
	}
	
	TreeNode helper(String[] arr, int[] wrapper) {
		if (arr[wrapper[0]].equals("#")) {
			return null;
		}
		
		TreeNode root = new TreeNode(Integer.valueOf(arr[wrapper[0]]));
		
		wrapper[0] = wrapper[0] + 1;
		root.left = helper(arr, wrapper);
		
		wrapper[0] = wrapper[0] + 1;
		root.right = helper(arr, wrapper);
		
		return root;
	}
}
