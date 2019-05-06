package company.snapchat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104
 * @author lei2017
 *
 */
public class FindKClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - 1;
        
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if(x <= arr[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        List<Integer> res = new ArrayList<Integer>();
        
        while(res.size() < k && left >= 0 && right < arr.length) {
            if(Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                res.add(arr[left]);
                left--;
            } else {
                res.add(arr[right]);
                right++;
            }
        }
        
        while(res.size() < k && left >= 0) {
            res.add(arr[left]);
            left--;
        }
        
        while(res.size() < k && right < arr.length) {
            res.add(arr[right]);
            right++;
        }
        
        Collections.sort(res);
        
        return res;
    }
}