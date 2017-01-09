package leetcode9.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 89. The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 */
public class GrayCode {
    /*
    n = 0: 0
    n = 1: 0, 1
    n = 2: 00, 01, 11, 10  (0, 1, 3, 2)
    n = 3: 000, 001, 011, 010, 110, 111, 101, 100 (0, 1, 3, 2, 6, 7, 5, 4)
    Take n=3 as the example, the first four sequences are exactly same as n=2 sequences.
    The second four sequences are derived of the following steps:
    
    reverse n=3 first half sequence: 010, 011, 001, 000
    Add 2^2 to each element of reversed n=2 sequence
    Now we can extend to n=i situation.
    The first half of n=i is the same sequence as n=i-1
    The second half of n=i is add 2^(i-1) to the reversed sequence of first half.
    */
    public List<Integer> grayCode(int n) {
        if (n == 0) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(0);
            return list;
        }
        
        List<Integer> res = grayCode(n - 1); // Go down to the bottom
        // List<Integer> res = new ArrayList<Integer>(tmp); // Dont need to build a new list
        
        int toAdd = 1 << (n - 1);
        for (int i = res.size() - 1; i >= 0; i--) {
            res.add(res.get(i) + toAdd);
        }
        
        return res;
    }
}
