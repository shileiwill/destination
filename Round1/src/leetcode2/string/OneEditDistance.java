package leetcode2.string;
// 161. Given two strings S and T, determine if they are both one edit distance apart.
public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        
        if (s.length() == t.length()) {
            return isOneModify(s, t);
        }
        
        if (s.length() > t.length()) {
            return isOneDelete(s, t);
        }
        
        return isOneDelete(t, s);
    }
    
    // s will always be 1 character longer than t
    boolean isOneDelete(String s, String t) {
        int minLen = t.length();
        
        for (int i = 0; i < minLen; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return s.substring(i + 1).equals(t.substring(i));
            }
        }
        
        return true;
    }
    
    boolean isOneModify(String s, String t) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            // Or we can calculate the number of different characters 
            if (s.charAt(i) != t.charAt(i)) {
                return s.substring(i + 1).equals(t.substring(i + 1));
            }
        }
        return false;// If All of them are the same
    }
}