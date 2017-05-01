package company.facebook;

import java.util.LinkedList;
import java.util.Queue;

import chapter3.binaryTree.TreeNode;

/**
 * salbring tree 跟117. Populating Next Right Pointers in Each Node II有点像，不过没有next指针， 你要用原来的left，right指针

比如：
          1
     2        3
4           5   6

变成：
1
｜
2 ——3
｜
4——5——6
 */
public class SalbringTree {

	public static void main(String[] args) {
		SalbringTree st = new SalbringTree();
		
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(5);
		TreeNode n4 = new TreeNode(4);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);
		TreeNode n6 = new TreeNode(6);
		
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n3.left = n5;
		n3.right = n6;

		st.connect(n1);
		
		System.out.println(n1.val);
	}

    public void connect(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        TreeNode up = null;
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            TreeNode prev = null;
            TreeNode now = null;
            for (int i = 0; i < size; i++) {
                if (i == 0) {
                	prev = queue.poll();
                	now = prev;
                	
                	if (up != null) { // Set left child of up node
                		up.left = prev;
                	}
                	up = prev;
                } else {
                    now = queue.poll();
                	
                    prev.right = now; // Set right child of prev node
                    prev = now;
                }
                
                if (now.left != null) {
                    queue.offer(now.left);
                    now.left = null; // Disable
                }
                if (now.right != null) {
                    queue.offer(now.right);
                    now.right = null;
                }
            }
        }
    }
}