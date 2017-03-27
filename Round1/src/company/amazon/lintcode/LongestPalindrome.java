package company.amazon.lintcode;
/**
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
This is case sensitive, for example "Aa" is not considered a palindrome here.
 Notice
Assume the length of given string will not exceed 1010.
Have you met this question in a real interview? 
Yes
Example
Given s = "abccccdd" return 7
One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class LongestPalindrome {
    /**
     * @param s a string which consists of lowercase or uppercase letters
     * @return the length of the longest palindromes that can be built
     */
    public int longestPalindrome(String s) {
        // Write your code here
        int[] hash = new int[256];
        for (char c : s.toCharArray()) {
            hash[c]++;
        }
       
        boolean hasSingle = false;
        int res = 0;
       
        for (int i = 0; i < 256; i++) {
            int count = hash[i];
            if (count % 2 == 1) {
                hasSingle = true;
            }
            res += (count - count % 2);
        }
       
        return hasSingle ? res + 1 : res;
    }
}