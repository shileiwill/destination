package leetcode6.divideandconquer;
/**
 * 327. Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 */
public class CountOfRangeSum {
    public int countRangeSumWithSumArray(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        int res = 0;
        
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        for (int i = 0; i < nums.length; i++) {// From 0 to the second to end
            for (int j = i + 1; j <= nums.length; j++) { // From 1 to end
                long cur = sum[j] - sum[i];
                if (cur >= lower && cur <= upper) {
                    res++;            
                }
            }
        }
        
        return res;
    }
    
    public int countRangeSumWithoutSumArray(int[] nums, int lower, int upper) {
        int res = 0;
        
        for (int i = 0; i < nums.length; i++) { // Should have all numbers, as it can be itself
            long curSum = nums[i];
            if (curSum >= lower && curSum <= upper) {
                res++;            
            }
            for (int j = i + 1; j < nums.length; j++) {
                curSum += nums[j];
                if (curSum >= lower && curSum <= upper) {
                    res++;            
                }
            }
        }
        
        return res;
    }
    
    public int countRangeSumMergeSort(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        return countWhileMergeSort(sum, 0, sum.length, lower, upper);
    }
    
    int countWhileMergeSort(long[] sum, int left, int right, int lower, int upper) {
        if (right - left <= 1) {
            return 0;
        }
        int mid = (left + right) / 2;
        
        int count = countWhileMergeSort(sum, left, mid, lower, upper) + countWhileMergeSort(sum, mid, right, lower, upper);
        
        int j = mid, k = mid, t = mid;
        long[] cache = new long[right - left]; 
        
        for (int i = left, r = 0; i < mid; i++, r++) { // r is the index of cache
            while (j < right && sum[j] - sum[i] < lower) {
                j++; // Find the first j which is larger equal than lower. why also equal, because include left edge
            }
            
            while (k < right && sum[k] - sum[i] <= upper) {
                k++; // Find the first k which is larger than upper. Doesn't include right edge
            }
            
            while (t < right && sum[t] <= sum[i]) { // Equal or not doesnt matter
                cache[r++] = sum[t++];
            }
            
            cache[r] = sum[i]; // Set sum[i] no matter what
            count += k - j; // Since left edge included while right edge not
        }
        
        System.arraycopy(cache, 0, sum, left, t - left);
        
        return count;
    }
    
    TreeNode insert(TreeNode root, long num) {
        if (root == null) {
            return new TreeNode(num);
        } else if (root.val == num) {
            root.count++; // One more duplicate
        } else if (num < root.val) { // Go left
            root.leftSize++;
            root.left = insert(root.left, num);
        } else {
            root.rightSize++;
            root.right = insert(root.right, num);
        }
        return root;
    }
    
    int countSmaller(TreeNode node, long target) {
        if (node == null) {
            return 0;
        } else if (node.val == target) {
            return node.leftSize;
        } else if (target < node.val) { // Go left
            return countSmaller(node.left, target);
        } else { //target is too big, go right
            return node.leftSize + node.count + countSmaller(node.right, target);
        }
    }
    
    // Both smaller and larger are not inclusive
    int countLarger(TreeNode node, long target) {
        if (node == null) {
            return 0;
        } else if (node.val == target) {
            return node.rightSize;
        } else if (target < node.val) {
            return node.rightSize + node.count + countLarger(node.left, target);
        } else {
            return countLarger(node.right, target);
        }
    }
    
    int rangeSize(TreeNode root, long lower, long upper) {
        int total = root.count + root.leftSize + root.rightSize;
        int smaller = countSmaller(root, lower);
        int larger = countLarger(root, upper);
        return total - smaller - larger;
    }
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        int res = 0;
        TreeNode root = new TreeNode(sum[0]);
        for (int i = 1; i < sum.length; i++) {
            res += rangeSize(root, sum[i] - upper, sum[i] - lower);
            insert(root, sum[i]);
        }
        
        return res;
    }
    
    class TreeNode {
        long val;
        int count = 1;
        TreeNode left, right;
        int leftSize, rightSize;
        
        TreeNode(long val) {
            this.val = val;
        }
    }
}