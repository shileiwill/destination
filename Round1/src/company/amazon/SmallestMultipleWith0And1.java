package company.amazon;

import java.util.ArrayList;
import java.util.List;
/**
 * You are given an integer N. You have to find smallest multiple of N which consists of digits 0 and 1 only. 
 * Since this multiple could be large, return it in form of a string.

Note:
- Returned string should not contain leading zeroes.

For example,

For N = 55, 110 is smallest multiple consisting of digits 0 and 1.
For N = 2, 10 is the answer.

Build a Binary tree and do level order traversal
        1
       / \
      /   \ 
     /     \ 
    /       \ 
   10       11   
  /  \     /  \
 /    \   /    \  
100  101  110  111
 */
public class SmallestMultipleWith0And1 {

    class BTIterator {
        List<String> list = new ArrayList<String>();
        
        BTIterator() {
            
        }
        
        List<String> nextLevel() {
            List<String> next = new ArrayList<String>();
            
            if (list.size() == 0) {
                list.add("1");
                return list;
            }
            
            for (String s : list) {
                next.add(s + "0");
                next.add(s + "1");
            }
            
            list = next;
            return list;
        }
    }
    
    public String multiple(int A) {
        BTIterator iterator = new BTIterator();
        
        while (true) {
            for (String s : iterator.nextLevel()) {
                long val = Long.parseLong(s); // What if overflow
                if (val >= A && val % A == 0) {
                    return s;
                }
            }
        }
    }
    
    public String multiple2(int A) {
        long res = -1;
        
        for (int i = 1; ; i++) {
            res = A * i;
            
            if (isValid(res + "")) {
                return res + "";
            }
        }
        
    }
    
    boolean isValid(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '1' && c != '0') {
                return false;
            }
        }
        
        return true;
    }

}
