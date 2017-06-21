package company.facebook;
/**
 * 340. Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.
 */
public class LongestSubstringAtMostK {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
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
            
            while (count > k) { // There are too many different chars, need to move left forward
                char leftChar = s.charAt(left);
                if (hash[leftChar] == 1) { // only when appears once. If it appears only once, then moving left forward will decrease the count of different chars. If it is more than once, just move forward
                    count--;
                }
                hash[leftChar]--;
                left++;
            }
            
            len = Math.max(len, right - left); // Here is guaranteed to be at most k distinct
        }
        
        return len;
    }
}
