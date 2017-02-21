package leetcode7.binarysearch;
/**
 * 275. Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

Hint:

Expected runtime complexity is in O(log n) and the input is sorted.
 */
public class HIndex2 {
    // There can be duplicate
    public int hIndex2(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        
        int max = citations.length;
        
        for (int i = 0; i < citations.length; i++) {
            if (max <= citations[i]) {
                return max;
            } else {
                max--;
            }
        }
        
        return 0;
    }
    
    public int hIndexNewer(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        
        int len = citations.length;
        int left = 0, right = len - 1;
        
        if (citations[0] > len) {
            return len;
        }
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (citations[mid] >= len - mid) {
                right = mid; // Go Left, try to find a smaller index
            } else {
                left = mid; // Go right
            }
        }
        
        if (citations[left] >= len - left) {
            return len - left;
        } 
        // To deal with [0], a single paper with citation 0
        return citations[right] == 0 ? 0 : len - right;
    }
    
    /*
    The idea is to search for the first index from the sorted array so that :
    citations[index] >= length(citations) - index. 
    And return (length - index) as the result.
    */
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        
        int len = citations.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] > len - mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return len - left;
    }
}
