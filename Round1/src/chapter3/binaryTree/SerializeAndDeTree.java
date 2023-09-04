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

    // Solution 1:
    // Added on 08/25/2023
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    void helper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,");
            return;
        }
        sb.append(node.val + ",");
        helper(node.left, sb);
        helper(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        int[] pos = {0};
        TreeNode root = dehelper(arr, pos);
        return root;
    }

    TreeNode dehelper(String[] arr, int[] pos) {
        if (arr[pos[0]].equals("#")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(arr[pos[0]]));
        pos[0] = pos[0] + 1;
        node.left = dehelper(arr, pos);
        pos[0] = pos[0] + 1;
        node.right = dehelper(arr, pos);

        return node;
    }

    // Solution 2: I dont like wrapping the single int to an array.
    // Instead of passing in the cursor, let's change the array as we go
    public TreeNode rdeserialize(List<String> list) {
        // Recursive deserialization.
        if (list.get(0).equals("null")) {
          list.remove(0);
          return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
        list.remove(0);
        root.left = rdeserialize(list);
        root.right = rdeserialize(list);

        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }


    // Solution 3: Use level order traversal, breath first search
    public String serializeBFS(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode cur = queue.poll();

                if (cur == null) {
                    sb.append("#,");
                } else {
                    sb.append(cur.val + ",");
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
            }
        }

        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    // Use queue again, can't use recursion
    public TreeNode deserializeBFS(String data) {
        String[] arr = data.split(",");
        List<String> list = new LinkedList<String>(Arrays.asList(arr));

        if (list.get(0).equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for (int i = 1; i < list.size(); i += 2) {
            TreeNode cur = queue.poll();

            if (!list.get(i).equals("#")) {
                TreeNode leftNode = new TreeNode(Integer.valueOf(list.get(i)));
                queue.offer(leftNode);
                cur.left = leftNode;
            }
            if (i + 1 < list.size() && !list.get(i + 1).equals("#")) {
                TreeNode rightNode = new TreeNode(Integer.valueOf(list.get(i + 1)));
                queue.offer(rightNode);
                cur.right = rightNode;
            }
        }

        return root;
    }

    /////////////////END

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