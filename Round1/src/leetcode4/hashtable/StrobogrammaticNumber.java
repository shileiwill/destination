package leetcode4.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 246. A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic
 */
public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');

        int left = 0, right = num.length() - 1;
        while (left <= right) { // Add equal to deal with single number
            char leftChar = num.charAt(left);
            char rightChar = num.charAt(right);
            // Actually no need to check map.containsKey(rightChar), as we will check equa
            if (!map.containsKey(leftChar) || !map.containsKey(rightChar)) {
                return false;
            }
            
            if (map.get(leftChar) != rightChar) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
}
