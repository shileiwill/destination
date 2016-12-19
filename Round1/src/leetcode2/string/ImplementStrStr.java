package leetcode2.string;
/**
 * 28. Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
public class ImplementStrStr {
    // KMP: https://www.youtube.com/watch?v=GTJr8OvyEVQ
    // Doesn't work
    public int strStr2(String haystack, String needle) {
        if(needle.equals("")) return 0;
	    if(haystack.equals("")) return -1;
	    
        String text = haystack;
        String pattern = needle;
        int textLen = haystack.length();
        int patternLen = needle.length();
        
        int[] table = getTable(pattern);
        int i = 0, j = 0;
        
        while (i < textLen && j < patternLen) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (j == 0) {
                    break;
                }
                j = table[j];
            }
        }
        
        if (j == patternLen) {
            return i - patternLen;
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
    
    //calculate KMP array
    public int[] getNext(String needle) {
    	int[] next = new int[needle.length()];
    	next[0] = 0;
     
    	for (int i = 1; i < needle.length(); i++) {
    		int index = next[i - 1];
    		while (index > 0 && needle.charAt(index) != needle.charAt(i)) {
    			index = next[index - 1];
    		}
     
    		if (needle.charAt(index) == needle.charAt(i)) {
    			next[i] = next[i - 1] + 1;
    		} else {
    			next[i] = 0;
    		}
    	}
	    return next;
    }
    
    public int strStr(String haystack, String needle) {
        if(haystack==null || needle==null)    
            return 0;
 
    	int h = haystack.length();
    	int n = needle.length();
     
    	if (n > h)
    		return -1;
    	if (n == 0)
    		return 0;
     
    	int[] next = getTable(needle);
    	int i = 0;
     
    	while (i <= h - n) {
    		int success = 1;
    		for (int j = 0; j < n; j++) {
    			if (needle.charAt(0) != haystack.charAt(i)) { // Not equal at the first char, move haystack forward by 1 step
    				success = 0;
    				i++;
    				break;
    			} else if (needle.charAt(j) != haystack.charAt(i + j)) { // There is some match, take advantage of lookup table
    				success = 0;
    				i = i + j - next[j - 1]; // Next start point
    				break;
    			}
    		}
    		if (success == 1)
    			return i;
    	}
     
    	return -1;
    }
}