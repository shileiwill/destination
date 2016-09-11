package chapter6.search.backtracking.dfs;
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
import java.util.ArrayList;
import java.util.List;

public class FactorCombination {

	public static void main(String[] args) {
		FactorCombination fc = new FactorCombination();
		List<List<Integer>> res = fc.getFactors(23848713);
		for (List<Integer> list : res) {
			for (int val : list) {
				System.out.print(val + "--");
			}
			System.out.println();
		}
	}

	public List<List<Integer>> getFactors2(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        dfs2(res, list, 2, n);
        return res;
    }
    
    void dfs2(List<List<Integer>> res, List<Integer> list, int pos, int n) {
        if (n == 1) {
            if (list.size() > 1) {
                res.add(new ArrayList<Integer>(list));
            }
            return;
        }
        for (int i = pos; i <= n; i++) { // n is changing
            if (n % i == 0) {
                list.add(i);
                dfs2(res, list, i, n / i);
                list.remove(list.size() - 1);
            }
        }
    }
    
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (n <= 1) {
            return res;
        }
        List<Integer> list = new ArrayList<Integer>();
        int[] factors = new int[n / 2 - 1];
        int start = 0;
        for (int i = n / 2; i >= 2; i--) {
            factors[start++] = i;
        }
        dfs(res, list, factors, 0, new int[]{n});
        return res;
    }
    // Need to use array to store remainder
    void dfs(List<List<Integer>> res, List<Integer> list, int[] factors, int pos, int[] curResult) {
        if (curResult[0] == 1) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        if (pos == factors.length) {
            return;
        }
        for (int i = pos; i < factors.length; i++) {
            if (curResult[0] % factors[i] == 0) {
                list.add(factors[i]);
                curResult[0] = curResult[0] / factors[i];
                dfs(res, list, factors, i, curResult);
                curResult[0] = curResult[0] * factors[i];
                list.remove(list.size() - 1);
            }
        }
    }
}
