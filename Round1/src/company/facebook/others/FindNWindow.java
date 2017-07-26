package company.facebook.others;

/**
 * Created by weixwu on 7/2/2017.
 * 还没看懂这个题啥意思
 */
public class FindNWindow {
    public int findNWindow(int[] nums, int k, int n) {
        int len = nums.length;
		int[] lastMaxK = new int[len];
        int[] maxK = new int[len];
        
        for (int i = 0; i < n; i++) { // N个window
            int curSum = 0;
            for (int j = i * k; j < len; j++) {
                curSum += nums[j];
                if (j == (i + 1) * k - 1) {
                	maxK[j] = (j -k >= 0 ? lastMaxK[j - k]: lastMaxK[0]) + curSum;
                }
                
                if (j >= (i + 1) * k) {
                    curSum -= nums[j - k];
                    maxK[j] = Math.max(lastMaxK[j - k] + curSum, maxK[j - 1]);
                }
            }
            int[] t = lastMaxK;
            lastMaxK = maxK;
            maxK = t;
        }
        
        return lastMaxK[len - 1];
    }

    public static void main(String args[]){
        FindNWindow fw = new FindNWindow();
        System.out.println(fw.findNWindow(new int[] {1,2,1,2,6,7,5,1}, 2, 3));
    }
}
