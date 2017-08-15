package company.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 205. Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.
 */
public class IsomorphicStrings {

	public static void main(String[] args) {
		IsomorphicStrings isomorphic = new IsomorphicStrings();
		String[] arr = {"fff","abc","foo","haa","www","vvv"};
		
		isomorphic.findIsomorphic(arr);
		
	}

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map1 = new HashMap<Character, Character>();
        Map<Character, Character> map2 = new HashMap<Character, Character>();
        
        if (s == null || t == null) {
            return false;
        }
        
        if (s.length() != t.length()) {
            return false;
        }
        
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            
            if (map1.containsKey(c1)) {
                if (map1.get(c1) != c2) {
                    return false;
                }
            } else {
                map1.put(c1, c2);
            }
            
            if (map2.containsKey(c2)) {
                if (map2.get(c2) != c1) {
                    return false;
                }
            } else {
                map2.put(c2, c1);
            }
        }
        
        return true;
    }

    // Another follow up, Follow up: 如果是三个String 怎么做. 下边的方法可以解决
 // follow up
 // transfer word into same isomorphic pattern
 // every word start with 'a', every time meet a new letter
 // map it to cur char, and increase the value of cur char the then if same letter show up again, use the value in map
 // ex. foo -> abb
 // ex. gjk -> abc
 // ex. pkk -> abb
    public List<List<String>> findIsomorphic(String[] input) {
    	List<List<String>> res = new ArrayList<List<String>>();
    	Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    	
    	for (String str : input) {
    		String trans = transform(str);
    		
    		if (!map.containsKey(trans)) {
    			map.put(trans, new HashSet<String>());
    		}
    		
    		map.get(trans).add(str);
    	}
    	
    	for (Set<String> set : map.values()) {
    		List<String> list = new ArrayList<String>();
    		list.addAll(set);
    		res.add(list);
    		
    		for (String s : set) {
    			System.out.print(s + "==");
    		}
    		System.out.println();
    	}
    	
    	return res;
    }
    
    // The methodology is very similar to Group Anagram
    String transform(String str) {
    	Map<Character, Character> map = new HashMap<Character, Character>();
    	char now = 'a';
    	StringBuilder sb = new StringBuilder();
    	
    	for (char c : str.toCharArray()) {
    		if (!map.containsKey(c)) {
    			map.put(c, now);
    			now++;
    		}
    		
    		sb.append(map.get(c));
    	}
    	
    	return sb.toString();
    }
}
