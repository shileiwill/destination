package leetcode4.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 336. Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
public class PalindromePairs {
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String word1 = words[i] + words[j];
                String word2 = words[j] + words[i];
                
                if (isPalindrome(word1)) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
                
                if (isPalindrome(word2)) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(j);
                    list.add(i);
                    res.add(list);
                }
            }
        }
        
        return res;
    }
    
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i); // words are guaranteed to be unique
        }
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) { // This equals sign is aimed to handle empty string, consider ["a", ""]
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                
                if (isPalindrome(str1)) {
                    String str2Reversed = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2Reversed) && map.get(str2Reversed) != i) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(map.get(str2Reversed));
                        list.add(i);
                        res.add(list);
                    }
                }
                
                if (isPalindrome(str2) && str2.length() != 0) { // != 0 is used to avoid duplicate. Consider ["abcd", "dcba"];
                    String str1Reversed = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(str1Reversed) && map.get(str1Reversed) != i) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(i);
                        list.add(map.get(str1Reversed));
                        res.add(list);
                    }
                }
            }
        }
        
        // Another way to avoid duplicates is to use Set<List<Integer>> ret = new HashSet<>(); and return new ArrayList<>(ret);
        return res;
    }
    
    boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;
        
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
}
