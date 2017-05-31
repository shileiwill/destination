package company.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DNASequence {

	public static void main(String[] args) {
		System.out.println(Math.sqrt(16.9));
		System.out.println(Math.sqrt(169));
	}

	/**
	 * Post Java7 O(N) on substring
	 * the behaviour of substring changed to create a copy - so every String refers to a char[] which is not shared with any other object, 
	 * as far as I'm aware.
	 * So at that point, substring() became an O(n) operation.
	 * 
	 * Pre Java7 O(1) on substring
	 * It simply builds a new String object referring to the same underlying char[] but with different offset and count values. 
	 * So the cost is the time taken to perform validation and construct a single new (reasonably small) object. 
	 * That's O(1) as far as it's sensible to talk about the complexity of operations which can vary in time based on garbage collection, CPU caches etc. 
	 * In particular, it doesn't directly depend on the length of the original string or the substring.
	 */
	
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
    
    public List<String> findRepeatedDnaSequencesStringBuilder(String s) {
        List<String> res = new ArrayList<String>();
        Set<String> repeated = new HashSet<String>();
        Set<String> seen = new HashSet<String>();
        
        StringBuilder sb = new StringBuilder(s.substring(0, 10));
        seen.add(sb.toString());
        
        for (int i = 0; i < s.length(); i++) {
        	sb.deleteCharAt(0);
        	sb.append(s.charAt(i));
        	
        	if (seen.contains(sb.toString())) {
        		repeated.add(sb.toString());
        	} else {
        		seen.add(sb.toString());
        	}
        }
        
        res.addAll(repeated);
        return res;
    }
    
    // What if input is given character by character
    public List<String> findRepeatedDnaSequencesIterator(Iterator<Character> it) {
        List<String> res = new ArrayList<String>();
        Set<String> repeated = new HashSet<String>();
        Set<String> seen = new HashSet<String>();
        
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (count < 10 && it.hasNext()) {
        	sb.append(it.next());
        }
        
        if (sb.length() < 10) {
        	return null; // Even the first sequence is less than 10
        }
        seen.add(sb.toString());
        
        while (it.hasNext()) {
        	sb.deleteCharAt(0);
        	sb.append(it.next());
        	
        	if (seen.contains(sb.toString())) {
        		repeated.add(sb.toString());
        	} else {
        		seen.add(sb.toString());
        	}
        }
        
        res.addAll(repeated);
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
        
        for (int i = 0; i <= s.length() - 10; i++) {
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
