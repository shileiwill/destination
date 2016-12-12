package leetcode4.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 274. Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

Hint:

An easy approach is to sort the array first.
What are the possible values of h-index?
A faster approach is to use extra space.
 */
public class HIndex {
    public int hIndex2(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int cite : citations) {
            map.put(cite, map.getOrDefault(cite, 0) + 1);
            min = Math.min(min, cite);
            max = Math.max(max, cite);
        }
        
        int sum = 0;
        for (int i = max; i >= 0; i--) { // This is not efficient at all
            if (map.containsKey(i)) {
                sum += map.get(i);
            }
            if (sum >= i) {
                return i;
            }
        }
        
        return 0;
    }
    
    public int hIndexBucketSort(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        
        int len = citations.length;
        int[] arr = new int[len + 1]; // One more
        
        for (int i = 0; i < len; i++) {
            if (citations[i] > len) { // H can't exceed the number of his papers
                arr[len]++;
            } else {
                arr[citations[i]]++;
            }
        }
        
        int sum = 0;
        for (int i = len; i >= 0; i--) {
            sum += arr[i];
            if (sum >= i) {
                return i;
            }
        }
        
        return 0;
    }
    
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        
        int len = citations.length;
        Arrays.sort(citations);
        
        for (int i = 0; i < citations.length; i++) {
            if (len <= citations[i]) {
                return len;
            } else {
                len--;
            }
        }
        
        return 0;
    }
}
