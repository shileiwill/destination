jump_game.java

class Solution {
    public boolean canJump(int[] nums) {
        if (nums[0] == 0) {
            return false;
        }

        int N = nums.length;
        boolean[] memo = new boolean[N];
        memo[0] = true;

        for (int i = 0; i < N; i++) {
            if (!memo[i]) {
                continue;
            }
            for (int step = 1; step <= nums[i]; step++) {
                if (i + step < N) {
                    memo[i + step] = true;
                }
            }
        }

        return memo[N - 1];
    }
}