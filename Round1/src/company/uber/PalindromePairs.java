package company.uber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 336. Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, 
 * i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

The brute force solution is pretty straightforward, just concat each pair and see if it is palindrome
 */
public class PalindromePairs {

	public static void main(String[] args) {
		PalindromePairs pp = new PalindromePairs();
		String[] words = {"bat", "tab", "cat"};
		List<List<Integer>> res = pp.palindromePairs(words);
		
		for (List<Integer> list : res) {
			for (int val : list) {
				System.out.print(val + "==");
			}
			System.out.println();
		}
		
		String s = "abc";
		System.out.println("--" + s.substring(0, 0) + "--");
	}

	// 分析一下这个的复杂度，与暴力解的比较
	List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			map.put(word, i);
		}
		
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j <= words[i].length(); j++) { // Split the word. Use equals here. could be ""
				String s1 = words[i].substring(0, j);
				String s2 = words[i].substring(j);
				
				if (isPalindrome(s1)) {
					String reversedS2 = new StringBuilder(s2).reverse().toString();
					if (map.containsKey(reversedS2) && map.get(reversedS2) != i) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(i);
						list.add(map.get(reversedS2));
						res.add(list);
					}
				}
				
				if (isPalindrome(s2) && s2.length() != 0) {
					String reversedS1 = new StringBuilder(s1).reverse().toString();
					if (map.containsKey(reversedS1) && map.get(reversedS1) != i) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(i);
						list.add(map.get(reversedS1));
						res.add(list);
					}
				}
			}
		}
		
		return res;
	}
	
	// Empty string and 1 character string will return true
	boolean isPalindrome(String S) {
		int left = 0;
		int right = S.length() - 1;
		
		while (left < right) {
			if (S.charAt(left) != S.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		
		return true;
	}
}
