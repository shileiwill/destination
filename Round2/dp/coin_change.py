coin_change.py

322

class Solution(object):
    min_count = -1

    def coinChange(self, coins, amount):
        """
        :type coins: List[int]
        :type amount: int
        :rtype: int

        min_cost = float('inf')
        """
        memo = {}
        res = []

        def helper(target, arr, list):
        	# join works only on strings, so first convert the int array to string array
            str_arr = ','.join(map(str,arr))
            # + works for concat
            key = str(target) + "_" + str_arr


            # this doesnt work. remove this if check will pass all cases except TLE
            if key in memo:
                return memo[key]
            
            if target < 0:
                return 0
            if len(arr) == 0:
                if target == 0:
                    copy = list[::]
                    res.append(copy)

                    print(copy)
                    self.min_count = len(copy) if self.min_count == -1 else min(self.min_count, len(copy))  
                    print(self.min_count)
                    return 1
                return 0
            
            list.append(arr[-1])
            take = helper(target - arr[-1], arr, list)
            list.pop()

            last_coin = arr.pop()
            notake = helper(target, arr, list)
            arr.append(last_coin)

            memo[key] = take + notake
            return take + notake
        
        helper(amount, coins, [])

        return self.min_count
            
            
The helper function returns 0 or 1, meaning it is a possible solution or not. so, helper returns the number of potential solutions to get target.

In the leetcode question, we need to know the min count, hence we use a list to tract the coins we need in order to reach target, we use a min_count to track the min count.


// it is similar to Permutation, with repeat pick
class Solution {
    public int coinChange(int[] coins, int amount) {
        Map<Integer, Integer> map = new HashMap<>();

        return helper(coins, amount, map);
    }

    int helper(int[] coins, int remaining, Map<Integer, Integer> map) {
        if (remaining < 0) {
            return -1; // no viable solution to get this amount
        }

        if (remaining == 0) {
            return 0; // take 0 tokens, hence 0
        }

        if (map.containsKey(remaining)) {
            return map.get(remaining);
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int count = helper(coins, remaining - coin, map);
            if (count >= 0 && count + 1 < min) { // +1 because we added one more coin
                min = count + 1;
            }
        }

        if (min == Integer.MAX_VALUE) {
            map.put(remaining, -1);
        } else {
            map.put(remaining, min);
        }
        
        return map.get(remaining);
    }
}