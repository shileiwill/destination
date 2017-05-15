package company.linkedin;

import java.util.Iterator;
import java.util.Random;

/**
 * given a stream of integer, randomly choose k elements from n, ensure each value among k has equal probability.  
 */
public class ReserviorSampling {

	public static void main(String[] args) {

	}

	int[] randomK(Iterator<Integer> it, int k) {
		int[] res = new int[k];
		
		int count = 0;
		while (count < k && it.hasNext()) {
			res[count++] = it.next();
		}
		
		if (!it.hasNext()) {
			return res;
		}
		
		Random ran = new Random();
		while (it.hasNext()) {
			int curVal = it.next();
			int ranIndex = ran.nextInt(++count);

			if (ranIndex <= k - 1) {
				res[ranIndex] = curVal;
			}
		}
		
		return res;
	}
	
	// 398 Random pick index
    public int pick(int target, int[] nums) {
        int res = -1;
        int count = 0; // 每一个target出现的概率都是一样的
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (ran.nextInt(++count) == 0) { // The probability of 0 is 1/N
                    res = i;
                }
            }
        }
        
        return res;
    }
}
