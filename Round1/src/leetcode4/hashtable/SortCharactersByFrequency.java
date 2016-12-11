package leetcode4.hashtable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 451. Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 */
public class SortCharactersByFrequency {
    public String frequencySortTwoMaps(String s) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        Map<Character, Integer> map1 = new HashMap<Character, Integer>();
        Map<Integer, Set<Character>> map2 = new HashMap<Integer, Set<Character>>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map1.containsKey(c)) {
                map1.put(c, 1);
                
                Set<Character> aSet = map2.getOrDefault(1, new HashSet<Character>());
                aSet.add(c);
                map2.put(1, aSet);
                
                min = Math.min(min, 1);
            } else {
                int oldCount = map1.get(c);
                // Add
                map1.put(c, oldCount + 1);
                
                Set<Character> aSet = map2.getOrDefault(oldCount + 1, new HashSet<Character>());
                aSet.add(c);
                map2.put(oldCount + 1, aSet);
                
                // Remove
                map2.get(oldCount).remove(c);
                max = Math.max(max, oldCount + 1);
            }
        }
        
        if (min == Integer.MAX_VALUE) {
            return s; // Empty String
        }
        if (max == Integer.MIN_VALUE) {
            max = 1; // All unique characters
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = max; i >= min; i--) {
            Set<Character> set = map2.get(i);
            for (char c : set) {
                StringBuilder sub = new StringBuilder();
                for(int count = 0; count < i; count++) {
                    sub.append(c);
                }
                sb.append(sub);
            }
        }
        
        return sb.toString();
    }
    
    public String frequencySortBucket(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        List<Character>[] bucket = new List[s.length() + 1];
        
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int value = entry.getValue();
            
            if (bucket[value] == null) {
                bucket[value] = new ArrayList<Character>();
            }
            bucket[value].add(key);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = bucket.length - 1; i > 0; i--) {
            if (bucket[i] == null) {
                continue;
            }
            
            List<Character> list = bucket[i];
            for (char c : list) {
                StringBuilder sub = new StringBuilder();
                for(int count = 0; count < i; count++) {
                    sub.append(c);
                }
                sb.append(sub);
            }
        }
        
        return sb.toString();
    }
    
    public String frequencySort(String s) {
        // Hey, this will not work. You can't give generic type here
        // LargeToSmallComparator<Map.Entry<Character, Integer>> comp = new LargeToSmallComparator<Map.Entry<Character, Integer>>();
        LargeToSmallComparator comp = new LargeToSmallComparator();
        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<Map.Entry<Character, Integer>>(comp);
        
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            heap.offer(entry);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) {
            Map.Entry<Character, Integer> entry = heap.poll();
            
            for(int count = 0; count < entry.getValue(); count++) {
                    sb.append(entry.getKey());
            }
        }
        
        return sb.toString();
    }
    
    class LargeToSmallComparator implements Comparator<Map.Entry<Character, Integer>> {
        public int compare(Map.Entry<Character, Integer> a, Map.Entry<Character, Integer> b) {
            return b.getValue() - a.getValue();
        }
    }
}