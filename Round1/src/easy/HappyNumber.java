package easy;
/**
 * 202. Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HappyNumber {
    Set<Integer> cache = new HashSet<Integer>();
    public boolean isHappy2(int n) {
        while (n != 1) {
            if (cache.contains(n)) {
                return false;
            }
            cache.add(n);
            
            int next = 0;
            while (n != 0) {
                int mod = n % 10;
                next += mod * mod;
                n = n / 10;
            }
            
            n = next;
        }
        
        return true;
    }
    
    Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        
        map.put(n, false);
        boolean res = isHappy(transform(n));
        map.replace(n, res);
        
        return res;
    }
    
    int transform(int n) {
        int next = 0;
        while (n != 0) {
            int mod = n % 10;
            next += mod * mod;
            n = n / 10;
        }
            
        return next;
    }
}
