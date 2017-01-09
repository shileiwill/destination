package leetcode9.backtracking;

import java.util.ArrayList;
import java.util.List;
/**
 * 254. Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note: 
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples: 
input: 1
output: 
[]
input: 37
output: 
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
 */
public class FactorCombinations {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (n <= 3) {
            return res;
        }
        
        helper(res, list, n, n / 2);
        
        return res;
    }
    
    void helper(List<List<Integer>> res, List<Integer> list, int n, int pos) {
//        if (n == 0) {
//            res.add(new ArrayList<Integer>(list));
//            return;
//        }
        
        if (n <= 1) {
            if (list.size() > 1) {
                res.add(new ArrayList<Integer>(list));
            }
            return;
        }
        
        for (int i = pos; i >= 2; i--) {
            if (n % i == 0) {
                list.add(i);
                helper(res, list, n / i, i);
                list.remove(list.size() - 1);
            }
        }
    }
    
    public static void main(String[] args) {
    	FactorCombinations fc = new FactorCombinations();
    	List<List<Integer>> factors = fc.getFactors(23848713);
    	
    	for (List<Integer> list : factors) {
    		for (int val : list) {
    			System.out.print(val + "-");
    		}
    		System.out.println();
    	}
    }
}
