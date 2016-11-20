package chapter2.sortedArrays;
/**
 * 349. Given two arrays, write a function to compute their intersection.

Example:
Hello World!
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IntersectionOf2Arrays {
    // Sort one and binary search the other
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Arrays.sort(nums1);
        
        Set<Integer> set = new HashSet<Integer>();
        
        for (int num : nums2) {
            if (binarySearchFound(nums1, num)) {
                set.add(num);
            }
        }
        
        int[] res = new int[set.size()];
        int index = 0;
        for (int num : set) {
            res[index++] = num;
        }
        
        return res;
    }
    
    boolean binarySearchFound(int[] N, int target) {
        int left = 0;
        int right = N.length - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
        
            if (N[mid] == target) {
                return true;
            } else if (N[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        if (N[left] == target || N[right] == target) {
            return true;
        }
        
        return false;
    }
    // Sort and "Merge 2 sorted array to one"
    public int[] intersection1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int[] temp = new int[nums1.length]; // anyone's length
        int index = 0;
        int i = 0, j = 0;
        
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) { // Our goal is to find match/equal
                if (index == 0 || temp[index - 1] != nums1[i]) { // Make sure not redundent
                    temp[index++] = nums1[i];
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        
        // index will be the real length
        int[] res = new int[index];
        for (int k = 0; k < index; k++) {
            res[k] = temp[k];
        }
        
        return res;
    }
    // Using 2 HashSets
    public int[] intersection2(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<Integer>();
        HashSet<Integer> set2 = new HashSet<Integer>();
        // int[] smaller = nums1.length <= nums2.length ? nums1 : nums2;
        // int[] larger = nums1.length <= nums2.length ? nums2 : nums1;
        
        for (int small : nums1) {
            set.add(small);
        }
        
        for (int num : nums2) {
            if (set.contains(num)) {
                set2.add(num);
            }
        }
        
        int len = set2.size();
        int[] res = new int[len];
        
        int i = 0;
        Iterator<Integer> it = set2.iterator();
        while (it.hasNext()) {
            int num = it.next();
            res[i++] = num;
        }
        
        return res;
    }
}