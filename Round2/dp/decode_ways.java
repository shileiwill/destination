decode_ways.java

class Solution {
    // Solution 1: tabulation to deal with 2 scenarios
    public int numDecodings2(String s) {
        int N = s.length();
        int[] memo = new int[N + 1];

        memo[0] = 1; // Empty string is 1 solution
        memo[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= N; i++) {
            int d1 = Integer.valueOf(s.substring(i - 1, i));
            int d2 = Integer.valueOf(s.substring(i - 2, i));

            if (d1 >= 1 && d1 <= 9) {
                memo[i] += memo[i - 1];
            }
            if (d2 >= 10 && d2 <= 26) {
                memo[i] += memo[i - 2];
            }
        }

        return memo[N];
    }

    // Solution 2: memoization, the base condition order matters
    public int numDecodings(String s) {
        Integer[] memo = new Integer[s.length()];
        return helper(s, 0, memo);
    }

    int helper(String s, int pos, Integer[] memo) {
        // this one is not necessary since it should never come here, should stop on pos == s.length()
        if (pos > s.length()) {
            return 0;
        }

        // the sequence of the if statements matters
        if (pos == s.length()) {
            return 1;
        }

        if (s.charAt(pos) == '0') {
            return 0;
        }

        if (pos == s.length() - 1) { // this should be after the above '0' check
            return 1;
        }

        if (memo[pos] != null) {
            return memo[pos];
        }

        // Take itself
        int res = helper(s, pos + 1, memo);
        int next2 = Integer.valueOf(s.substring(pos, pos + 2));
        if (next2 <= 26) {
            res += helper(s, pos + 2, memo);
        }

        memo[pos] = res;
        return res;
    }

    // Solution 3, more organized?
    public int numDecodings(String s) {
        Integer[] memo = new Integer[s.length()];
        return helper(s, 0, memo);
    }

    int helper(String s, int pos, Integer[] memo) {
        if (pos == s.length()) {
            return 1;
        }

        if (memo[pos] != null) {
            return memo[pos];
        }

        int res = 0;
        for (int len = 1; len <= 2; len++) {
            if (pos + len > s.length()) {
                continue;
            }

            String sub = s.substring(pos, pos + len);

            if (sub.charAt(0) != '0' && Integer.parseInt(sub) <= 26) {
                res += helper(s, pos + len, memo);
            }
        }

        memo[pos] = res;
        return res;
    }
}