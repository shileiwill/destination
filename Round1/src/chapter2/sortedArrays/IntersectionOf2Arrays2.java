package chapter2.sortedArrays;
/**
 * 350. Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IntersectionOf2Arrays2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        // We can also change this list to HashMap, key is the num, value is the count. Once found, count--
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        
        int[] smaller = nums1.length <= nums2.length ? nums1 : nums2;
        int[] larger = nums1.length <= nums2.length ? nums2 : nums1;
        
        for (int small : smaller) {
            list.add(small);
        }
        
        for (int num : larger) {
            if (list.contains(num)) {
                list2.add(num);
                list.remove(Integer.valueOf(num));
            }
            if (list.size() == 0) { // Terminate earlier
                break;
            }
        }
        
        int len = list2.size();
        int[] res = new int[len];
        
        int i = 0;
        Iterator<Integer> it = list2.iterator();
        while (it.hasNext()) {
            int num = it.next();
            res[i++] = num;
        }
        
        return res;
    }
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int[] temp = new int[nums1.length]; // anyone's length
        int index = 0;
        int i = 0, j = 0;
        
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) { // Our goal is to find match/equal
                temp[index++] = nums1[i];
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
}