package Facebook;

/**
 * Created by weixwu on 7/11/2017.
 */
public class AtLeastTwoMaxSubarray {
    public int maxSubarray(int[] nums){
        int max = nums[0], maxSoFar = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++){
            max = Math.max(nums[i - 1] + nums[i], max + nums[i]);
            maxSoFar = Math.max(max, maxSoFar);
        }
        return maxSoFar;
    }

    public static void main(String args[]){
        AtLeastTwoMaxSubarray ms = new AtLeastTwoMaxSubarray();
        System.out.print(ms.maxSubarray(new int[]{-2, 1, 1, -1, 5, -2, -4, -1}));
    }
}
