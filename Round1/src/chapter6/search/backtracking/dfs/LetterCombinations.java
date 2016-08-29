package chapter6.search.backtracking.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.
 * @author Lei
 *
 */
public class LetterCombinations {

    public List<String> letterCombinations(String digits) {
        Map<Character, char[]> map = buildMap(); // Key type must be Character. Array can be char[]
        List<String> res = new ArrayList<String>();
        
        if (digits == null || digits.length() == 0) {
            return res;
        }
        // Compare with other subsets questions, this is StringBuilder, rather than List
        StringBuilder sb = new StringBuilder();
        helper(res, sb, digits, map);
        
        return res;
    }
    
    private void helper(List<String> res, StringBuilder sb, String digits, Map<Character, char[]> map) {
        
        int length = sb.length();
        if (length == digits.length()) { // When achieve desired length, done!
            res.add(sb.toString()); // Usually, we should protect this SB. Why we dont need to make deep copy of StringBuilder
            return; // This is because sb.toString() will generate a new String object.
        }
        
        char nextDigit = digits.charAt(length); // Get next digit, based on the length of dynamic StringBuilder
        char[] letters = map.get(nextDigit); // Get the list of chars
        for (char c : letters) { // Iterate and backtrack the list
            sb.append(c);
            helper(res, sb, digits, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    private Map<Character, char[]> buildMap() {
        Map<Character, char[]> map = new HashMap<Character, char[]>();
        
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
        
        return map;
    }
}
