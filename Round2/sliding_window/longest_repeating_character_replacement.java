longest_repeating_character_replacement.java

class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int majorCount = 0;
        int res = 0;

        while (right < s.length()) {
            char rightc = s.charAt(right);
            if (!map.containsKey(rightc)) {
                map.put(rightc, 0);
            }
            map.put(rightc, map.get(rightc) + 1);

            if (map.get(rightc) > majorCount) {
                majorCount = map.get(rightc);
            }

            while (right - left + 1 - majorCount > k) {
                char leftc = s.charAt(left);
                map.put(leftc, map.get(leftc) - 1);
                left++;
            }

            // now the non-majority letters are equal to k, we can just replace them all.
            res = Math.max(res, right - left + 1);
            right++;
        }

        return res;
    }
}