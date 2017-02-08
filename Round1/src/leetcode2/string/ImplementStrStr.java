package leetcode2.string;
/**
 * 28. Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
public class ImplementStrStr {
    // KMP: https://www.youtube.com/watch?v=GTJr8OvyEVQ
    // Dude, see this one for brute force  
	// Reference : https://github.com/mission-peace/interview/blob/master/src/com/interview/string/SubstringSearch.java
    public int strStrBruteForce(String haystack, String needle) {
        if(needle.length() == 0){
            return 0;
        }
        
        int i = 0;
        int j = 0;
        int k = 0; // Potential result
        int m = haystack.length();
        int n = needle.length();
        
        while (i < m && j < n) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = 0;
                k++;
                i = k; // i move 1 step further
            }
        }
        
        if (j == n) {
            return k;
        }
        return -1;
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
    
    // k是前缀中相同部分的末尾，同时也是相同部分的长度，因为长度等于k-0。
    // j是后缀的末尾，即后缀相同部分的末尾 
    /**
     * Compute temporary array to maintain size of suffix which is same as prefix
     * Time/space complexity is O(size of pattern)
     */
    private int[] computeTemporaryArray(char[] pattern) {
        int[] lps = new int[pattern.length];
        int index =0;
        for(int i=1; i < pattern.length;){ // 没有主动i++
            if(pattern[i] == pattern[index]){
                lps[i] = index + 1;
                index++;
                i++;
            }else{
                if(index != 0){
                    index = lps[index-1]; // 没有set数组
                }else{
                    lps[i] =0;
                    i++;
                }
            }
        }
        return lps;
    }
    
    /**
     * KMP algorithm of pattern matching.
     */
    public boolean KMP(char[] text, char[] pattern){
        
        int lps[] = computeTemporaryArray(pattern);
        int i=0;
        int j=0;
        while(i < text.length && j < pattern.length){
            if(text[i] == pattern[j]){
                i++;
                j++;
            }else{
                if(j!=0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
        }
        if(j == pattern.length){
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
    	ImplementStrStr iss = new ImplementStrStr();
		String haystack = "aabaabaaa";
		String needle = "aabaabaaa";
		int res = iss.strStrBruteForce(haystack, needle);
		System.out.println(res);
	}
}