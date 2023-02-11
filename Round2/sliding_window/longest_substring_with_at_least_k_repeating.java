longest_substring_with_at_least_k_repeating.java

class Solution {
    public int longestSubstring(String s, int k) {
        
        int N = s.length();
        int len = -1;
        int allUnique = countUnique(s);

        for (int unique = 1; unique <= allUnique; unique++) {
            Map<Character, Integer> map = new HashMap<>();
            int left = 0, right = 0;
            int greaterK = 0;
            int curUni = 0;

            while (right < N) {
                // hunt
                if (curUni <= unique) {
                    char rc = s.charAt(right);
                    if (!map.containsKey(rc)) {
                        curUni++;
                    }
                    map.put(rc, map.getOrDefault(rc, 0) + 1);
                    if (map.get(rc) == k) {
                        greaterK++;
                    }
                    right++;
                } else {
                    // catch up, shrink
                    char lc = s.charAt(left);
                    map.put(lc, map.get(lc) - 1);
                    if (map.get(lc) == k - 1) {
                        greaterK--;
                    }

                    if (map.get(lc) == 0) {
                        curUni--;
                        map.remove(lc);
                    }
                    left++;
                }

                if (curUni == unique && curUni == greaterK) {
                    len = Math.max(right - left, len);
                }
            }

        }

        return len == -1 ? 0 : len;
    }


    int countUnique(String s) {
        int countUnique = 0;
        Map<Character, Integer> map = new HashMap<>();
        
        for (Character c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                countUnique++;
            } 
            map.put(c, map.getOrDefault(c, 0) + 1);

        }

        return countUnique;
    }

    public int longestSubstring2(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int N = s.length();
        int len = -1;

        // int countUniqueLetters = countUnique(s);
        for (int start = 0; start < N; start++) {
            for (int end = start + 1; end <= N; end++) {
                String sub = s.substring(start, end);
                boolean isGood = count(sub, k);
                if (isGood) {
                    len = Math.max(len, end - start);
                }

            }
        }

        return len == -1 ? 0 : len;
    }

    boolean count(String s, int k) {
        int countUnique = 0;
        int countGreaterK = 0;
        Map<Character, Integer> map = new HashMap<>();
        
        for (Character c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                countUnique++;
            } 
            map.put(c, map.getOrDefault(c, 0) + 1);

            if (map.get(c) == k) {
                countGreaterK++;
            }

        }

        return countUnique == countGreaterK;
    }
}