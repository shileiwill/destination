package leetcode4.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 249. Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
A solution is:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
 */
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        // Key是相对偏移量
        for (String str : strings) {
            // Build the key
            int offset = str.charAt(0) - 'a';
            String key = "";
            for (char ch : str.toCharArray()) {
                char relativeChar = (char)(ch - offset);
                if (relativeChar < 'a') {
                    relativeChar += 26;
                }
                
                key += relativeChar;
            }
            
            // Group the strings with same key together
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<String>();
                map.put(key, list);
            }
            map.get(key).add(str);
        }
        
        // Best use of existing java APIs
        res.addAll(map.values());
        
        return res;
    }
}

