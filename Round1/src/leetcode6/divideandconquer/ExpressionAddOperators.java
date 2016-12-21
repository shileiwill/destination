package leetcode6.divideandconquer;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
 */
public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        helper(res, sb, num, target, 0, 0, 0);
        return res;
    }
    
    // multi is the value which will be multiplied next time, need to save this value as multiply has higher priority
    void helper(List<String> res, StringBuilder sb, String str, int target, int pos, long prev, long multi) { // Type as long
        if (pos == str.length()) {
            if (prev == target) {
                res.add(sb.toString());
            }
            return;
        }
        
        for (int i = pos; i < str.length(); i++) {
            if (i != pos && str.charAt(pos) == '0') { // 首字母为0 后边就没事儿了
                break;
            }
            // Use Long here to avoid overflow, for example, 999999
            long num = Long.parseLong(str.substring(pos, i + 1));
            int prevLen = sb.length();
            
            if (sb.length() == 0) { // The first time, will add number only. OR here can be pos == 0
                helper(res, sb.append(num), str, target, i + 1, num, num);
                sb.setLength(prevLen);
            } else { // Will also add operator
                helper(res, sb.append("+").append(num), str, target, i + 1, prev + num, num);
                sb.setLength(prevLen);
                
                helper(res, sb.append("-").append(num), str, target, i + 1, prev - num, -num);
                sb.setLength(prevLen);
                
                // if you have a sequence of 12345 and you have proceeded to 1 + 2 + 3. If you want to add a * between 3 and 4, you would take 3 as the digit to be multiplied, so you want to take it out from the existing eval. You have 1 + 2 + 3 * 4 and the eval now is (1 + 2 + 3) - 3 + (3 * 4)
                helper(res, sb.append("*").append(num), str, target, i + 1, prev - multi + multi * num, multi * num);
                sb.setLength(prevLen);
            }
        }
    }
}