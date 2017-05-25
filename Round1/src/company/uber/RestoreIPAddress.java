package company.uber;

import java.util.ArrayList;
import java.util.List;

//93
/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
public class RestoreIPAddress {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        List<String> list = new ArrayList<String>();
        
        helper(res, list, s, 0);
        return res;
    }
    
    void helper(List<String> res, List<String> list, String s, int pos) {
        if (list.size() == 4) {
            if (pos != s.length()) {
                return;
            }
            
            StringBuilder sb = new StringBuilder();
            for (String val : list) {
                sb.append(val + ".");
            }
            
            sb.setLength(sb.length() - 1);
            res.add(sb.toString());
            return;
        }
        
        for (int len = 1; pos + len <= s.length() && len <= 3; len++) {
            String sub = s.substring(pos, pos + len); // Next section
            
            if (!isValidSection(sub)) {
                continue;
            }
            
            list.add(sub);
            helper(res, list, s, pos + len);
            list.remove(list.size() - 1);
        }
    }
    
    boolean isValidSection(String s) {
        if (s.charAt(0) == '0') {
            return s.length() == 1;
        }
        
        int val = Integer.parseInt(s);
        return val >= 1 && val <= 255;
    }

}
