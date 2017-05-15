package company.linkedin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestPalindrome {

	public static void main(String[] args) {
		LongestPalindrome lp = new LongestPalindrome();
		String s = "cbcdsafasdfwgsfgfdjyrsadfasdfadfADFADGHFDUADFGSDFDASDFAFsdfsadsadfasdfbc";
//		String res = lp.longestPalindromeSubstring(s);
//		System.out.println(res);
		
		int res = lp.longestPalindromeSubseq2(s);
		System.out.println(res);
//		for (String str : res) {
//			System.out.println(str);
//		}
	}


    public String longestPalindromeSubstring(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        
        int longest = 1;
        String res = s.substring(0, 1);
        
        for (int i = 1; i < s.length(); i++) {
            String now = extend(s, i, i);
            if (s.charAt(i) == s.charAt(i - 1)) {
                String second = extend(s, i - 1, i);
                if (second.length() > now.length()) {
                    now = second;
                }
            }
            
            if (now.length() > longest) {
                res = now;
                longest = now.length();
            }
        }
        
        return res;
    }
    
    String extend(String s, int left, int right) {
        left--;
        right++;
        while (left >= 0 && right < s.length()) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            
            if (l == r) {
                left--;
                right++;
            } else {
                break;
            }
        }
        
        return s.substring(left + 1, right);
    }

    // Longest Palindrome Sequence. Is the hash[][] array useful at all?
    public int longestPalindromeSubseq2(String s) {
    	Integer[][] hash = new Integer[s.length()][s.length()];
    	return helper(s, hash, 0, s.length() - 1);
    }
    
    int helper(String s, Integer[][] hash, int left, int right) {
    	if (left > right) {
    		return 0;
    	}
    	
    	if (left == right) {
    		hash[left][right] = 1;
    		return 1;
    	}
    	
    	if (hash[left][right] != null) {
    		System.out.println("Does it ever come here? YES, it will come!!!");
    		return hash[left][right];
    	}
    	
    	if (s.charAt(left) == s.charAt(right)) {
    		hash[left][right] = helper(s, hash, left + 1, right - 1) + 2; // 往里走
    	} else {
    		hash[left][right] = Math.max(helper(s, hash, left + 1, right),  helper(s, hash, left, right - 1));
    	}
    	
    	return hash[left][right];
    }
    
    // find all palidrome string by deleting any letter from the given string. 这题比较难，我只做了dfs的bf解, 
    // Doesnt work
    Map<Integer, Set<String>> map = new HashMap();
    Set<String> getAllPalindrome(String s, int left, int right){
    	int id = left * s.length() + right;
    	
    	if (map.containsKey(id)) {
    		return map.get(id);
    	}
    	
    	Set<String> res = new HashSet<String>();
    	if (left == right) { // itself
    		res.add(s);
    		return res;
    	}
    	
    	if (left > right) {
    		return res;
    	}
    	
		if (s.charAt(left) == s.charAt(right)) {
			Set<String> next = getAllPalindrome(s, left + 1, right - 1);
			res.addAll(next); // 所有的这些都算
			
			for (String sub : next) {
				res.add(s.charAt(left) + sub + s.charAt(right)); // 外加这一堆
			}
		} else {
//			Set<String> next1 = getAllPalindrome(s, left + 1, right);
//			res.addAll(next1); // 所有的这些都算
//			
//			Set<String> next2 = getAllPalindrome(s, left, right - 1);
//			res.addAll(next2); // 所有的这些都算
		}
    	
		map.put(id, res);
    	return res;
    }
}
