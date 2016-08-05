package chapter8.frequent1;

import java.util.ArrayList;
/**
 * Given n unique integers, number k (1<=k<=n) and target.

Find all possible k integers where their sum is target.

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4], k = 2, target = 5. Return:

[
  [1,4],
  [2,3]
]
 * @author Lei
 *
 */
public class KSum2 {
    public ArrayList<ArrayList<Integer>> kSumII(int[] A, int k, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        helper(A, k, target, res, list, 0);
        
        return res;
    }
    
    void helper(int[] A, int k, int target, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list, int pos) {
        if (target < 0 || list.size() > k) {
            return;
        }
        
        if (target == 0 && list.size() == k) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        
        for (int i = pos; i < A.length; i++) {
            list.add(A[i]);
            helper(A, k, target - A[i], res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
