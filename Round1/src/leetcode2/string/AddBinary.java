package leetcode2.string;
/**
 * 67. Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int p1 = a.length() - 1;
        int p2 = b.length() - 1;
        
        int carry = 0;
        while (p1 >= 0 && p2 >= 0) {
            int c1 = a.charAt(p1) - '0';
            int c2 = b.charAt(p2) - '0';
            
            int curSum = c1 + c2 + carry;
            carry = curSum / 2;
            int curDigit = curSum % 2;
            
            sb.append(curDigit);
            
            p1--;
            p2--;
        }
        
        while (p1 >= 0) {
            int c1 = a.charAt(p1) - '0';
            
            int curSum = c1 + carry;
            carry = curSum / 2;
            int curDigit = curSum % 2;
            
            sb.append(curDigit);
            
            p1--;
        }
        
        while (p2 >= 0) {
            int c2 = b.charAt(p2) - '0';
            
            int curSum = c2 + carry;
            carry = curSum / 2;
            int curDigit = curSum % 2;
            
            sb.append(curDigit);
            
            p2--;
        }
        
        if (carry != 0) {
            sb.append(carry);
        }
        // 记得反过来
        return sb.reverse().toString();
    }
}
