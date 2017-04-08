package company.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
You are given a string, S, and a list of words, L, that are all of the same length.

Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.

Example :

S: "barfoothefoobarman"
L: ["foo", "bar"]
You should return the indices: [0,9].
(order does not matter).
 */
public class SubstringConcatenation {

	public static void main(String[] args) {

	}

	public ArrayList<Integer> findSubstringHashMapBetter(String a, final List<String> b) {
	    
	    ArrayList<Integer> res = new ArrayList<Integer>();
	    
	    if (a.length() < b.size() * b.get(0).length()) {
	        return res;
	    }
	    
	    int len = b.get(0).length();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String s : b) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        
        for (int i = 0; i <= a.length() - b.size() * len; i++) {
            Map<String, Integer> dup = new HashMap<String, Integer>(map);
            
            for (int j = i; j < i + b.size() * len; j += len) {
                String sub = a.substring(j, j + len);
                
                if (!dup.containsKey(sub)) {
                    break;
                } else {
                    dup.put(sub, dup.get(sub) - 1);
                    if (dup.get(sub) == 0) {
                        dup.remove(sub);
                    }
                }
            }
            
            if (dup.isEmpty()) {
                res.add(i);
            }
        }
	    
	    return res;
	}

	public ArrayList<Integer> findSubstring(String a, final List<String> b) {
	    
	    ArrayList<Integer> res = new ArrayList<Integer>();
	    
	    if (a.length() < b.size() * b.get(0).length()) {
	        return res;
	    }
	    
	    Set<String> dict = getAllCombinations(b);
	    for (int i = 0; i <= a.length() - b.size() * b.get(0).length(); i++) {
	        String sub = a.substring(i, i + b.size() * b.get(0).length());
	        if (dict.contains(sub)) {
	            res.add(i);
	        }
	    }
	    
	    return res;
	}
	
	Set<String> getAllCombinations(List<String> b) {
	    Set<String> set = new HashSet<String>();
	    Set<Integer> visited = new HashSet<Integer>();
	    
	    helper(set, visited, b, "");
	    return set;
	}
	
	void helper(Set<String> res, Set<Integer> visited, List<String> b, String s) {
	    if (s.length() == b.size() * b.get(0).length()) {
	        res.add(s);
	        return;
	    }
	    
	    if (s.length() > b.size() * b.get(0).length()) {
	        return;
	    }
	    
	    for (int i = 0; i < b.size(); i++) {
	        if (visited.contains(i)) {
	            continue;
	        }
	        visited.add(i);
	        helper(res, visited, b, s + b.get(i));
	        visited.remove(i);
	    }
	}

}
