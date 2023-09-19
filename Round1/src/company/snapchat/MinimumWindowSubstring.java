package company.snapchat;
//重要
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 76. Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

这题我每次碰到更小的都更新返回值，这样每次都可能调用substring, 造成计算量和内存使用空间增加，应该保存起始和结束坐标，最后求一次substring最好，大家要注意

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=474012
input output都是word 不是letter
 */
public class MinimumWindowSubstring {

    // Solution 1: 2 pointers with hashmap
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }

        int left = 0, right = 0;
        int len = Integer.MAX_VALUE;
        String res = "";
        int count = t.length();

        while (right < s.length()) {
            // 1. move right
            char rc = s.charAt(right);
            if (map.containsKey(rc) && map.get(rc) > 0) {
                count--;
            }
            map.put(rc, map.getOrDefault(rc, 0) - 1);
            right++;

            // 2. move left, when should we move left
            while (count == 0) {
                // 3. result
                if (right - left < len) {
                    len = right - left;
                    res = s.substring(left, right);
                }

                char lc = s.charAt(left);
                if (map.get(lc) >= 0) {
                    count++;
                }
                map.put(lc, map.get(lc) + 1);
                left++;
            }
        }

        return res;
    }

    public String minWindow(String s, String t) {
        int[] hash = new int[256];
        
        int count = t.length();
        for (char c : t.toCharArray()) {
            hash[c]++;
        }
        
        int left = 0, right = 0;
        int len = Integer.MAX_VALUE;
        String res = "";
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (hash[rightChar] > 0) {
                count--;
            }
            hash[rightChar]--;
            right++; // Right is added here, so not included
            
            while (count == 0) {
                String newStr = s.substring(left, right);
                int newLen = right - left;
                
                if (newLen < len) {
                    len = newLen;
                    res = newStr;
                }
                
                char leftChar = s.charAt(left);
                if (hash[leftChar] >= 0) {
                    count++;
                }
                hash[leftChar]++;
                left++;
            }
        }
        
        return res;
    }
    
    /**
     * 438. Find all anagrams in a string
     * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

		Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
		
		The order of output does not matter.
		
		Example 1:
		
		Input:
		s: "cbaebabacd" p: "abc"
		
		Output:
		[0, 6]
		
		Explanation:
		The substring with start index = 0 is "cba", which is an anagram of "abc".
		The substring with start index = 6 is "bac", which is an anagram of "abc".
		
		给两个字符串A和B, 返回B里面出现的所有的A的permutation. 1point3acres
		A = “abc”
		B = “abcfecabaqebca”
		output: [“abc”, “cab”, “bca”]
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        
        if (p.length() > s.length()) {
            return res;
        }
        
        int m = s.length();
        int n = p.length();
        int[] hash = new int[256];
        int count = n;
        
        for (int i = 0; i < n; i++) {
            hash[p.charAt(i)]++;
        }
        
        int left = 0, right = 0;
        while (right < m) {
            char now = s.charAt(right);
            
            if (hash[now] > 0) {
                count--;
            }
            hash[now]--;
            right++;
            
            if (count == 0) {
                res.add(left);
            }
            
            if (right - left == n) { // 一个if就够了 不能再长了
                char leftChar = s.charAt(left);
                if (hash[leftChar] >= 0) {
                    count++;
                }
                
                hash[leftChar]++;
                left++;
            }
        }
        
        return res;
    }
    
    
    /**
     * No duplicates uses set 
     * Duplicate use hash table 数出现的次数
     */
    public String minWindowNoDuplicate(String s, Set<Character> set) {
        int[] hash = new int[256];
        
        int count = set.size();
        for (char c : set) {
            hash[c]++;
        }
        
        int left = 0, right = 0;
        int len = Integer.MAX_VALUE;
        String res = "";
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (hash[rightChar] > 0) {
                count--;
            }
            hash[rightChar]--;
            right++; // Right is added here, so not included
            
            while (count == 0) {
                String newStr = s.substring(left, right);
                int newLen = right - left;
                
                if (newLen < len) {
                    len = newLen;
                    res = newStr;
                }
                
                char leftChar = s.charAt(left);
                if (hash[leftChar] >= 0) {
                    count++;
                }
                hash[leftChar]++;
                left++;
            }
        }
        
        return res;
    }
}
