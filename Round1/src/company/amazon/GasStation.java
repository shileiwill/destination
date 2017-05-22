package company.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GasStation {

	public static void main(String[] args) {
//		Integer[] gas0 = {141, 669, 822, 180, 778, 138, 453, 561, 111, 551, 131, 28, 946, 211, 854, 371, 565, 628, 545, 392, 171, 860, 396, 148, 864, 408, 103, 566, 59, 233, 470, 114, 526, 400, 564, 421, 556, 343, 823, 731, 87, 433, 187, 123, 737, 409, 288, 512, 99, 224, 58, 257, 722, 313, 314, 171, 400, 686, 250, 519, 131, 94, 535, 103, 177, 780, 319, 926, 280, 973, 88, 598, 682, 311, 277, 426, 28, 176, 542, 411, 155, 633, 335, 683, 846, 506, 9, 593, 71, 305, 929, 53, 54, 558, 448, 8, 528};
//		Integer[] cost0 = {982, 934, 504, 226, 710, 775, 705, 545, 647, 760, 161, 185, 95, 554, 750, 333, 773, 887, 279, 600, 9, 664, 555, 969, 203, 233, 440, 958, 399, 351, 393, 123, 367, 637, 235, 134, 664, 688, 70, 885, 326, 45, 659, 240, 827, 892, 481, 80, 208, 441, 213, 586, 970, 326, 960, 558, 563, 623, 177, 252, 598, 985, 18, 758, 496, 439, 622, 598, 97, 263, 275, 604, 861, 454, 376, 872, 873, 239, 964, 321, 187, 261, 499, 195, 4, 443, 414, 785, 809, 632, 431, 427, 271, 699, 699, 843, 33};
//		
//		List<Integer> gas = new ArrayList<Integer>(Arrays.asList(gas0));
//		List<Integer> cost = new ArrayList<Integer>(Arrays.asList(cost0));

		Integer[] arr = {-65, 41, 15, -11, 69, 23, -63, -4, 49, -99, -61, 17, -61};
		List<Integer> cost = new ArrayList<Integer>(Arrays.asList(arr));
		System.out.println(maxp3(cost));
	}
	
	/**
	 * Highest Product
	 * Given an array of integers, return the highest product possible by multiplying 3 numbers from the array
		array of integers e.g {1, 2, 3}
		 NOTE: Solution will fit in a 32-bit signed integer 
		Example:
		
		[0, -1, 3, 100, 70, 50]
		
		=> 70*50*100 = 350000
	 */
	// An easier way to find MAX/MIN is to use PriorityQueue
    public static int maxp3(List<Integer> a) {
        int res = Integer.MIN_VALUE;
        int N = a.size();
        
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        
        for (int i = 0; i < N; i++) {
            int now = a.get(i);
            
            if (now >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = now;
            } else if (now >= max2) {
                max3 = max2;
                max2 = now;
            } else if (now >= max3) {
                max3 = now;
            }
        }
        
        // Must be separate
        for (int i = 0; i < N; i++) {
            int now = a.get(i);
            
            if (now <= min1) {
                min2 = min1;
                min1 = now;
            } else if (now <= min2) {
                min2 = now;
            }
        }
        
        int res1 = max1 * max2 * max3;
        int res2 = res1;
        if (min1 != Integer.MAX_VALUE && min2 != Integer.MAX_VALUE) {
            res2 = max1 * min1 * min2;
        }
        return Math.max(res1, res2);
    }

	public static int canCompleteCircuit(final List<Integer> gas, final List<Integer> cost) {
	    int start = 0;
	    int leftover = 0;
	    int N = gas.size();
	    
	    int gasSum = 0;
	    int costSum = 0;
	    
	    for (int i = 0; i < N; i++) {
	        gasSum += gas.get(i);
	        costSum += cost.get(i);
	    }
	    
	    if (costSum > gasSum) {
	        return -1;
	    }
	    
//	    boolean found = false;
	    int i = 0;
	    for (i = 0; i < N; i++) {
//	        if (i == N - 1 && leftover + gas.get(i) - cost.get(i) >= 0) {
//	            found = true;
//	        }
	        
	        leftover = leftover + gas.get(i) - cost.get(i);
	        
	        if (leftover < 0) {
	            start = i + 1;
	            leftover = 0;
	        }
	    }
	    
	    return start;
//	    return found ? start : -1;
	}
}
