lowest_common_ancestor.java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pList = new ArrayList<TreeNode>();
        // pList.add(root);
        helper(root, p, pList);

        List<TreeNode> qList = new ArrayList<TreeNode>();
        // qList.add(root);
        helper(root, q, qList);

        for (TreeNode n : pList) {
            System.out.println("p: " + n.val);
        }
        
        for (TreeNode n : qList) {
            System.out.println("q: " + n.val);
        }

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
        if (node == target) {
            list.add(node);
            return true;
        }
        if (node == null) {
            return false;
        }

        list.add(node);
        boolean left = helper(node.left, target, list);
        if (left) {
            return true;
        }
        boolean right = helper(node.right, target, list);
        if (right) {
            return true;
        }
        list.remove(list.size() - 1);

        return false;
    }
}


 // use a global variable: found
class Solution {
    boolean found = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pList = new ArrayList<TreeNode>();
        // pList.add(root);
        helper(root, p, pList);

        found = false;
        List<TreeNode> qList = new ArrayList<TreeNode>();
        // qList.add(root);
        helper(root, q, qList);

        for (TreeNode n : pList) {
            System.out.println("p: " + n.val);
        }
        
        for (TreeNode n : qList) {
            System.out.println("q: " + n.val);
        }

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

    void helper (TreeNode node, TreeNode target, List<TreeNode> list) {
        if (node == target || found) {
            list.add(node);
            found = true;
            return;
        }
        if (node == null) {
            return;
        }

        list.add(node);
        helper(node.left, target, list);
        if (found) {
            return;
        }
        helper(node.right, target, list);
        if (found) {
            return;
        }
        list.remove(list.size() - 1);
    }
}