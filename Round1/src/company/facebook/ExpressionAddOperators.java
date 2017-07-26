package company.facebook;

import java.util.ArrayList;
import java.util.List;
/**
 * 282. Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * 
 * between the digits so they evaluate to the target value.

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
        helper(res, num, 0, 0, "", target, 0);
        return res;
    }
    
    void helper(List<String> res, String num, int pos, long val, String s, long target, long tmp) {
        if (pos == num.length()) {
            if (val == target) {
                res.add(s);
            }
            return;
        }
        
        for (int len = 1; len <= num.length() - pos; len++) { // 一个for循环就足够了
            if (len != 1 && num.charAt(pos) == '0') {
                break;
            }
            long cur = Long.valueOf(num.substring(pos, pos + len)); // 利用好start point
            
            if (s.length() > 0) {
                helper(res, num, pos + len, val + cur, s + "+" + cur, target, cur);
                helper(res, num, pos + len, val - cur, s + "-" + cur, target, -cur);
                helper(res, num, pos + len, val - tmp + tmp * cur, s + "*" + cur, target, tmp * cur);
            } else {
                helper(res, num, pos + len, val + cur, cur + "", target, cur);
            }
        }
    }
}
