package company.facebook;
/**
 * 214. Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. 
 * Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".

三指针方法不错
 */
public class ShortestPalindrome {
    // Using 3 pointers to find the longest palindrome starting from index 0
    public String shortestPalindrome3Pointers(String s) {
        int i = 0;
        int j = s.length() - 1;
        int end = s.length() - 1;
        
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                i = 0;
                end--;
                j = end;
            }
        }
        
        return new StringBuilder(s.substring(end + 1)).reverse().append(s).toString();
    }
    
    /**
     * Reference: https://discuss.leetcode.com/topic/27261/clean-kmp-solution-with-super-detailed-explanation
     * https://www.youtube.com/watch?v=KG44VoDtsAA
     */
    public String shortestPalindrome(String s) {
        String concat = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = getTable(concat);
        String res = new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
        return res;
    }
    
    int[] getTable(String s) {
        // Look up table
        int[] table = new int[s.length()];
        
        int j = 0; // This is the index in the front
        
        // This is the index afterward
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(j)) {
                // Best scenario when the two equals
                table[i] = table[i - 1] + 1;
                j++;
            } else {
                j = table[i - 1];
                
                while (j > 0 && s.charAt(j) != s.charAt(i)) {
                    j = table[j - 1];
                }
                
                // Compare the last chance
                if (s.charAt(j) == s.charAt(i) ) {
                    j++;
                } // If not equal, then it will be 0
                
                table[i] = j; // j increased already
            }
        }
        
        return table;
    }
}