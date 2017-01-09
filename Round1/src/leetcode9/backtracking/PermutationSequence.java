package leetcode9.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 60. The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
 */
public class PermutationSequence {
    /*
    say n = 4, you have {1, 2, 3, 4}

    If you were to list out all the permutations you have
    
    1 + (permutations of 2, 3, 4)
    
    2 + (permutations of 1, 3, 4)
    
    3 + (permutations of 1, 2, 4)
    
    4 + (permutations of 1, 2, 3)
    
    
    We know how to calculate the number of permutations of n numbers... n! So each of those with permutations of 3 numbers means there are 6 possible permutations. Meaning there would be a total of 24 permutations in this particular one. So if you were to look for the (k = 14) 14th permutation, it would be in the
    
    3 + (permutations of 1, 2, 4) subset.
    */
    public String getPermutation(int n, int k) {
        List<Integer> number = new ArrayList<Integer>();
        int[] factorial = new int[n + 1];
        StringBuilder sb = new StringBuilder();
        
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        
        for (int i = 1; i <= n; i++) {
            number.add(i);
        }
        
        k--;
        for (int i = 1; i <= n; i++) {
            int index = k / (factorial[n - i]);
            sb.append(number.get(index));
            number.remove(index);
            k -= index * factorial[n - i];
        }
        
        return sb.toString();
    }
}
