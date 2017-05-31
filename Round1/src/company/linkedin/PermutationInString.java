package company.linkedin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 567. Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. 
 * In other words, one of the first string's permutations is the substring of the second string.

Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False
Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].
 */
public class PermutationInString {

	public static void main(String[] args) {
		String s = "baa";
		System.out.println(new PermutationInString().checkInclusionMyStyle(s, "eidbaooo"));
	}

	// Better
	public boolean checkInclusionMyStyle(String s1, String s2) {
        int[] hash = new int[256];
        int len1 = s1.length();
        int len2 = s2.length();
        int count = len1;
        
        if (s1.length() > s2.length()) {
            return false;
        }
        
        for (char c1 : s1.toCharArray()) {
            hash[c1]++;
        }
        
        int left = 0, right = 0;
        while (right < len2) {
            char rightChar = s2.charAt(right);
            if (hash[rightChar] > 0) {
                count--;
            }
            hash[rightChar]--;
            right++;
            
            if (right - left == len1) {
                if (count == 0) {
                    return true;
                }
                
                char leftChar = s2.charAt(left);
                if (hash[leftChar] >= 0) {
                    count++;
                }
                hash[leftChar]++;
                left++;
            }
        }
        
        return false;
    }
	
    public boolean checkInclusion(String s1, String s2) {
        int[] hash = new int[256];
        int len1 = s1.length();
        int len2 = s2.length();
        int count = len1;
        
        if (s1.length() > s2.length()) {
            return false;
        }
        
        for (char c1 : s1.toCharArray()) {
            hash[c1]++;
        }
        
        int left = 0, right = 0;
        while (right < len2) {
            while (right - left < len1) {
                char rightChar = s2.charAt(right);
                if (hash[rightChar] > 0) {
                    count--;
                }
                hash[rightChar]--;
                right++;
            }
            
            if (count == 0) {
                return true;
            }
            
            char leftChar = s2.charAt(left);
            if (hash[leftChar] >= 0) {
                count++;
            }
            hash[leftChar]++;
            left++;
        }
        
        return false;
    }

    // How to get permutation of a string? string permutation. 如何不用额外的空间
    List<String> getPermutations(String s) {
    	char[] arr = s.toCharArray();
    	
    	List<String> res = new ArrayList<String>();
    	Set<Integer> visited = new HashSet<Integer>();
    	
    	helper(res, "", arr, visited);
    	return res;
    }


	private void helper(List<String> res, String now, char[] arr, Set<Integer> visited) {
		if (now.length() == arr.length) {
			System.out.println(now);
			res.add(now);
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (!visited.contains(i)) {
				visited.add(i);
				helper(res, now + arr[i], arr, visited);
				visited.remove(i);
			}
		}
	}
	
	// Another way, No extra space. This is from Princeton
	void getPermutations2(String s) {
		helper("", s);
	}
	
	void helper(String prefix, String str) {
		int len = str.length();
		
		if (len == 0) {
			System.out.println(prefix); // Found one
		} else {
			for (int i = 0; i < len; i++) { // Move one from str to prefix, any one is possible
				helper(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
			}
		}
	}
}
