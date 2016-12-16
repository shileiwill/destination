package leetcode2.string;
/**
 * 43. Given two numbers represented as strings, return multiplication of the numbers as a string.

Note:
The numbers can be arbitrarily large and are non-negative.
Converting the input string to integer is NOT allowed.
You should NOT use internal library such as BigInteger.
 */
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        
        // Length will not exceed the sum of 2 string's length
        int[] arr = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int res = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                
                int p1 = i + j;
                int p2 = i + j + 1;
                
                res += arr[p2];
                // 注意方向
                arr[p1] += res / 10;
                arr[p2] = res % 10; // 没有+
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int val : arr) {
            if (sb.length() == 0 && val == 0) { // Leading zeros are not allowed
                continue;
            }
            sb.append(val);
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
