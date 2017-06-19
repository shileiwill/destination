package company.facebook;
import chapter3.binaryTree.TreeNode;

class ILikeMyOwnSolution {
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        ResultType rt = helperGood(root);
        return Math.max(rt.rob, rt.noRob);
    }
    // Good
    ResultType helperGood(TreeNode node) {
        if (node == null) {
            return new ResultType(0, 0);
        }
        
        ResultType left = helperGood(node.left);
        ResultType right = helperGood(node.right);
        
        int rob = node.val + left.noRob + right.noRob;
        int noRob = Math.max(left.rob, left.noRob) + Math.max(right.rob, right.noRob); // rob is not necessarily bigger
        
        return new ResultType(rob, noRob);
    }
}

class ResultType {
    int rob;
    int noRob;
    
    ResultType(int rob, int noRob) {
        this.rob = rob;
        this.noRob = noRob;
    }
}