package leetcode2.string;
/**
 * 408. Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.
 */
public class ValidWordAbbreviation {
    public boolean validWordAbbreviation2(String word, String abbr) {
        int p1 = 0, p2 = 0;
        
        while (p1 < word.length() && p2 < abbr.length()) {
            char c1 = word.charAt(p1);
            char c2 = abbr.charAt(p2);
            
            if (c2 > '0' && c2 <= '9') { // It is a number, not starting with 0
                String numStr = c2 + "";
                p2++; // Check if next char is digit
                
                while (p2 < abbr.length()) {
                    char cc = abbr.charAt(p2);
                    if (Character.isDigit(cc)) { // isDigit includes 0
                        numStr += cc;
                        p2++;
                    } else {
                        break;
                    }
                }
                
                int num = Integer.parseInt(numStr);
                int i;
                for (i = 0; i < num && p1 < word.length(); i++) {
                    p1++;
                }
                
                if (i < num) {
                    return false;
                }
            } else { // It is a letter, or c2 can be 0
                if (c1 != c2) {
                    return false;
                }
                p1++;
                p2++;
            }
        }
        
        return p1 == word.length() && p2 == abbr.length();
    }
    
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        
        while (i < word.length() && j < abbr.length()) {
            char c1 = word.charAt(i);
            char c2 = abbr.charAt(j);
            
            if (c1 == c2) { // They are equal, just continue
                i++;
                j++;
                continue;
            }
            
            if (c2 <= '0' || c2 > '9') { // c2 is a letter or 0, and it is not equal to c2. false
                return false;
            }
            
            int start = j;
            while (j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                j++;
            }
            
            int len = Integer.parseInt(abbr.substring(start, j));
            
            i += len; // Let word jump
        }
        
        return i == word.length() && j == abbr.length();
    }
}
