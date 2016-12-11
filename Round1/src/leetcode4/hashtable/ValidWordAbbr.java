package leetcode4.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * 288. An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> 
false

isUnique("cart") -> 
true

isUnique("cane") -> 
false

isUnique("make") -> 
true
 */
public class ValidWordAbbr {
// A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
     // no other word
    Set<String> set = new HashSet<String>();
    
    public ValidWordAbbr(String[] dictionary) {
        for (String s : dictionary) {
            String abbr = calAbbreviation(s);
            set.add(abbr);
        }
    }

    public boolean isUnique(String word) {
        String abbr = calAbbreviation(word);
        return set.contains(abbr);
    }
    
    private String calAbbreviation(String word) {
        int len = word.length();
        if (len <= 2) {
            return word;
        }
        
        return "" + word.charAt(0) + (len - 2) + word.charAt(len - 1);
    }
}

class ValidWordAbbr2 {
    Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    
    public ValidWordAbbr2(String[] dictionary) {
        for (String s : dictionary) {
            String abbr = calAbbreviation(s);
            if (!map.containsKey(abbr)) {
                map.put(abbr, new HashSet<String>());
            }
            map.get(abbr).add(s);
        }
    }

    public boolean isUnique(String word) {
        if (map.isEmpty()) {
            return true;
        }
        
        String abbr = calAbbreviation(word);
        if (!map.containsKey(abbr)) {
            return true;
        }
        
        Set<String> set = map.get(abbr);
        if (set.size() == 1 && set.contains(word)) {
            return true;
        }
        
        return false;
    }
    
    private String calAbbreviation(String word) {
        int len = word.length();
        if (len <= 2) {
            return word;
        }
        
        return "" + word.charAt(0) + (len - 2) + word.charAt(len - 1);
    }
}


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
