package chapter2.sortedArrays;
/**
 * Given a string and an offset, rotate string by offset. (rotate from left to right)
Example
Given "abcdefg".

offset=0 => "abcdefg"
offset=1 => "gabcdef"
offset=2 => "fgabcde"
offset=3 => "efgabcd"
 * @author Lei
 *
 */
public class RotateString {
    public void rotateString(char[] str, int offset) {
        // write your code here
        if (str == null || str.length == 0) {
            return;
        }
        // Make sure offset is less than length
        offset = offset % str.length;
        // ReCalculate offset from the beginning
        offset = (str.length - offset - 1);
        reverse(str, 0, offset); 
        reverse(str, offset + 1, str.length - 1);
        reverse(str, 0, str.length - 1);
    }
    
    private void reverse(char[] str, int start, int end) {
    	while (start < end) {
    		char temp = str[start];
    		str[start] = str[end];
    		str[end] = temp;
    		start++;
    		end--;
    	}
    }
}
