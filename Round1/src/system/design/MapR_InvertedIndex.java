package system.design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

//Use map reduce to build inverted index for given documents.
class OutputCollector<K, V> {
	public void collect(K key, V value) {
	}
	// Adds a key/value pair to the output buffer
}

class Document {
	public int id;
	public String content;
}

public class MapR_InvertedIndex {
	public static class Map {
		public void map(Document doc, OutputCollector<String, Integer> output) {
			// Output the results into output buffer.
			// Ps. output.collect(String key, int value);
			int id = doc.id; // index of document
			StringTokenizer tokenizer = new StringTokenizer(doc.content);
			while (tokenizer.hasMoreTokens()) {
				String word = tokenizer.nextToken();
				output.collect(word, id); // 所有的这些单词都在一个document中
			}
		}
	}

	public static class Reduce {
		public void reduce(String key, Iterator<Integer> values, OutputCollector<String, List<Integer>> output) {
			// Output the results into output buffer.
			// Ps. output.collect(String key, List<Integer> value);
			List<Integer> results = new ArrayList<Integer>();
			int previous = -1;
			while (values.hasNext()) {
				int now = values.next();
				if (previous != now) { // result中不重复， 可能一个单词在一个document中出现了很多次
					results.add(now);
				}
				previous = now;
			}

			output.collect(key, results);
		}
	}

}
