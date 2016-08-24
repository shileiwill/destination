package chapter3.binaryTree;
/**
 * 366. Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty. 

Example:
 Given binary tree 

          1
         / \
        2   3
       / \     
      4   5    



Returns [4, 5, 3], [2], [1]. 

Explanation:


1. Removing the leaves [4, 5, 3] would result in this tree: 
          1
         / 
        2          



2. Now removing the leaf [2] would result in this tree: 
          1          



3. Now removing the leaf [1] would result in the empty tree: 
          []         




Returns [4, 5, 3], [2], [1]. 

 */
import java.util.ArrayList;
import java.util.List;

public class FindLeavesOfBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        // Must be bottom up approach
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(root, res);
        return res;
    }
    
    int helper(TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return 0;
        }
        int height = 1 + Math.max(helper(root.left, res), helper(root.right, res));
        
        if (height > res.size()) {
            res.add(new ArrayList<Integer>());
        }
        
        res.get(height - 1).add(root.val);
        
        // This is actually remove leaves, not required
        root.left = null;
        root.right = null;
        return height;
    }
}
/**
The key to solve this problem is converting the problem to be finding the index of the element in the result list. 
Then this is a typical DFS problem on trees.
 
For this question we need to take bottom-up approach. The key is to find the height of each node. Here the definition of height is:
The height of a node is the number of edges from the node to the deepest leaf. I didnt use this definition

I used a helper function to return the height of current node. According to the definition, the height of leaf is 0. h(node) = 1 + max(h(node.left), h(node.right)).
 The height of a node is also the its index in the result list (res). For example, leaves, whose heights are 0, are stored in res[0]. Once we find the height of a node, we can put it directly into the result.
**/
