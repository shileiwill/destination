package chapter5.dp;
/**
 * 给n个正整数，一个数target，问能否从n个数中取出若干个数，他们的和为target
 * 
 * 背包问题的所有数都不能是负数
 * @author Lei
 *
 */
public class Backpack0_1 {

	public static void main(String[] args) {
		int[] nums = {2, 3, 5, 7};
		int target = 10;
		
//		Backpack0_1 b01 = new Backpack0_1();
//		boolean res = b01.backpack(nums, target);
//		
//		System.out.println(res);
	}

	boolean backpack1(int[] nums, int target) {
		// 从数组中取出前i个数，看看能不能组成"target"
		boolean[][] hash = new boolean[nums.length + 1][target + 1];
		
		for (int i = 0; i <= nums.length; i++) {
			hash[i][0] = true;
		}
		
		for (int i = 1; i <= target; i++) {
			hash[0][i] = false; // 从前0个数中取，肯定啥也取不出来
		}
		
		for (int i = 1; i <= nums.length; i++) {
			for (int j = 1; j <= target; j++) { // Nums 永远是 -1 减一
				if (hash[i - 1][j] || (j - nums[i - 1] >= 0 && hash[i - 1][j - nums[i - 1]])) {
					hash[i][j] = true;
				}
			}
		}
		
		return hash[nums.length][target];
	}
	/**
	 * Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

 Notice

You can not divide any item into small pieces.

Have you met this question in a real interview? Yes
Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.
	 * @param target
	 * @param A
	 * @return
	 */
    public int backPack2(int target, int[] A) {
        // We make it true false, even though we want int
        boolean[][] hash = new boolean[A.length + 1][target + 1];
        
        for (int i = 0; i <= A.length; i++) {
            hash[i][0] = true;
        }
        
        for (int i = 1; i <= target; i++) {
			hash[0][i] = false; // 从前0个数中取，肯定啥也取不出来
		}
		
		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= target; j++) { // Nums 永远是-1
				if (hash[i - 1][j] || (j - A[i - 1] >= 0 && hash[i - 1][j - A[i - 1]])) {
					hash[i][j] = true;
				}
			}
		}
		
		for (int i = target; i >= 0; i--) {
		    if (hash[A.length][i]) {
		        return i;
		    }
		}
		
		return -1;
    }
}
