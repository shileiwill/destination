package company.linkedin;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import chapter3.binaryTree.TreeNode;
import company.uber.SerializeDeserializeTree.Node;

/**
 * Given a tree string expression in balanced parenthesis format:
[A[B[C][D]][E][F]]
Construct a tree and return the root of the tree.
                A. 
            /   |  \
          B    E   F
         / \
       C   D
       
       Refer to Uber -> SerializeDeserializeTree
 */
public class ConstructTreeFromString {

	/**
	 * You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. 
The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   
Note:
There will only be '(', ')', '-' and '0' ~ '9' in the input string.
An empty tree is represented by "" instead of "()".
	 */
	public TreeNode str2BinaryTree(String s) {
		int left = 0, right = 0;
		
		while (right < s.length() && Character.isDigit(s.charAt(right))) {
			right++;
		} // Right is (
		
		int num = Integer.valueOf(s.substring(left, right));
		TreeNode root = new TreeNode(num);
		
		if (right < s.length()) {
			int count = 1;
			left = right; // left is (
			
			while (right + 1 < s.length() && count != 0) {
				right++;
				if (s.charAt(right) == '(') {
					count++;
				}
				if (s.charAt(right) == ')') {
					count--;
				}
			} // right is ) eventually
			
			root.left = str2BinaryTree(s.substring(left + 1, right));
		}
		
		if (right < s.length()) {
			root.right = str2BinaryTree(s.substring(right + 1, s.length() - 1));
		}
		
		return root;
	}
	
	public NaryTreeNode build(String input) {
		if (input == null || input.length() <= 2) {
			return null;
		}
		
		int[] pos = {1};
		NaryTreeNode root = helper(input, pos);
		return root;
	}
	
	// Not correct, not working
	NaryTreeNode helper(String s, int[] pos) {
		String value = "";
		NaryTreeNode root = null;
		
		while (pos[0] < s.length() && Character.isDigit(s.charAt(pos[0]))) {
			value += s.charAt(pos[0]);
			pos[0] = pos[0] + 1;
		}
		
		if (value.equals("")) {
			return null;
		} else {
			root = new NaryTreeNode(Integer.valueOf(value));
		}
		
		while (pos[0] < s.length()) {
			if (s.charAt(pos[0]) == '[') { // a new sub start
				pos[0] = pos[0] + 1;
				NaryTreeNode node = helper(s, pos);
				
				root.children.add(node);
				pos[0] = pos[0] + 1;
			} else {
				break;
			}
		}
		
		return root;
	}
	
	Node deserialize(String s) {
		Node root = null;
		Node parent = null;
		Stack<Node> stack = new Stack<Node>();
		int pos = 0;
		
		while (pos < s.length()) {
			char c = s.charAt(pos);
			
			if (c == '(') {
				pos++; // Next must be a number
				
				Node node = new Node(s.charAt(pos) - '0');
				if (root == null) {
					root = node; // Will come here only once
				}
				if (parent != null) {
					parent.children.add(node);
				}
				
				parent = node;
				stack.push(node); // 记录一下
			} else if (c == ')') {
				stack.pop(); // 弹出来
				
				if (!stack.isEmpty()) {
					parent = stack.peek(); // Change parent node, 1 level above
				}
			}
			
			pos++;
		}
		
		return root;
	}
	
	public static void main(String[] args) {
		String s = "[1[2[3][4]][5][6]]";
		ConstructTreeFromString ct = new ConstructTreeFromString();
		NaryTreeNode root = ct.build(s);
		System.out.println(root.val);
	}
	
	static class NaryTreeNode {
		int val;
		List<NaryTreeNode> children = new ArrayList<NaryTreeNode>();
		
		NaryTreeNode(int val) {
			this.val = val;
		}
	}
}
