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
}
