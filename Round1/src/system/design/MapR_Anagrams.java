package system.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/*
 * Use Map Reduce to find anagrams in a given list of words.

Example
Given ["lint", "intl", "inlt", "code"], return ["lint", "inlt", "intl"],["code"].
Given ["ab", "ba", "cd", "dc", "e"], return ["ab", "ba"], ["cd", "dc"], ["e"].

Constructs a string tokenizer for the specified string. The tokenizer uses the default delimiter set, which is " \t\n\r\f": 
the space character, the tab character, the newline character, the carriage-return character, and the form-feed character. 
Delimiter characters themselves will not be treated as tokens.
 * */
public class MapR_Anagrams {
	static class OutputCollector<K, V> {
		public void collect(K key, V value) {};
		// Adds a key/value pair to the output buffer
	}
	
    public static class Map { // 是单独的一个class
        public void map(String aLongString,
                        OutputCollector<String, String> output) {
            // Output the results into output buffer.
            // Ps. output.collect(String key, String value);
            StringTokenizer tokenizer = new StringTokenizer(aLongString);
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                String original = word;
                char[] chars = original.toCharArray();
                Arrays.sort(chars);
                String sorted = new String(chars);
                
                output.collect(sorted, word);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<String> values,
                           OutputCollector<String, List<String>> output) {
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<String> value);
            List<String> results = new ArrayList<String>();
            while (values.hasNext()) {
                    results.add(values.next());
            }
            
            output.collect(key, results);
        }
    }

}
