package leetcode4.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 30. You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).
 */
public class SubstringWithConcatenation {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        Map<String, Integer> toFind = new HashMap<String, Integer>();
        Map<String, Integer> found = new HashMap<String, Integer>();
        
        for (String word : words) {
            toFind.put(word, toFind.getOrDefault(word, 0) + 1);
        }
        
        int count = words.length;
        int strLen = words[0].length();
        
        for (int i = 0; i + strLen * count <= s.length(); i++) {
            found.clear(); // Start New
            int j;
            for (j = 0; j < count; j++) {
                int sectionStart = i + j * strLen;
                String str = s.substring(sectionStart, sectionStart + strLen);
                
                if (!toFind.containsKey(str)) {
                    break; // 根本没有这个string
                }
                
                found.put(str, found.getOrDefault(str, 0) + 1);
                
                if (toFind.get(str) < found.get(str)) {
                    break;
                }
            }
            
            if (j == count) { // Fully complete
                res.add(i);
            }
        }
        
        return res;
    }
    
    // This sliding window solution is far better. But don't understand yet.
    public List<Integer> findSubstring3(String S, String[] L) {
        List<Integer> res = new LinkedList<>();
        if (L.length == 0 || S.length() < L.length * L[0].length())   return res;
        int N = S.length(), M = L.length, K = L[0].length();
        Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();
        for (String s : L) {
            if (map.containsKey(s))   map.put(s, map.get(s) + 1);
            else                      map.put(s, 1);
        }
        String str = null, tmp = null;
        for (int i = 0; i < K; i++) {
            int count = 0;  // remark: reset count 
            for (int l = i, r = i; r + K <= N; r += K) {
                str = S.substring(r, r + K);
                if (map.containsKey(str)) {
                    if (curMap.containsKey(str))   curMap.put(str, curMap.get(str) + 1);
                    else                           curMap.put(str, 1);
                    
                    if (curMap.get(str) <= map.get(str))    count++;
                    while (curMap.get(str) > map.get(str)) {
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        if (curMap.get(tmp) < map.get(tmp)) count--;
                    }
                    if (count == M) {
                        res.add(l);
                        tmp = S.substring(l, l + K);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        l += K;
                        count--;
                    }
                }else {
                    curMap.clear();
                    count = 0;
                    l = r + K;
                }
            }
            curMap.clear();
        }
        return res;
    }
}
