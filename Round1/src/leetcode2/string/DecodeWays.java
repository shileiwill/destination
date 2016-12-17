package leetcode2.string;
/**
 * 91. A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int[] hash = new int[s.length() + 1];
        hash[0] = 1;
        hash[1] = s.charAt(0) == '0' ? 0 : 1;
        
        for (int i = 2; i <= s.length(); i++) {
            int oneDigit = Integer.valueOf(s.substring(i - 1, i));
            int twoDigits = Integer.valueOf(s.substring(i - 2, i));
            // 这两种可能都是直接累加的
            if (oneDigit >= 1 && oneDigit <= 9) { // oneDigit != 0 也可
                hash[i] += hash[i - 1];
            }
            
            if (twoDigits >= 10 && twoDigits <= 26) { // 排除了前边一位是0的可能
                hash[i] += hash[i - 2];
            }
        }
        
        return hash[s.length()];
    }
}
