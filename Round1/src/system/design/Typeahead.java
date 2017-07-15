package system.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/*
 * Implement typeahead. Given a string and a dictionary, return all words that contains the string as a substring. 
 * The dictionary will give at the initialize method and wont be changed. The method to find all words with given
		substring would be called multiple times.

Given dictionary = {"Jason Zhang", "James Yu", "Bob Zhang", "Larry Shi"}

search "Zhang", return ["Jason Zhang", "Bob Zhang"].

search "James", return ["James Yu"].

当数据量小时，可以用Map做。数据量大了，用Trie做
 * */

public class Typeahead {
    private HashMap<String, List<String>> map = new HashMap<String , List<String>>();
    
    // @param dict A dictionary of words dict
    public Typeahead(Set<String> dict) {
        for (String str : dict) {
            int len = str.length();
            
            for (int start = 0; start < len; start++) {
            	for (int end = start + 1; end <= len; end++) {
                    String sub = str.substring(start, end);
                    
                    if (!map.containsKey(sub)) {
                        map.put(sub, new ArrayList<String>());
                        map.get(sub).add(str);
                    } else {
                        List<String> list = map.get(sub);
                        if (!str.equals(list.get(list.size() - 1))) { // 去重，{"Jason Zhang"， "Jason Zhang"}
                            list.add(str);
                        }
                    }
                }
            }
        }
    }

    // @param str: a string
    // @return a list of words
    public List<String> search(String str) {
        if (!map.containsKey(str)) {
            return new ArrayList<String>();
        } else {
            return map.get(str);
        }
    }
}
