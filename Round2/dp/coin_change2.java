coin_change2.java

class Solution {
    public int change(int amount, int[] coins) {
        Map<String, Integer> map = new HashMap<>();
        return helper(coins, amount, map, 0);
    }

    int helper(int[] coins, int target, Map<String, Integer> map, int pos) {
        if (target == 0) {
            return 1; // means one solution
        }
        if (pos == coins.length) {
            return 0; 
        }

        String key = pos + ":" + target;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int take = 0;
        if (target - coins[pos] >= 0) {
            take = helper(coins, target - coins[pos], map, pos);
        }
        int notake = helper(coins, target, map, pos + 1);

        map.put(key, take + notake);
        return map.get(key);
    }
}