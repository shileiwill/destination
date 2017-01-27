package leetcode19.bitmanipulation;
/**
 * 318. Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.
 */
public class MaximumProductOfWordLengths {

    public int maxProduct(String[] words) {
        int len = words.length;
        int[] arr = new int[len];
        
        for (int i = 0; i < len; i++) {
            String word = words[i];
            for (char c : word.toCharArray()) {
                arr[i] |= (1 << (c - 'a')); // Maximum there are 26 letters
            }
        }
        
        int maxProduct = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (((arr[i] & arr[j]) == 0) && (words[i].length() * words[j].length() > maxProduct)) {
                    maxProduct = words[i].length() * words[j].length();
                }
            }
        }
        
        return maxProduct;
    }

}
