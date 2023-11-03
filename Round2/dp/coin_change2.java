coin_change2.java

class Solution {

    // Solution 1: helper has return, which is how many coins
    public int coinChange(int[] coins, int amount) {
        Map<Integer, Integer> map = new HashMap<>();
        return helper3(coins, amount, map);
    }

    // for these coins, to get target amount, how many coins needed minimum
    int helper3(int[] coins, int amount, Map<Integer, Integer> map) {
        if (amount < 0) {
            return -1; // no viable solution
        }
        if (amount == 0) {
            return 0; // just dont take anything, 0
        }
        if (map.containsKey(amount)) {
            return map.get(amount);
        }

        // use a local variable to track
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            // take this coin
            int count = helper3(coins, amount - coin, map);
            if (count >= 0) {
                min = Math.min(min, count + 1);
            }
        }

        map.put(amount, min == Integer.MAX_VALUE ? -1 : min);
        return map.get(amount);
    }

    // Solution 2: what if I want to print the solution, then I can not skip by memoization
    List<Integer> res = null;
    public int coinChange(int[] coins, int amount) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        return helper3(coins, amount, map, list);
    }

    // for these coins, to get target amount, how many coins needed minimum
    int helper3(int[] coins, int amount, Map<Integer, Integer> map, List<Integer> list) {
        if (amount < 0) {
            return -1; // no viable solution
        }
        if (amount == 0) {
            // This is change #1
            if (res == null || res.size() > list.size()) {
                res = new ArrayList<Integer>(list);
                System.out.println(list);
            }
            return 0; // just dont take anything, 0
        }

        // This is change #2
        // if (map.containsKey(amount)) {
        //     return map.get(amount);
        // }

        // use a local variable to track
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            // take this coin
            list.add(coin);
            int count = helper3(coins, amount - coin, map, list);
            
            if (count >= 0) {
                min = Math.min(min, count + 1);
            }
            list.remove(list.size() - 1);
        }

        map.put(amount, min == Integer.MAX_VALUE ? -1 : min);
        return map.get(amount);
    }

    // Solution 3: the above is essentially the combination sum problem, what if we can use a coin only once, then use position and do i + 1
    List<Integer> res = null;
    public int coinChange(int[] coins, int amount) {
        // Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        helper(coins, amount, list, 0);
        return res == null ? -1 : res.size();
    }

    // for these coins, to get target amount, how many coins needed minimum
    void helper(int[] coins, int amount, List<Integer> list, int pos) {
        if (amount < 0) {
            return;
        }
        if (amount == 0) {
            // found a solution
            if (res == null || list.size() < res.size()) {
                res = new ArrayList<>(list);
            }
            return;
        }

        for (int i = pos; i < coins.length; i++) {
            int coin = coins[i];
            list.add(coin); // take this coin
            // Do i + 1 if we can use the coin only once
            helper(coins, amount - coin, list, i); // stay with i since we can use the coin multiple times
            list.remove(list.size() - 1); // backtrack
        }
    }

    // Solution 4 (what about not using for loop in helper? use memoization, for each coin, take vs. notake
    // This is so similar to the backpack_outco problem!
    // Doesnt work at all!
    // public int change(int amount, int[] coins) {
    //     Map<String, Integer> map = new HashMap<>();
    //     return helper(coins, amount, map, 0);
    // }

    // int helper(int[] coins, int target, Map<String, Integer> map, int pos) {
    //     if (target == 0) {
    //         return 1; // means one solution
    //     }
    //     if (pos == coins.length) {
    //         return 0; 
    //     }

    //     String key = pos + ":" + target;
    //     if (map.containsKey(key)) {
    //         return map.get(key);
    //     }

    //     int take = 0;
    //     if (target - coins[pos] >= 0) {
    //         take = helper(coins, target - coins[pos], map, pos);
    //     }
    //     int notake = helper(coins, target, map, pos + 1);

    //     map.put(key, take + notake);
    //     return map.get(key);
    // }

    // Solution 5: Use BFS
    public int coinChange(int[] coins, int amount) {
        // goal is to find the min number of coins. min level to reach target, level order traversal
        if (amount == 0) {
            return 0;
        }

        // you need to sort it!!!
        Arrays.sort(coins);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        Set<Integer> visited = new HashSet<>();
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int cur = queue.poll();

                for (int coin : coins) {
                    int next = cur + coin;
                    if (next == amount) {
                        return level + 1;
                    }

                    // You need to break!!!
                    // That is why we  need to sort, bc the later numbers will only be bigger
                    if (next > amount) {
                        break;
                    }

                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            level++;
        }

        return -1;
    }
}