package leetcode2.string;
/**
 * 434. Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:

Input: "Hello, my name is John"
Output: 5
 */
public class CountSegments {
    public int countSegments(String s) {
        int count = 0;
        int left = 0;
        boolean solid = false;
        
        s = s + " ";
        while (left < s.length()) {
            char c = s.charAt(left);
            
            if (c == ' ') {
                if (solid) {
                    count++;
                }
                solid = false;
            } else {
                solid = true;
            }
            
            left++;
        }
        
        return count;
    }
}
