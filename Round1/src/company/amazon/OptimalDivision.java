package company.amazon;
/**
 * 553. Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.

However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain redundant parenthesis.

Example:
Input: [1000,100,10,2]
Output: "1000/(100/10/2)"
Explanation:
1000/(100/10/2) = 1000/((100/10)/2) = 200
However, the bold parenthesis in "1000/((100/10)/2)" are redundant, 
since they don't influence the operation priority. So you should return "1000/(100/10/2)". 

Other cases:
1000/(100/10)/2 = 50
1000/(100/(10/2)) = 50
1000/100/10/2 = 0.5
1000/100/(10/2) = 2
Note:

The length of the input array is [1, 10].
Elements in the given array will be in range [2, 1000].
There is only one optimal division for each test case.
 */
public class OptimalDivision {
    public String optimalDivision(int[] nums) {
        ResultType[][] hash = new ResultType[nums.length][nums.length];
        ResultType res = helper(nums, 0, nums.length - 1, "", hash);
        return res.maxStr;
    }
    
    ResultType helper(int[] nums, int left, int right, String s, ResultType[][] hash) {
        if (hash[left][right] != null) {
            return hash[left][right];
        }
        
        ResultType rt = new ResultType();
        
        if (left == right) {
            rt.minVal = nums[left];
            rt.maxVal = nums[left];
            rt.minStr = nums[left] + "";
            rt.maxStr = nums[left] + "";
            
            hash[left][right] = rt;
            return rt;
        }
        
        rt.minVal = Integer.MAX_VALUE;
        rt.maxVal = Integer.MIN_VALUE;
        rt.minStr = rt.maxStr = "";
        for (int i = left; i < right; i++) { // Less than right
            ResultType leftRT = helper(nums, left, i, "", hash);
            ResultType rightRT = helper(nums, i + 1, right, "", hash);
            
            if (rt.minVal > leftRT.minVal / rightRT.maxVal) {
                rt.minVal = leftRT.minVal / rightRT.maxVal;
                if (i + 1 == right) {
                    rt.minStr = leftRT.minStr + "/" + rightRT.maxStr;
                } else {
                    rt.minStr = leftRT.minStr + "/" + "(" + rightRT.maxStr + ")";
                }
            }
            
            if (rt.maxVal < leftRT.maxVal / rightRT.minVal) {
                rt.maxVal = leftRT.maxVal / rightRT.minVal;
                if (i + 1 == right) {
                    rt.maxStr = leftRT.maxStr + "/" + rightRT.minStr;
                } else {
                    rt.maxStr = leftRT.maxStr + "/" + "(" + rightRT.minStr + ")";
                }
            }
        }
        
        hash[left][right] = rt;
        return rt;
    }
}

class ResultType {
    double minVal, maxVal;
    String minStr, maxStr;
}
