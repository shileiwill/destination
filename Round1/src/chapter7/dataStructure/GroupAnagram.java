package chapter7.dataStructure;
/**
 * 49. Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagram {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        List<List<String>> res = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        
        // Build Map
        for (int i = 0; i < strs.length; i++) {
            char[] arr = strs[i].replaceAll("[\\s]", "").toCharArray();
            Arrays.sort(arr); // Sort to make different anagrams share the same key
            String strKey = new String(arr);
            
            if (map.containsKey(strKey)) {
                List<String> list = map.get(strKey);
                list.add(strs[i]);
                map.put(strKey, list);
            } else {
                List<String> list = new ArrayList<String>();
                list.add(strs[i]);
                map.put(strKey, list);
            }
        }
        
        // Build List
        for (String key : map.keySet()) {
            if (map.get(key).size() > 0) { // If we want only the ones with anagrams, change this to > 1
                res.add(map.get(key));
            }
        }
        
        return res;
    }
}
