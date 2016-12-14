package leetcode2.string;
/**
 * 8. Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 */
public class StringToInteger {
    // Doesn't work
    public int myAtoi2(String str) {
        
        if (str.length() == 0) {
            return 0;
        }
        
        StringBuilder sb = new StringBuilder();
        boolean neg = false;
        int i = 0;
        for (i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ') {
                break;
            }
        }
        
        char sig = str.charAt(i);
        if (sig == '-') {
            neg = true;
            i++;
        } else if (sig == '+') {
            neg = false;
            i++;
        } else {
            neg = false;
        }
        
        for (int start = i; start < str.length(); start++) {
            char c = str.charAt(start);
            if (c >= '0' && c <= '9') {
                sb.append(c);
            } else {
                break;
            }
        }
        
        if (sb.length() == 0) {
            return 0;
        }
        
        long l = Long.valueOf(sb.toString());
        if (!neg && l >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        System.out.println((-1L * l));
        System.out.println((-1L * l) < Integer.MIN_VALUE);
        if (neg && (-1L * l) <= Integer.MIN_VALUE) {
            
            return Integer.MIN_VALUE;
        }
        System.out.println(neg);
        return neg ? -1 * Integer.valueOf(sb.toString()) : Integer.valueOf(sb.toString());
    }
    
    public int myAtoi(String str) {
        if (str.length() == 0) {
            return 0;
        }
        
        str = str.trim(); // Remove leading 0s
        int sign = 1;
        int i = 0;
        
        // Sign
        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
            if (str.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }
        
        int num = 0;
        int bound = Integer.MAX_VALUE / 10;
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            int digit = str.charAt(i) - '0';
            
            if (num > bound) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            if (sign == -1 && num == bound && digit > 8) {
                return Integer.MIN_VALUE;
            }
            if (sign == 1 && num == bound && digit > 7) {
                return Integer.MAX_VALUE;
            }
            
            num = 10 * num + digit;
            i++;
        }
        
        return sign * num;
    }
}
