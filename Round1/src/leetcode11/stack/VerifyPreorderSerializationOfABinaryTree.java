package leetcode11.stack;
/**
 * 331. One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false
 */
public class VerifyPreorderSerializationOfABinaryTree {
    // If we consider null node as "#", the tree is a full tree. Full tree has a nice feature, number of leaves = number of non-leaves + 1
    public boolean isValidSerialization2(String preorder) {
        int nonLeaves = 0, leaves = 0, i = 0;
        
        String[] arr = preorder.split(",");
        
        while (i < arr.length && nonLeaves + 1 != leaves) {
            if (arr[i].equals("#")) {
                leaves++;
            } else {
                nonLeaves++;
            }
            i++;
        }
        
        return i == arr.length && nonLeaves + 1 == leaves;
    }
    
    // We can also calculate and compare indegree(parent) and outdegree(children)
    /*
     In a binary tree, if we consider null as leaves, then

    all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
    all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).
    Suppose we try to build this tree. During building, we record the difference between out degree and in degree diff = outdegree - indegree. When the next node comes, we then decrease diff by 1, because the node provides an in degree. If the node is not null, we increase diff by 2, because it provides two out degrees. If a serialization is correct, diff should never be negative and diff will be zero when finished.
    */
    public boolean isValidSerialization(String preorder) {
        String[] arr = preorder.split(",");
        
        int degree = -1;
        for (String s : arr) {
            degree++; // Each node has a indegree, except for root (compensated already)
            
            if (degree > 0) {
                return false;
            }
            
            if (!s.equals("#")) {
                degree -= 2; // 2 outdegree for non-leave nodes
            }
        }
        
        return degree == 0;
    }
}
