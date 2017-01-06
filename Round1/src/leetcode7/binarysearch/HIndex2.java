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
