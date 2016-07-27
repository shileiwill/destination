package chapter0.pickOne;
/**
 * 14. Write a function to find the longest common prefix string amongst an array of strings.
 * @author Lei
 *
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int index = 0;
        while (true) {
            char cur;
            // Get the first one
            if (index < strs[0].length()) {
                cur = strs[0].charAt(index);
            } else {
                return strs[0].substring(0, index);
            }
            // Test the rest ones
            for (int i = 1; i < strs.length; i++) {
                String str = strs[i];
                if (index >= str.length() || cur != str.charAt(index)) {
                    return strs[0].substring(0, index);
                }
            }
            index++;
        }
    }
}
