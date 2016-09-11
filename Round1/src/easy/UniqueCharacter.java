package easy;
/**
 * 387. Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
 */
import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueCharacter {
    public int firstUniqChar(String s) {
        if (s.length() == 0) {
            return -1;
        }
        Map<Character, ResultType> map = new LinkedHashMap<Character, ResultType>();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            if (map.containsKey(key)) {
                // map.put(key, new ResultType(map.get(key).index, map.get(key).count + 1));
                map.get(key).count = map.get(key).count + 1;
            } else {
                map.put(key, new ResultType(i, 1));
            }
        }
        
        for (Map.Entry<Character, ResultType> entry : map.entrySet()) {
            char key = entry.getKey();
            ResultType val = entry.getValue();
            
            if (val.count == 1) {
                return val.index;
            }
        }
        
        return -1;
    }
    // Another version, better
    public int firstUniqChar2(String s) {
        char[] hash = new char[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            hash[index] += 1;
        }
        
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (hash[index] == 1) {
                return i;
            }
        }
        return -1;
    }
}

class ResultType {
        int index;
        int count;
        ResultType(int index, int count) {
            this.index = index;
            this.count = count;
        }
}