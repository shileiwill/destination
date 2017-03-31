package company.yahoo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 486. Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

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
 */
public class PredictTheWinner {

    //The dp[i][j] saves how much more scores that the first-in-action player will get from i to j than the second player
    public boolean PredictTheWinner(int[] nums) {
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
    				visited[i] = false; // Change back, because will RETURN later, shortly 现在改了，一会儿return了就没机会改了
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
