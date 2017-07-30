package company.linkedin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CanIWin {

	public static void main(String[] args) {
		CanIWin ci = new CanIWin();
		int[] arr = {3,2,1};
		int[] arr2 = {1,20,4};
		ci.firstWillWin(arr);
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
	 * There are n coins in a line. Two players take turns to take a coin from one of the ends of the line until there are no more coins left. 
	 * The player with the larger amount of money wins.

	Could you please decide the first player will win or lose?
	
	Have you met this question in a real interview? Yes
	Example
	Given array A = [3,2,2], return true.
	
	Given array A = [1,2,4], return true.
	
	Given array A = [1,20,4], return false.
	
	给出偶数个硬币，每个硬币的价值为vi。游戏规则是两个人轮流选择拿走第一个硬币或者最后一个，并得到相应的硬币价值。问如果先走，最多可能拿到多少价值。
	 */
    public boolean firstWillWin(int[] values) {
        int len = values.length;
        // 这个只能从两头取，所以可以用下标记录
        Integer[][] hash = new Integer[len + 1][len + 1];

        int sum = 0;
        for (int val : values) {
            sum += val;
        }
        
        int res = helper(values, 0, len - 1, hash);
        System.out.println(res);
		boolean bool = sum < 2 * res;
		System.out.println(bool);
		return bool;
    }
    
    // 这个helper是直接得到一个number 很好！
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
            // 为啥用min? 我们假设对方也是聪明的，肯定会取大的数，所以留给我们的肯定是min的
            int pickLeft = Math.min(helper(arr, left + 2, right, hash), helper(arr, left + 1, right - 1, hash)) + arr[left];
            int pickRight = Math.min(helper(arr, left + 1, right - 1, hash), helper(arr, left, right - 2, hash)) + arr[right];
            
            hash[left][right] = Math.max(pickLeft, pickRight);
        }
        
        return hash[left][right];
    }
}

/**
 * 486. Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array 
 * followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. 
 * This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

Example 1:
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.
Example 2:
Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
Note:
1 <= length of the array <= 20.
Any scores in the given array are non-negative integers and will not exceed 10,000,000.
If the scores of both players are equal, then player 1 is still the winner.

// 设计一个算法，类似两个player切披萨，每次只能从两边取，求第一个player能拿到的最大sum,对手可以是任何策略。用DP做的，写了induction rule. 这个题是Uber的，LC486
 */
class PredictTheWinner {
	public static void main(String[] args) {
		PredictTheWinner winner = new PredictTheWinner();
		int[] nums = {1, 5, 233, 7};
		System.out.println(winner.predictTheWinner(nums));
		System.out.println(winner.predictTheWinner2(nums));
	}

    //The dp[i][j] saves how much more scores that the first-in-action player will get from i to j than the second player
    public boolean predictTheWinner(int[] nums) {
        int N = nums.length;
        int[][] hash = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            hash[i][i] = nums[i];
        }
        
        for (int len = 1; len < N; len++) {
            for (int i = 0; i < N - len; i++) {
                int j = i + len;
                
                hash[i][j] = Math.max(nums[i] - hash[i + 1][j], nums[j] - hash[i][j - 1]);
            }
        }
        
        return hash[0][N - 1] >= 0;
    }
    
    public int predictTheWinner2(int[] nums) {
        int N = nums.length;
        int sum = 0;
        int[][] hash = new int[N][N];
        
        for (int i = 0; i < N; i++) {
        	sum += nums[i];
            hash[i][i] = nums[i];
        }
        
        for (int len = 1; len < N; len++) {
            for (int i = 0; i < N - len; i++) {
                int j = i + len;
                
                hash[i][j] = Math.max(nums[i] - hash[i + 1][j], nums[j] - hash[i][j - 1]);
            }
        }
        // What if I want to get the maximum number player1 could get?
        return (hash[0][N - 1] + sum) / 2;
    }
    
    /**
	So assuming the sum of the array it SUM, so eventually player1 and player2 will split the SUM between themselves. 
	For player1 to win, he/she has to get more than what player2 gets. If we think from the prospective of one player, 
	then what he/she gains each time is a plus, while, what the other player gains each time is a minus. Eventually if 
	player1 can have a >0 total, player1 can win.
	
	Helper function simulate this process. In each round:
	if e==s, there is no choice but have to select nums[s]
	otherwise, this current player has 2 options:
	--> nums[s]-helper(nums,s+1,e): this player select the front item, leaving the other player a choice from s+1 to e
	--> nums[e]-helper(nums,s,e-1): this player select the tail item, leaving the other player a choice from s to e-1
	Then take the max of these two options as this player's selection, return it.
     */
    public boolean predictTheWinnerResursion(int[] nums) {
        Integer[][] hash = new Integer[nums.length][nums.length];
		int diff = helper(nums, 0, nums.length - 1, hash);
		return diff >= 0;
    }
    
    // 这个helper是计算差
    private int helper(int[] nums, int left, int right, Integer[][] hash) {    
        if(hash[left][right] != null) {
        	return hash[left][right];
        }
        
        if (left == right) {
        	hash[left][right] = nums[left];
        	return hash[left][right];
        }
        
        int chooseLeft = nums[left] - helper(nums, left + 1, right, hash);
        int chooseRight = nums[right] - helper(nums, left, right - 1, hash);
		hash[left][right] = Math.max(chooseRight, chooseLeft);
		
        return hash[left][right];
    }

    /**
     * 
The solution is quite strightforward. First off we have to eliminate primitive cases. So,

if the first player can choose a number, which is already greater than or equal to the desired total obviously it wins.
If max choosable integer is less than the desired total, but if it exceeds the desired total in sum with any other number then the first player looses anyway.
If the sum of all number in the pool cannot exceed or reach the desired total, then no one can win.
Now, for the other cases we can use MiniMax logic to reveal the winner. Because both player play optimally, 
In order to win, the first player has to make a choice, that leaves the second player no chance to win. 
Thus, at each step we consider all the possible choices by the current player and give turn to the second player recursively. 
If we find a move, after which the second player looses anyway or we have already exceed the desired total by adding the chosen number, 
we return true, i.e the current player wins. This way the game looks like the following tree:

     player1 ->  0
              /| ...\
  player2 -> 1 2 ....max 
            /|\ ..../ | \
player1 -> 2 3...  1  2 ..max-1
           ...                \
player1 ->   /      |     \   loose
player2 -> loose   win   loose
The figure above helps to imagine how the algorithm considers all possible scenarios of the game. 
The leafs of the game tree are loose or win states for one of the players. Finally the logic concludes to the idea, 
that if some branch does not contain any leaf that ends with win state for player2, 
the move associated with that branch is the optimal one for the first player.

P.S: Time complexity of naive implementation will work for O(n!). Therefore we have to memorize branch states after traversing once.
     */
    public boolean canIWin(int max, int target) {
    	Map<String, Boolean> map = new HashMap<String, Boolean>();
    	boolean[] visited = new boolean[max + 1];
    	
    	return helper(map, visited, target);
    }
    
    boolean helper(Map<String, Boolean> map, boolean[] visited, int target) {
    	String curStr = Arrays.toString(visited);
    	
    	if (map.containsKey(curStr)) {
    		return map.get(curStr);
    	}
    	
    	// Try all possible options for current player
    	for (int i = 1; i < visited.length; i++) {
    		if (!visited[i]) {
    			visited[i] = true;
    			// If Opponent will loose eventually
    			if (i >= target || !helper(map, visited, target - i)) {
    				visited[i] = false; // Change back, because will RETURN later, shortly 现在改了，否则一会儿return了就没机会改了
    				map.put(curStr, true);
    				return true;
    			}
    			// Even though can't win, change back
    			visited[i] = false;
    		}
    	}
    	
    	map.put(curStr, false);
    	return false;
    }
}

