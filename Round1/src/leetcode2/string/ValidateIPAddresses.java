package leetcode2.string;
/**
 * 468. In this problem, your job to write a function to check whether a input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, you need to keep in mind that leading zeros in the IPv4 is illegal. For example, the address 172.16.254.01 is illegal.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a legal one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, you need to keep in mind that extra leading zeros in the IPv6 is also illegal. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is also illegal.

Note: You could assume there is no extra space in the test cases and there may some special characters in the input string.

Example 1:
Input: "172.16.254.1"

Output: "IPv4"

Explanation: This is a valid IPv4 address, return "IPv4".
Example 2:
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

Output: "IPv6"

Explanation: This is a valid IPv6 address, return "IPv6".
Example 3:
Input: "256.256.256.256"

Output: "Neither"

Explanation: This is neither a IPv4 address nor a IPv6 address.
 */
public class ValidateIPAddresses {
    public String validIPAddress(String IP) {
        String[] v4 = IP.split("\\.");
        String[] v6 = IP.split(":");
        
        int v4Count = IP.length() - IP.replace(".", "").length();
        int v6Count = IP.length() - IP.replace(":", "").length();
        
        if (v4.length == 4 && v4Count == 3) { // Try V4. v4Count is to avoid 1.1.1.1.
            for (int i = 0; i < 4; i++) {
                String v = v4[i];
                if (!validDecSection(v)) {
                    return "Neither";
                }
            }
            
            return "IPv4";
        } else if (v6.length == 8 && v6Count == 7) { // Try V6
            for (int i = 0; i < 8; i++) {
                String v = v6[i];
                
                if (!validHexSection(v)) {
                    return "Neither";
                }
                
            }
            return "IPv6";
        }
        
        return "Neither"; 
    }
    
    boolean validHexChar(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }
    
    boolean validDecSection(String v) {
                if (v.length() > 3 || v.length() == 0) {
                    return false;
                }
                
                if (v.charAt(0) == '0' && v.length() != 1) {
                    return false;
                }
                
                if (v.charAt(0) == '-') {
                    return false;
                }
                
                int val = 0;
                try {
                    val = Integer.parseInt(v);
                } catch (NumberFormatException ex) {
                    return false;
                }
                
                if (val > 255) {
                    return false;
                } 
                
                return true;
    }
    boolean validHexSection(String str) {
        if (str.length() == 0 || str.length() > 4) {
            return false;
        }
        
        boolean nonZeroExist = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!validHexChar(c)) {
                return false;
            }
            
            // Both are valid, so no need to check this.
            // if (c == '0') {
            //     if (!nonZeroExist && i != 0) { // 00ab is false. 0abc is true. 0 is true. ab00 is true. a00b is true
            //         return false;
            //     }
            // } else {
            //     nonZeroExist = true;
            // }
        }
        
        return true;
    }
}