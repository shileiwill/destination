longest_common_subsequence.java

/**
 * 1143. Longest Common Subsequence
 * 
 * /
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int M = text1.length();
        int N = text2.length();

        int[][] arr = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = -1;
            }
        }

        return helper(text1, 0, text2, 0, arr);
    }

    int helper(String str1, int pos1, String str2, int pos2, int[][] arr) {
        if (pos1 >= str1.length() || pos2 >= str2.length()) {
            // out of bound, not viable, length is 0
            return 0;
        }

        if (arr[pos1][pos2] != -1) {
            return arr[pos1][pos2];
        }

        // Option 1, dont pick pos1 from str1. 
        int opt1 = helper(str1, pos1 + 1, str2, pos2, arr);

        // Option 2, pick pos1 from str1, and find a match in str2
        int opt2 = 0;
        char c1 = str1.charAt(pos1);
        int firstOccurence = str2.indexOf(c1, pos2);
        if (firstOccurence == -1) {
            // this char doesnt exist in str2 after pos2
            opt2 = 0;
        } else {
            // found it 
            opt2 = 1 + helper(str1, pos1 + 1, str2, firstOccurence + 1, arr);
        }

        arr[pos1][pos2] = Math.max(opt1, opt2);

        return arr[pos1][pos2];
    }
}

// Ari's solution using 2 pointers doesnt work
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int M = text1.length();
        int N = text2.length();
        if (N > M) {
            return longestCommonSubsequence(text2, text1);
        }

        Map<Character, Queue<Integer>> map = new HashMap<>();
        for (int i = 0; i < text2.length(); i++) {
            char c = text2.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, new LinkedList<Integer>());
            }
            map.get(c).offer(i);
        }

        for (int i = 0, j = 0; i < text1.length(); i++) {
        char c1 = text1.charAt(i);
        if (map.get(c1) != null && !map.get(c1).isEmpty()) {
            int indexOfC1InText2 = map.get(c1).peek();
            if (indexOfC1InText2 >= j) {
                count++;
                j = indexOfC1InText2 + 1;
                map.get(c1).poll();
            }
        }
    }

        return count;
    }
}