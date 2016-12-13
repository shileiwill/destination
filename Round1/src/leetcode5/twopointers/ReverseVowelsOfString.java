package leetcode5.twopointers;
/**
 * 345. Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".
 */
public class ReverseVowelsOfString {
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            if (isVowel(arr[left]) && isVowel(arr[right])) {
                swap(arr, left, right);
                right--;
                left++;
            } else if (isVowel(arr[left])) { // Left is, move right
                right--;
            } else if (isVowel(arr[right])) { // Left is, move right
                left++;
            } else {
                right--;
                left++;
            }
        }
        
        return new String(arr);
    }
    
    void swap(char[] arr, int left, int right) {
        char c = arr[left];
        arr[left] = arr[right];
        arr[right] = c;
    }
    boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }
        return false;
    }
}
