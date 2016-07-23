package chapter3.binaryTree;
/**
 * 236. Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * @author Lei
 *
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Return if we found p or q, or hit null
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // Divide : Find p or q in subtree. If found return itself. If not, return null
        TreeNode left = lowestCommonAncestor(root.left, p, q); // It will continue going to left until it finds either p, q or null.
        // So it will come to the bottom
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        // Conquer
        if (left != null && right != null) { // Both are found, which means root is a common parent
            return root;
        }
        if (left != null) { // Found only left
            return left;
        }
        if (right != null) {
            return right;
        }
        
        // Found nothing
        return null;
    }
}
