package leetcode4.hashtable;
/**
 * 3. Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class ValidSubstringWithoutRepeating {
    // Solution 1: what if we can use extra space, e.g. a Set
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            int cur = 0;
            set = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (set.contains(c)) {
                    break;
                }
                set.add(c);
                cur++;
            }

            count = Math.max(count, cur); 
        }

        return count;
    }

    // Solution 2: 2 pointers with bool
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        int bestLen = 0;
        boolean hasDup = false;

        // abba
        while (right < s.length()) {
            // 1. move right: hunting phase
            char rc = s.charAt(right);
            if (map.containsKey(rc) && map.get(rc) > 0) {
                hasDup = true;
            } 
            map.put(rc, map.getOrDefault(rc, 0) + 1);
            right++;

            while (hasDup) {
                // we have duplicate! need to move left, catchup phase
                char lc = s.charAt(left); // a b
                if (map.get(lc) > 1) {
                    // this is a duplicate letter
                    hasDup = false;
                }
                map.put(lc, map.get(lc) - 1); // 
                // if (map.get(lc) == 0) { // you don't need to remove if you check count in L48
                //     map.remove(lc);
                // }

                left++;
            }

            // no duplicate, we can get the length
            int curLen = right - left;
            if (curLen > bestLen) {
                bestLen = curLen;
            }
        }

        return bestLen;
    }

    // Solution 3: Use 2 pointers going from left, 1) use dup as int to track how many duplicates 2) return the substring
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        int leftRes = 0, rightRes = 0;
        int dup = 0;

        while (right < s.length()) {
            // 1. move right
            char rc = s.charAt(right);
            if (map.containsKey(rc)) {
                dup++;
            }
            map.put(rc, map.getOrDefault(rc, 0) + 1);
            right++; // remember to plus

            // 2. move left, when should we move left
            while (dup != 0) {
                char lc = s.charAt(left);
                if (map.get(lc) > 1) {
                    dup--;
                }
                map.put(lc, map.get(lc) - 1);
                if (map.get(lc) == 0) {
                    map.remove(lc); // remember to remove
                }
                left++;
            }

            // 3. compare with final result
            if (right - left > rightRes - leftRes) {
                leftRes = left;
                rightRes = right;
            }
        }

        return rightRes - leftRes;
    }
}
