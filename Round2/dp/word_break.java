word_break.java

class Solution {
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }
        int N = s.length();
        boolean[] hash = new boolean[N + 1];
        hash[0] = true;

        for (int i = 1; i <= N; i++) {
            hash[i] = false;

            for (int j = 0; j < i; j++) {
                String sub = s.substring(j, i);
                if (hash[j] && set.contains(sub)) {
                    hash[i] = true;
                    break;
                }
            }
        }

        return hash[N];

    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }
        int N = s.length();
        Boolean[] memo = new Boolean[N];

        return helper(s, set, memo, 0);
    }

    boolean helper(String s, Set<String> set, Boolean[] memo, int start) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String sub = s.substring(start, end);
            if (set.contains(sub) && helper(s, set, memo, end)) {
                memo[start] = true;
                return true;
            }
        }

        memo[start] = false;
        return memo[start];
    }
}