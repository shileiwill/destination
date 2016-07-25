package chapter4.linkedlist;

import java.util.ArrayList;
import java.util.List;


public class MergeKSortedArray {
	public static void main(String[] args) {
		MergeKSortedArray m = new MergeKSortedArray();
		int[] a = {0, 4, 10, 34};
		int[] b = {1, 5, 11};
		int[] c = {};
		int[] d = {6, 10};
		
		List<int[]> lists = new ArrayList<int[]>();
		lists.add(a);
		lists.add(b);
		lists.add(c);
		lists.add(d);
		
		int[] res = m.mergeKArrays(lists);
		
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " - ");
		}
	}
	// Divide and Conquer
    public int[] mergeKArrays(List<int[]> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        return helper(lists, 0, lists.size() - 1);
    }
    
    int[] helper(List<int[]> lists, int left, int right) {
        if (left == right) {
            return lists.get(left);
        }
        
        int mid = left + (right - left) / 2;
        int[] leftArray = helper(lists, left, mid);
        int[] rightArray = helper(lists, mid + 1, right);
        
        int[] mergeHead = mergeTwoArrays(leftArray, rightArray);
        
        return mergeHead;
    }
    
    public int[] mergeTwoArrays(int[] l1, int[] l2) {
    	if (l1 == null && l2 == null) {
    		return null;
    	} else if (l1 == null) {
    		return l2;
    	} else if (l2 == null) {
    		return l1;
    	}
    	
    	// Create extra space to hold 2 arrays
    	int[] res = new int[l1.length + l2.length];
    	
    	int i = 0;
    	int j = 0;
    	
    	while (i < l1.length && j < l2.length) {
    		if (l1[i] <= l2[j]) {
    			res[i + j] = l1[i];
    			i++;
    		} else {
    			res[i + j] = l2[j];
    			j++;
    		}
    	}
    	
    	while (i < l1.length) {
    		res[i + j] = l1[i];
			i++;
    	}
    	
    	while (j < l2.length) {
    		res[i + j] = l2[j];
			j++;
    	}
    	
    	return res;
    }
}
