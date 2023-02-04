combination_sum4.java

class Solution {
    Map<Integer, Integer> map = new HashMap<>();

    public int combinationSum4_2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<Integer>();

        helper(res, list, nums, target);
        return res.size();
    }

    void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int target) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int num : nums) {
            list.add(num);
            helper(res, list, nums, target - num);
            list.remove(list.size() - 1);
        }
    }

    public int combinationSum4(int[] nums, int target) {
        return helper2(nums, target);
    }

    int helper2(int[] nums, int remaining) {
        if (remaining == 0) {
            return 1; // pick nothing is one solution
        }
        if (remaining < 0) {
            return 0;
        }

        if (map.containsKey(remaining)) {
            return map.get(remaining);
        }

        int res = 0;
        for (int num : nums) {
            res += helper2(nums, remaining - num);
        }

        map.put(remaining, res);
        return res;
    }
}