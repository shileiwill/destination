package company.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 17. Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Recursive BFS
Recursive DFS
Iterative BFS
Iterative DFS
如果还有大于10的数字怎么办 e.g. 1: "abcde", 2: "", 3: "f", ..., 10: "x", 11: "y", 12: "z", ...
 */
public class LetterCombinationOfPhoneNumbers {

	public static void main(String[] args) {
		LetterCombinationOfPhoneNumbers lc = new LetterCombinationOfPhoneNumbers();
		lc.find("23");
		System.out.println();
		lc.findQueue("23");
	}


    public List<String> letterCombinations(String digits) {
        Map<Character, char[]> map = buildMap(); // Key type must be Character. Array can be char[]
        List<String> res = new ArrayList<String>();
        
        if (digits == null || digits.length() == 0) {
            return res;
        }
        
        helper(res, "", digits, map);
        
        return res;
    }
    
    // Recursive DFS
    private void helper(List<String> res, String sb, String digits, Map<Character, char[]> map) {
        if (sb.length() == digits.length()) {
            res.add(sb);
            return;
        }
        
        for (char c : map.get(digits.charAt(sb.length()))) {
            helper(res, sb + c, digits, map);
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

    // Iterative BFS, Very good. It is by myself!
    void find(String digits) {
        Map<Character, char[]> map = buildMap();
        List<String> res = new ArrayList<String>();
        
        for (int i = 0; i < map.get(digits.charAt(0)).length; i++) {
        	res.add(map.get(digits.charAt(0))[i] + "");
        }
        
        int size = res.size();
        for (int i = 1; i < digits.length(); i++) { // Every digit
        	List<String> next = new ArrayList<String>();
        	char[] arr = map.get(digits.charAt(i));
        	
        	for (int j = 0; j < size; j++) { // Every existing string prefix
        		String prefix = res.get(j);
        		for (char c : arr) { // Every possible next char
        			next.add(prefix + c);
        		}
        	}
        	
        	res = next;
        }
        
        for (String s : res) {
        	System.out.println(s);
        }
    }
    
    // 这个是你熟悉的queue BFS
    void findQueue(String digits) {
        Map<Character, char[]> map = buildMap();
        Queue<String> queue = new LinkedList<String>();
        
        for (int i = 0; i < map.get(digits.charAt(0)).length; i++) {
        	queue.offer(map.get(digits.charAt(0))[i] + "");
        }
        
        int pos = 1;
        while (pos < digits.length()) {
        	int size = queue.size();
        	char[] arr = map.get(digits.charAt(pos));
        	for (int i = 0; i < size; i++) {
        		String prefix = queue.poll();
        		for (char c : arr) {
        			queue.offer(prefix + c);
        		}
        	}
        	
        	pos++;
        }
        
        for (String s : queue) {
        	System.out.println(s);
        }
    }
}
