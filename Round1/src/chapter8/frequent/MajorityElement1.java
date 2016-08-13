package chapter8.frequent;

import java.util.Arrays;

/**
 * 169. Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
 * @author Lei
 *
 */
public class MajorityElement1 {
    public int majorityElement(int[] num) {
        int candidate = 0;
        int count = 0;
        
        for (int i = 0; i < num.length; i++) {
            if (count == 0) { // nothing left yet
                candidate = num[i];
                count += 1;
            } else if (num[i] != candidate) { // not the same, delete all
                count--;
            } else {
                count++; // The same element, increase count
            }
        }
        
        return candidate;
    }
    public int majorityElement2(int[] num) {
        Arrays.sort(num);
        return num[num.length / 2];
    }
}