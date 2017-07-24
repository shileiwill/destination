package company.facebook.others;

/**
 * Created by weixwu on 7/10/2017.
 */
public class BSTtoLinkedList {
    public TreeNode bstToLinkedList(TreeNode root){
        if (root == null) return null;
        TreeNode left = bstToLinkedList(root.left);
        if (left != null){
            root.pre = left.pre;
            left.pre.next = root;
        }
        TreeNode right = bstToLinkedList(root.right);
        if (left == null && right == null){
            root.next = root;
            root.pre = root;
        }else{
            if (left != null && right != null){
                left.pre = right.pre;
                right.pre.next = left;
            }
            if (left == null){
                root.pre = right;
                right.next = root;
            }
            if (right == null){
                left.pre = root;
                root.next = left;
            }
        }
        if (right != null){
            root.next = right;
            right.pre = root;
        }
        return left != null ? left : root;
    }

    static class TreeNode{
        TreeNode left;
        TreeNode right;
        TreeNode pre;
        TreeNode next;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }

    public static void main(String args[]){
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(11);
        //root.right.right = new TreeNode(14);
        BSTtoLinkedList sl = new BSTtoLinkedList();
        TreeNode res = sl.bstToLinkedList(root);
        System.out.println();
    }
}
