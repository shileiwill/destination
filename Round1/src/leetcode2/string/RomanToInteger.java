package leetcode2.string;
/**
 * 13. Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
 */
public class RomanToInteger {
    public int romanToInt2(String s) {
        int sum = 0;
        // These are the only cases with double chars
        if (s.indexOf("IV") != -1) {
            sum -= 2;
        }
        if (s.indexOf("IX") != -1) {
            sum -= 2;
        }
        if (s.indexOf("XL") != -1) {
            sum -= 20;
        }
        if (s.indexOf("XC") != -1) {
            sum -= 20;
        }
        if (s.indexOf("CD") != -1) {
            sum -= 200;
        }
        if (s.indexOf("CM") != -1) {
            sum -= 200;
        }
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'I') {
                sum += 1;
            }
            if (c == 'V') {
                sum += 5;
            }
            if (c == 'X') {
                sum += 10;
            }
            if (c == 'L') {
                sum += 50;
            }
            if (c == 'C') {
                sum += 100;
            }
            if (c == 'D') {
                sum += 500;
            }
            if (c == 'M') {
                sum += 1000;
            }
        }
        
        return sum;
    }
    
    // Scan the string one time with switch check for seven cases. Only need to note that for I, X and C, these three letters can be placed before other letters to form subtraction. So every time we have one of these three letters, we need to check the letter behind this one to see if it is a subtraction. If it is, jump over the next one after calculation.
    public int romanToInt(String s) {
        int sum = 0;
        
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == 'I') {
                sum += (sum >= 5) ? -1 : 1; // IV, IX
            }
            if (c == 'V') {
                sum += 5;
            }
            if (c == 'X') {
                sum += (sum >= 50) ? -10 : 10; // XL, XC
            }
            if (c == 'L') {
                sum += 50;
            }
            if (c == 'C') {
                sum += (sum >= 500) ? -100 : 100;
            }
            if (c == 'D') {
                sum += 500;
            }
            if (c == 'M') {
                sum += 1000;
            }
        }
        
        return sum;
    }
}
