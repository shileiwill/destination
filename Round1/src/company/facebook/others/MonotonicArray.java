package company.facebook.others;

/**
 * Created by weixwu on 7/6/2017.
 * 这个代码很漂亮
 */
public class MonotonicArray {
    public boolean monotonicArray(int[] nums){
        if (nums.length == 0 || nums.length == 1) return true;
        boolean increasing = false;
        boolean decreasing = false;
        for(int i = 1; i < nums.length; i++){
            if (nums[i] > nums[i - 1]) increasing = true;
            if (nums[i] < nums[i - 1]) decreasing = true;
            if (increasing && decreasing) return false;
        }
        return true;
    }

    public static void main(String args[]){
        MonotonicArray ma = new MonotonicArray();
        System.out.print(ma.monotonicArray(new int[] {10,1,1,1,4}));
    }
}
