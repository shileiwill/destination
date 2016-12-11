package leetcode4.hashtable;

import java.util.HashMap;
import java.util.Map;
/**
 * 166. Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
Hint:

No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.
 */
public class FractionToRecurringDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        
        // Multiple is very dangerous, stackoverflow
        if ((numerator < 0) ^ (denominator < 0)) {
            sb.append("-");
        }
        
        long numeratorL = Math.abs((long)numerator);
        long denominatorL = Math.abs((long)denominator);
        
        sb.append(numeratorL / denominatorL);
        long remainder = numeratorL % denominatorL;
        
        if (remainder == 0) {
            return sb.toString();
        }
        
        sb.append(".");
        
        while (remainder != 0) {
            map.put(remainder, sb.length());
            
            remainder *= 10;
            sb.append(remainder / denominatorL);
            remainder = remainder % denominatorL;
            
            if (map.containsKey(remainder)) {
                int start = map.get(remainder);
                sb.insert(start, "(");
                sb.append(")");
                break;
            }
        }
        
        return sb.toString();
    }
}