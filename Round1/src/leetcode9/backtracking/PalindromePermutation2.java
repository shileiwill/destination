package leetcode9.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 267. Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

Hint:

If a palindromic permutation exists, we just need to generate the first half of the string.
To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.
 */
public class PalindromePermutation2 {

    public List<String> generatePalindromes(String s) {
        List<StringBuilder> list = new ArrayList<StringBuilder>();
        Set<String> res = new HashSet<String>(); // To remove duplicate, like aaaa
        
        String half = getHalfString(s);
        if (half == null) {
            return new ArrayList<String>(res);
        }
        
        boolean[] used = new boolean[half.length()];
        // Add all permutation to list
        helper(list, half, 0, new StringBuilder(), used);
        
        for (StringBuilder sb : list) {
            String reversedStr = sb.reverse().toString();
            sb = sb.reverse(); // Be careful here, reverse() will reverse the initial StringBuilder as well
            
            if (independent != '*') {
                sb = sb.append(independent).append(reversedStr);
            } else {
                sb = sb.append(reversedStr);
            }
            res.add(sb.toString());
        }
        
        return new ArrayList<String>(res);
    }
    
    void helper(List<StringBuilder> list, String half, int pos, StringBuilder cur, boolean[] used) {
        if (cur.length() == half.length()) {
            list.add(new StringBuilder(cur));
            return;
        }
        
        for (int i = 0; i < half.length(); i++) { // Always from 0
        	if (used[i]) {
        		continue;
        	}
            cur.append(half.charAt(i));
            used[i] = true;
            helper(list, half, i + 1, cur, used);
            cur.setLength(cur.length() - 1);
            used[i] = false;
        }
    }
    
    char independent = '*';
    public String getHalfString(String s) {
        char[] arr = s.toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        StringBuilder sb = new StringBuilder();
        
        int single = 0;
        
        for (char c : arr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (char c : map.keySet()) {
            int v = map.get(c);
            
            int remainder = v % 2;
            
            if (remainder == 1) {
                independent = c;
                single++;
            }
            
            if (single >= 2) {
                return null;
            }
            
            for (int i = 0; i < v / 2; i++) {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}
