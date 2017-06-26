package company.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 500. Given a List of words, return the words that can be typed using letters of alphabet 
 * on only one row's of American keyboard like the image below.

American keyboard


Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]
Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.
 */
public class KeyboardRow {
    public String[] findWords(String[] words) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        Character[] arr1 = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'};
        for (char c1 : arr1) {
            map.put(c1, 1);
        }
        
        Character[] arr2 = {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'};
        for (char c2 : arr2) {
            map.put(c2, 2);
        }
        
        Character[] arr3 = {'z', 'x', 'c', 'v', 'b', 'n', 'm'};
        for (char c3 : arr3) {
            map.put(c3, 3);
        }
        
        List<String> set = new ArrayList<String>();
        for (String word : words) {
            int row = -1;
            String word2 = word.toLowerCase();
            
            int i = 0;
            for (; i < word2.toCharArray().length; i++) {
                char c = word2.toCharArray()[i];
                int now = map.get(c);
                
                if (row == -1) {
                    row = now; 
                } else if (row != now) {
                    break;
                }
            }
            
            if (i == word2.toCharArray().length) {
                set.add(word);
            }
        }
        
        String[] res = new String[set.size()];
        int index = 0;
        
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            res[index++] = it.next();
        }
        
        return res;
    }
}
