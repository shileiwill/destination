package leetcode16.segmenttree.binaryindextree;
/**
 * 307. Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
 */
public class NumArray {
    
    int[] tree = null;
    int[] nums = null;
    public NumArray(int[] nums) {
    	this.nums = nums;
        tree = new int[nums.length + 1];
        
        for (int i = 0; i < nums.length; i++) {
            helper(i + 1, nums[i]);
        }
    }
    
    public void helper(int i, int val) {
        while (i < tree.length) {
            tree[i] += val;
            i = getNext(i);
        }
    }
    
    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        i = i + 1;
        while (i < tree.length) {
            tree[i] += diff;
            i = getNext(i);
        }
    }
    
    public int sumRange(int i, int j) {
        int sum1 = 0;
        // i = i + 1;
        while (i > 0) {
            sum1 += tree[i];
            i = getParent(i);
        }
        
        int sum2 = 0;
        j = j + 1;
        while (j > 0) {
            sum2 += tree[j];
            j = getParent(j);
        }
        
        return sum2 - sum1;
    }
    
    int getNext(int index) {
        int onlyLastDigit1 = (index - (index & (index - 1)));
        return index + onlyLastDigit1;
    }
    
    int getParent(int index) {
        return (index & (index - 1));
    }
    
    // [1,3,5]],[0,2],[1,2],[0,2]
//    ["NumArray","update","update","update","sumRange","update","sumRange","update","sumRange","sumRange","update"]
//    		[[[7,2,7,2,0]],[4,6],[0,2],[0,9],[4,4],[3,8],[0,4],[4,1],[0,3],[0,4],[0,4]]
    public static void main(String[] args) {
    	int[] arr = {7,2,7,2,0};
    	NumArray na = new NumArray(arr);
    	
    	int res1 = na.sumRange(0, 2);
    	System.out.println(res1);
    	na.update(4,6);
    	
    	na.update(0,2);
    	
    	na.update(0,9);
    	
    	int res2 = na.sumRange(4,4);
    	System.out.println(res2);
    	
    	na.update(3,8);
    	
    	int res3 = na.sumRange(0, 4);
    	System.out.println(res3);
	}
}