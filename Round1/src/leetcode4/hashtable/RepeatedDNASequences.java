package leetcode4.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 187. All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
 */
public class RepeatedDNASequences {
	    public List<String> findRepeatedDnaSequences2(String s) {
	        List<String> res = new ArrayList<String>();
	        Map<String, Integer> map = new HashMap<String, Integer>();
	        
	        for (int i = 0; i <= s.length() - 10; i++) {
	            String sub = s.substring(i, i + 10);
	            
	            map.put(sub, map.getOrDefault(sub, 0) + 1);
	        }
	        
	        for (Map.Entry<String, Integer> entry : map.entrySet()) {
	            String key = entry.getKey();
	            int value = entry.getValue();
	            
	            if (value > 1) {
	                res.add(key);
	            }
	        }
	        
	        return res;
	    }
	    
	    // This will significant reduce the number of substring()
	    public List<String> findRepeatedDnaSequences(String s) {
	        Set<Integer> existingBit = new HashSet<Integer>();
	        Set<String> existingStr = new HashSet<String>();
	        
	        int[] arr = new int[26];
	        arr['A' - 'A'] = 0; //00
	        arr['C' - 'A'] = 1; //01
	        arr['G' - 'A'] = 2; //10
	        arr['T' - 'A'] = 3; //11
	        
	        for (int i = 0; i < s.length() - 9; i++) {
	            int v = 0;
	            for (int j = i; j < i + 10; j++) { // All the 10 digits
	                v <<= 2; // Move 2 digits left, the 2 digits stand for 1 letter
	                v |= arr[s.charAt(j) - 'A'];
	            }
	            if (!existingBit.add(v)) {
	                existingStr.add(s.substring(i, i + 10));
	            }
	        }
	        
	        return new ArrayList<String>(existingStr);
	    }
}
