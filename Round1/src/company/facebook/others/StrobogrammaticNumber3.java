package company.facebook.others;
/**
 * 248. A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
 */
public class StrobogrammaticNumber3 {

    // Can get the length of low and high, then apply Strobogrammatic Number2, then filter out the values in between low and high
    
    int count = 0;
    char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
    public int strobogrammaticInRange(String low, String high) {
        for (int len = low.length(); len <= high.length(); len++) {
            char[] arr = new char[len];
            helper(low, high, arr, 0, len - 1);
        }
        return count;
    }
    
    void helper(String low, String high, char[] arr, int left, int right) {
        if (left > right) {
            String s = new String(arr);
            if ((s.length() == low.length() && s.compareTo(low) < 0) || (s.length() == high.length() && s.compareTo(high) > 0)) {
                return;
            }
            count++;
            return;
        }
        
        // From out to inner
        for (char[] pair : pairs) {
            arr[left] = pair[0];
            arr[right] = pair[1];
            
            if (left == right && pair[0] != pair[1]) {
                continue;
            }
            
            if (arr.length != 1 && arr[0] == '0') {//因为是从外往里，所以第一个char不能是0. 重新赋值
                continue;
            }
            
            helper(low, high, arr, left + 1, right - 1);
        }
    }

}
