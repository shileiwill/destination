package system.design;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * Find top k frequent words with map reduce framework.

The mapper's key is the document id, value is the content of the document, 
words in a document are split by spaces.

For reducer, the output should be at most k key-value pairs, 
which are the top k words and their frequencies in this reducer. 
The judge will take care about how to merge different reducers' 
results to get the global top k frequent words, 
so you don't need to care about that part.

The k is given in the constructor of TopK class.

For the words with same frequency, rank them with alphabet.

Given document A =
lintcode is the best online judge
I love lintcode

and document B =
lintcode is an online judge for coding interview
you can test your code online at lintcode

The top 2 words and their frequencies should be
lintcode, 4
online, 3
 * */

class Pair {
	    String key;
	    int count;
	    
	    Pair(String key, int value) {
	        this.key = key;
	        this.count = value;
	    }
}

public class MapR_topKfreqWords {
    public static class Map {
        public void map(Document doc,
                        OutputCollector<String, Integer> output) {
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            String[] words = doc.content.split("\\s+");
            
            for (String word : words) {
            	if (word.length() > 0) {
                    output.collect(word, 1); // 出现一次就是一次，后边再累加， sum不是Map的任务
                }
            }
        }
    }

    public static class Reduce {
        private PriorityQueue<Pair> heap = null;
        private int k;

        private Comparator<Pair> pairComparator = new Comparator<Pair>() {
            public int compare(Pair left, Pair right) {
                if (left.count != right.count) {
                    return left.count - right.count;
                }
                return right.key.compareTo(left.key); // 如果count相等，那么按字母排序
            }
        };

        public void setup(int k) {
            this.k = k;
            heap = new PriorityQueue<Pair>(k, pairComparator);
        }   

        public void reduce(String key, Iterator<Integer> values) { //这一堆values都是这个单词的
            int sum = 0;
            while (values.hasNext()) {
                    sum += values.next();
            }

            Pair pair = new Pair(key, sum);
            if (heap.size() < k) {
                heap.add(pair);
            } else {
                if (heap.peek().count < pair.count) {
                    heap.poll();
                    heap.add(pair);
                }
            }
        }

        public void cleanup(OutputCollector<String, Integer> output) {
            // Output the top k pairs <word, times> into output buffer.
            // Ps. output.collect(String key, Integer value);
            List<Pair> pairs = new ArrayList<Pair>();
            while (!heap.isEmpty()) {
                pairs.add(heap.poll());
            }

            // reverse result
            int n = pairs.size();
            for (int i = n - 1; i >= 0; --i) {
                Pair pair = pairs.get(i);
                output.collect(pair.key, pair.count);
            }
        }
    }
}
