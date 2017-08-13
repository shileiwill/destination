package company.facebook.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 247. A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
 */
public class StrobogrammaticNumber2 {

    public List<String> findStrobogrammaticRecursion(int n) {
        return helper(n, n);
    }
    
    List<String> helper(int m, int n) {
        if (m == 0) {
            return new ArrayList<String>(Arrays.asList(""));
        }
        if (m == 1) {
            return new ArrayList<String>(Arrays.asList("0", "1", "8"));
        }
        
        List<String> list = helper(m - 2, n);
        List<String> res = new ArrayList<String>();
        
        for (String str : list) {
            if (m != n) { // Not the first time
                res.add("0" + str + "0");
            }
            res.add("1" + str + "1");
            res.add("6" + str + "9");
            res.add("9" + str + "6");
            res.add("8" + str + "8");
        }
        
        return res;
    }
    
    public List<String> findStrobogrammatic(int n) {
        List<String> one = Arrays.asList("0", "1", "8");
        List<String> two = Arrays.asList("");
        List<String> ours = n % 2 == 0 ? two : one;
        
        for (int i = (n % 2) + 2; i <= n; i += 2) {
            List<String> list = new ArrayList<String>();
            // From inner to outer
            for (String str : ours) {
                if (i != n) {
                    list.add("0" + str + "0");
                }
                list.add("1" + str + "1");
                list.add("6" + str + "9");
                list.add("8" + str + "8");
                list.add("9" + str + "6");
            }
            
            ours = list;
        }
        
        return ours;
    }

}
