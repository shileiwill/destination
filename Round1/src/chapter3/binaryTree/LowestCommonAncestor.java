package chapter3.binaryTree;
/**
 * 236. Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: �The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).�

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
    // Solution 1: check if a given node exists in a tree/subtree. Pick a side to go
    // If P and Q are both on the left of the node, branch left to look for the common ancestor. When P and Q are no longer on the same side, found
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (!covers(root, p) || !covers(root, q)) { // Error check, if P or Q is not in tree
            return null;
        }

        if (covers(p, q)) {
            return p;
        }
        if (covers(q, p)) {
            return q;
        }
        
        boolean isPOnLeft = covers(root.left, p);
        boolean isQOnLeft = covers(root.left, q);
        
        if (isPOnLeft != isQOnLeft) {
            return root;
        }
        
        TreeNode nextSide = isPOnLeft ? root.left : root.right;
        
        return lowestCommonAncestor2(nextSide, p, q);
    }
    
    boolean covers(TreeNode root, TreeNode node) {
        if (root == null) {
            return false;
        }
        if (root == node) {
            return true;
        }
        
        return covers(root.left, node) || covers(root.right, node);
    }

    // Solution 2: Rather than returning true/false, we return the Node if we found p or q or null
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

    // Solution 3: Get the path from root to the target node, compare the 2 paths
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pList = new ArrayList<TreeNode>();
        // pList.add(root);
        helper(root, p, pList);

        List<TreeNode> qList = new ArrayList<TreeNode>();
        // qList.add(root);
        helper(root, q, qList);

        int pos = Math.min(pList.size() - 1, qList.size() - 1);
        while (pos >= 0) {
            TreeNode n1 = pList.get(pos);
            TreeNode n2 = qList.get(pos);

            if (n1 == n2) {
                return n1;
            }
            pos--;
        }

        return root;
    }

    boolean helper (TreeNode node, TreeNode target, List<TreeNode> list) {
        // Base conditions
        if (node == target) {
            list.add(node);
            return true;
        }
        if (node == null) {
            return false;
        }

        // Try current node
        list.add(node);

        // Try left path
        boolean left = helper(node.left, target, list);
        if (left) {
            return true;
        }

        // Try right path
        boolean right = helper(node.right, target, list);
        if (right) {
            return true;
        }

        // If it comes here, it means we tried both left and right, and we couldn't find the target
        list.remove(list.size() - 1);

        return false;
    }

    //////////////END
}
