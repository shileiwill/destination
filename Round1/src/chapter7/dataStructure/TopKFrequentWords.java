package chapter7.dataStructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
Given a list of words and an integer k, return the top k frequent words in the list.
Example
Given
[
    "yes", "lint", "code",
    "yes", "code", "baby",
    "you", "baby", "chrome",
    "safari", "lint", "code",
    "body", "lint", "code"
]
for k = 3, return ["code", "lint", "baby"].
for k = 4, return ["code", "lint", "baby", "yes"],
Note
You should order the words by the frequency of them in the return list, the most frequent one comes first. If two words has the same frequency, the one with lower alphabetical order come first.
Challenge
Do it in O(nlogk) time and O(n) extra space.
Extra points if you can do it in O(n) time with O(k) extra space.
Tags Expand 
Hash Table Heap Priority Queue
*/
public class TopKFrequentWords {

	public static void main(String[] args) {
		String[] words = {    "yes", "lint", "code",
			    "yes", "code", "baby",
			    "you", "baby", "chrome",
			    "safari", "lint", "code",
			    "body", "lint", "code"};
		int k = 4;
		topKFrequentWords(words, k);
	}
	
	public static String[] topKFrequentWords(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Comparator<Map.Entry<String, Integer>> comp = new Comparator<Map.Entry<String, Integer>>(){
          public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
        	  if (m2.getValue() != m1.getValue()) {
        		  return m2.getValue() - m1.getValue();
        	  } else {
        		  return m1.getKey().compareTo(m2.getKey());
        	  }
          }  
        };
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<Map.Entry<String, Integer>>(k, comp);
        
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i])) {
                map.put(words[i], map.get(words[i]) + 1);
            } else {
                map.put(words[i], 1);
            }
        }
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
        	System.out.println(entry.getKey() + " -- " + entry.getValue());
            heap.offer(entry);
        }
        
        int count = 0;
        String[] res = new String[k];
        while (count < k) {
        	String key = heap.poll().getKey();
        	System.out.println(key);
            res[count] = key;
            count++;
        }
        
        return res;
	}

}
