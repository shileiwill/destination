package company.amazon;

import java.util.List;
// 相隔最远的两个数 A, B, 并且A < B, 返回indexOf(B) - indexOf(A)
public class MaxDistance {
	public int maximumGap2(final List<Integer> a) {
	    if (a == null || a.size() == 0) {
	        return -1;
	    }
	    
	    int res = 0;
	    
	    for (int i = 0; i < a.size(); i++) {
	        int now = a.get(i);
	        
	        for (int j = a.size() - 1; j > i; j--) {
	            int next = a.get(j);
	            
	            if (next >= now) {
	                res = Math.max(res, j - i);
	                break;
	            }
	        }
	    }
	    
	    return res;
	}
	
	// This is a good 2 pointers question
	/**
	 * To solve this problem, we need to get two optimum indexes of arr[]: left index i and right index j. 
	 * For an element arr[i], we do not need to consider arr[i] for left index if there is an element smaller than arr[i] on left side of arr[i]. 
	 * Similarly, if there is a greater element on right side of arr[j] then we do not need to consider this j for right index. 
	 * So we construct two auxiliary arrays LMin[] and RMax[] such that LMin[i] holds the smallest element on left side of arr[i] including arr[i], 
	 * and RMax[j] holds the greatest element on right side of arr[j] including arr[j]. After constructing these two auxiliary arrays, 
	 * we traverse both of these arrays from left to right. While traversing LMin[] and RMa[] if we see that LMin[i] is greater than RMax[j], 
	 * then we must move ahead in LMin[] (or do i++) because all elements on left of LMin[i] are greater than or equal to LMin[i]. 
	 * Otherwise we must move ahead in RMax[j] to look for a greater j – i value.
	 */
	public int maximumGap(final List<Integer> a) {
	    if (a == null || a.size() == 0) {
	        return -1;
	    }
	    
	    int len = a.size();
        int[] leftMin = new int[len];
        leftMin[0] = a.get(0);
        
        int[] rightMax = new int[len];
        rightMax[len - 1] = a.get(len - 1);
        
        for (int i = 1; i < len; i++) {
            leftMin[i] = Math.min(a.get(i), leftMin[i - 1]);
        }
        
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(a.get(i), rightMax[i + 1]);
        }
        
        int i = 0, j = 0, res = -1; // 2 pointers start from the beginning 
        while (i < len && j < len) {
            if (leftMin[i] <= rightMax[j]) {
                res = Math.max(res, j - i);
                j++; // Go smaller potentially
            } else {
                i++; // Potentially go bigger
            }
        }
        
        return res;
	}
}
