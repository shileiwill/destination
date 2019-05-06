package company.snapchat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Important!!!
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=511875

给一组数和一个target数，要求用数组里所有的数 和 +, -, *, /, (, ) 六种operator，能否得到target数。例子：【1，2，3, 4】， 25；返回 true，因为 （1 + 4）* (2 + 3) = 25

 * 679. 24 Game
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24
Example 2:
Input: [1, 2, 1, 2]
Output: False
Note:
The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 * @author lei2017
 *
 */

/**
这道题体面要求是四个数，填入+、-、*、/和()，来使其结果为24，实际上可以看成一个组合问题，比如从四个数中任意取出2个，算出和，在与剩下的两个数组合到一起，重复这个过程，如果最后能得到24,就返回True，否则返回False

电话里给的例子只有3个数，帖子里的例子是我想的。而且店面题目给的是个int list，可以有任意多数。当然本质可能差不多. 应该也是只能两个两个的选数进行dfs吧，没想到更好的办法。
**/
class Game24 {
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.addAll(Arrays.asList(3,2,1));
		
		list.add(list.size(), 5);
		
		for(int val : list) {
			System.out.println(val);
		}
	}
	
    boolean flag = false;
    double eps = 0.00001;
    
    public boolean judgePoint24(int[] nums) {
        List<Double> numsD = new ArrayList<Double>();
        for(int num : nums) {
            numsD.add((double)num);
        }
        
        helper(numsD);
        return flag;
    }
    
    void helper(List<Double> numsD) { //这个list一直在变
        if(flag) {
            return;
        }
        
        if(numsD.size() == 1) { //只剩下一个数，并且是24
            if (Math.abs(numsD.get(0) - 24) < eps) {
                flag = true;
            }
            return;
        }
        
        for(int i = 0; i < numsD.size(); i++) {
            for(int j = i + 1; j < numsD.size(); j++) { 
                // Firstly, pick up 2 numbers.
                double A = numsD.get(i);
                double B = numsD.get(j);
                
                // Put remaining number(s) to a list
                List<Double> remaining2Num = new ArrayList<Double>();
                for(int x = 0; x < numsD.size(); x++) {
                    if(x != i && x != j) {
                        remaining2Num.add(numsD.get(x));
                    }
                }
                
                // Get all possible results of the firstly picked 2 numbers
                List<Double> possibleResultsOfFirst2 = new ArrayList<Double>();
                possibleResultsOfFirst2.addAll(Arrays.asList(A + B, A * B, A / B, B / A, A - B, B - A));
                
                // Try all above possible results one by one
                for(Double aPossibleRes : possibleResultsOfFirst2) {
                    remaining2Num.add(aPossibleRes);
                    helper(remaining2Num);
                    remaining2Num.remove(remaining2Num.size() - 1);
                }
            }
        }
    }
}