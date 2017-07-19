package company.uber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class PrintTreeNodes {

	public static void main(String[] args) {
		Node A = new Node('a');
		Node B = new Node('b');
		Node C = new Node('c');
		Node D = new Node('d');
		Node E = new Node('e');
		Node F = new Node('f');
		Node G = new Node('g');
		Node H = new Node('h');
		Node I = new Node('i');
		Node J = new Node('j');
		Node Z = new Node('z');
		         
		A.left = B;
		A.right = C;
		B.left = D;
		C.left = E;
		C.right = F;
		D.left = Z;
		D.right = G;
		E.right = H;
		F.left = I;
		F.right = J;
		
		PrintTreeNodes p = new PrintTreeNodes();
//		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//		System.out.println(p.fib(5, map));
		
		p.printTree(A);
	}

	static class Node {
		Node left;
		Node right;
		char val;
		int col; // Track column
		
		Node(char val) {
			this.val = val;
		}
	}
	
	// 斐波那契的话，构造个列表，存储之前得到的结果，降低平均时间复杂度
	int fib(int N, Map<Integer, Integer> map) {
		if (N == 0 || N == 1) {
			return 1;
		}
		
		if (map.containsKey(N)) {
			System.out.println("Come with " + N);
			return map.get(N);
		}
		
		int N_1 = fib(N - 1, map);
		map.put(N - 1, N_1);
		
		int N_2 = fib(N - 2, map);
		map.put(N - 2, N_2);
		
		map.put(N, N_1 + N_2);
		return N_1 + N_2;
	}
	
	/**
	 *  这个题有点像vertical order traversal, 但是不是！
	 *  给一个binary tree, 要elegantly print出来，要求：
		假设node的宽度都是一样的（单字母）。不能有多个字母出现在同一列。左子树的node都要在左边列，右子树在右边
		
		Give a binary tree, elegantly print it so that no two tree nodes share the same column. 
		Requirement: left child should appear on the left column of root, and right child should appear on the right of root.
		
		Example: 
		    a
		   b   c
		 d   e   f
		z g   h i j
		
		这道题若能发现inorder traversal each node的顺序其实就是column number递增的顺序，那么就成功了一大半

		维护一个global variable，colNum, 做inorder traversal

		然后level order 一层一层打印出来
	 */
	
	int col = 0;
	void printTree(Node root) {
		inOrderTraversal(root);
		levelOrderTraversal(root);
	}
	
	private void inOrderTraversal(Node root) {
		if (root == null) {
			return;
		}
		
		inOrderTraversal(root.left);
		root.col = col;
		col++;
		inOrderTraversal(root.right);
	}
	
	private List<String> levelOrderTraversal(Node root) {
		List<String> res = new ArrayList<String>();
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			Map<Integer, Character> map = new HashMap<Integer, Character>(); // col, val
			int maxCol = 0;
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				Node now = queue.poll();
				
				map.put(now.col, now.val);
				maxCol = Math.max(maxCol, now.col); // No need to compare
				
				if (now.left != null) {
					queue.offer(now.left);
				}
				
				if (now.right != null) {
					queue.offer(now.right);
				}
			}
			
			for (int i = 0; i <= maxCol; i++) {
				if (map.containsKey(i)) {
					sb.append(map.get(i));
				} else {
					sb.append(" ");
				}
			}
			
			res.add(sb.toString());
			
			System.out.println(sb.toString());
		}
		
		return res;
	}
}
