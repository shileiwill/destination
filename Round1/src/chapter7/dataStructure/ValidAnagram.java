package chapter7.dataStructure;
/**
 * 242. Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
import java.util.Arrays;

public class ValidAnagram {
    // Fastest algorithm would be to map each of the 26 English characters to a unique prime number. 
	// Then calculate the product of the string. By the fundamental theorem of arithmetic, 2 strings are anagrams 
	// if and only if their products are the same.
    public boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        char[] array1 = s1.replaceAll("[\\s]", "").toCharArray();
        char[] array2 = s2.replaceAll("[\\s]", "").toCharArray();
        
        Arrays.sort(array1);
        Arrays.sort(array2);
        
        return Arrays.equals(array1, array2);
    }
}
