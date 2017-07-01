package company.linkedin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CanIWin {

	public static void main(String[] args) {

	}

	// Choose from 1 to max, inclusive. Whoever first meet target will win
	public boolean canIWin(int max, int target) {
		if (target < 0) {
			return true;
		}
		
		if (max * (max + 1) / 2 < target) {
			return false;
		}
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean[] visited = new boolean[max];
		
		return helper(max, target, map, visited);
	}

	private boolean helper(int max, int target, Map<String, Boolean> map, boolean[] visited) {
		String str = Arrays.toString(visited);
		
		if (map.containsKey(str)) {
			return map.get(str);
		}
		
		for (int i = 1; i <= max; i++) {
			if (!visited[i - 1]) {
				visited[i - 1] = true;
				
				if (i >= target || !helper(max, target - i, map, visited)) {
					map.put(str, true);
					visited[i - 1] = false;
					return true;
				}
				
				visited[i - 1] = false;
			}
		}
		
		map.put(str, false);
		return false;
	}
	
	/**
	 * There are n coins in a line. Two players take turns to take a coin from one of the ends of the line until there are no more coins left. The player with the larger amount of money wins.

	Could you please decide the first player will win or lose?
	
	Have you met this question in a real interview? Yes
	Example
	Given array A = [3,2,2], return true.
	
	Given array A = [1,2,4], return true.
	
	Given array A = [1,20,4], return false.
	 */
    public boolean firstWillWin(int[] values) {
        int len = values.length;
        Integer[][] hash = new Integer[len + 1][len + 1];

        int sum = 0;
        for (int val : values) {
            sum += val;
        }
        
        return sum < 2 * helper(values, 0, len - 1, hash);
    }
    
    int helper(int[] arr, int left, int right, Integer[][] hash) {
        if (hash[left][right] != null) {
            return hash[left][right];
        } 
        
        if (left > right) {
            hash[left][right] = 0;
        } else if (left == right) {
            hash[left][right] = arr[left];
        } else if (left + 1 == right) {
            hash[left][right] = Math.max(arr[left], arr[right]);
        } else {
            // 这个是对方能得到的， 所以用min, 哈
            int pickLeft = Math.min(helper(arr, left + 2, right, hash), helper(arr, left + 1, right - 1, hash)) + arr[left];
            int pickRight = Math.min(helper(arr, left + 1, right - 1, hash), helper(arr, left, right - 2, hash)) + arr[right];
            
            hash[left][right] = Math.max(pickLeft, pickRight);
        }
        
        return hash[left][right];
    }
}
