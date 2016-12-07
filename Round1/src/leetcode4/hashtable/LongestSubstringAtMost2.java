package leetcode4.hashtable;
/**
 * 159. Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.
 */
public class LongestSubstringAtMost2 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int left = 0, right = 0, count = 0; // The count of duplicates
        int len = 0;
        int[] hash = new int[256];
        
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (hash[rightChar] == 0) { // This right character doesnt exist, a brand new one
                count++;
            }
            
            hash[rightChar]++;
            right++;
            
            while (count > 2) { // There are too many different chars, need to move left forward
                char leftChar = s.charAt(left);
                if (hash[leftChar] == 1) { // only when appears once. If it appears only once, then moving left forward will decrease the count of different chars. If it is more than once, just move forward
                    count--;
                }
                hash[leftChar]--;
                left++;
            }
            
            len = Math.max(len, right - left);
        }
        
        return len;
    }
}
