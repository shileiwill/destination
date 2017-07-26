package company.facebook.others;

import java.util.*;

/**This is my phone interview.
 * Created by weixwu on 6/29/2017.
 */
public class PrimeSet {
	// Array中都是质数，求所有可能的乘积
    public List<Integer> getPrimeSet(int[] nums){
        List<Integer> res = new ArrayList<>();
        return dfs(nums, new ArrayList<>(), 0, 1);
    }

    private List<Integer> dfs(int[] nums, List<Integer> res, int index, int num){
        if (num != 1) {
        	res.add(num);
        }
        
        for (int i = index; i < nums.length; i++){
            dfs(nums, res, i + 1, num * nums[i]);
        }
        return res;
    }

    public static void main(String args[]){
        PrimeSet ps = new PrimeSet();
        List<Integer> res = ps.getPrimeSet(new int[]{2,3,5});
        for (int r : res){
            System.out.println(r);
        }
    }
}
