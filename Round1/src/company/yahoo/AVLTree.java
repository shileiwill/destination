package company.yahoo;
// https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/AVLTree.java
public class AVLTree {

	public static void main(String[] args) {
		AVLTree avlTree = new AVLTree();
        Node root = null;
        root = avlTree.insert(root, -10);
        System.out.println(root.val);
        root = avlTree.insert(root, 2);
        System.out.println(root.val);
        root = avlTree.insert(root, 13);
        System.out.println(root.val);
        root = avlTree.insert(root, -13);
        System.out.println(root.val);
        root = avlTree.insert(root, -15);
        System.out.println(root.val);
        root = avlTree.insert(root, 15);
        System.out.println(root.val);
        root = avlTree.insert(root, 17);
        System.out.println(root.val);
        root = avlTree.insert(root, 20);
        
        System.out.println(root.val);
        
	}

	Node leftRotate(Node root) {
		Node newRoot = root.right;
		root.right = newRoot.left;
		newRoot.left = root;
		
		root.height = height(root);
		newRoot.height = height(newRoot);
		
		return newRoot; 
	}
	
	Node rightRotate(Node root) {
		Node newRoot = root.left;
		root.left = newRoot.right;
		newRoot.right = root;
		
		root.height = height(root);
		newRoot.height = height(newRoot);
		
		return newRoot;
	}
	
	int height(Node root) {
		if (root == null) {
			return 0;
		}
		
		if (root.left == null && root.right == null) {
			return 1;
		}
		
		return 1 + Math.max(root.left == null ? 0 : root.left.height, root.right == null ? 0 : root.right.height);
	}
	
	int balance(Node node1, Node node2) {
		return height(node1) - height(node2);
	}
	
	Node insert(Node root, int val) {
		if (root == null) {
			return new Node(val);
		} else if (val < root.val) {
			root.left = insert(root.left, val);
		} else {
			root.right = insert(root.right, val);
		}
		
		int balance = balance(root.left, root.right);
		
		if (balance > 1) { // left is higher than right, rightRotate
			if (height(root.left.left) >= height(root.left.right)) { // left, left
				root = rightRotate(root);
			} else { // left, right
				root.left = leftRotate(root.left);
				root = rightRotate(root);
			}
		} else if (balance < -1) { // right is higher
			if (height(root.right.right) >= height(root.right.left)) { // Right, right
				root = leftRotate(root);
			} else { // Right right
				root.right = rightRotate(root.right);
				root = leftRotate(root);
			}
		} else {
			root.height = height(root);
		}
		
		return root;
	}
	
}

class Node {
	Node left;
	Node right;
	int val;
	int height;
	
	Node(int val) {
		this.val = val;
	}
}
