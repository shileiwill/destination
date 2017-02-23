package bloomberg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 386. Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 */
public class LexicographicalNumbers {

    public List<Integer> lexicalOrderNaive(int n) {
        List<String> strList = new ArrayList<String>();
        List<Integer> intList = new ArrayList<Integer>();
        
        for (int i = 1; i <= n; i++) {
            strList.add(i + "");
        }
        
        Collections.sort(strList);
        
        for (int i = 0; i < strList.size(); i++) {
            intList.add(Integer.valueOf(strList.get(i)));
        }
        
        return intList;
    }
    
    public List<Integer> lexicalOrderGood(int n) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++) { // The first digit cant be 0
            if (i > n) {
                return res; // break is also fine
            }
            
            dfsGood(i, n, res);
        }
        return res;
    }
    
    void dfsGood(int cur, int n, List<Integer> res) {
        res.add(cur); // Only valid numbers come here. Add here only
        for (int i = 0; i <= 9; i++) { // All following possible digits
            int next = cur * 10 + i;
            if (next > n) {
                return;
            }
            dfsGood(next, n, res);
        }
    }
    
    // Make it a tree, but not binary tree
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++) { // The first digit cant be 0
            if (i <= n) {
                res.add(i);
                dfs(i, n, res);
            }
        }
        return res;
    }
    
    void dfs(int cur, int n, List<Integer> res) {
        if (cur > n) {
            return;
        }
        
        for (int i = 0; i <= 9; i++) { // All following possible digits
            int next = cur * 10 + i;
            if (next > n) {
                return;
            }
            res.add(next);
            dfs(next, n, res);
        }
    }

}
