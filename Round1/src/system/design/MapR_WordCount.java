package system.design;

import java.util.Iterator;
import java.util.StringTokenizer;
/*
 * Using map reduce to count word frequency.

https://hadoop.apache.org/docs/r1.2.1/mapred_tutorial.html#Example%3A+WordCount+v1.0

Example
chunk1: "Google Bye GoodBye Hadoop code"
chunk2: "lintcode code Bye"


Get MapReduce result:
    Bye: 2
    GoodBye: 1
    Google: 1
    Hadoop: 1
    code: 2
    lintcode: 1
 * */
public class MapR_WordCount {
    public static class Map {
        public void map(String content, OutputCollector<String, Integer> output) {
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            StringTokenizer tokenizer = new StringTokenizer(content);
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                output.collect(word, 1); // 发现一次就记录1
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values,
                           OutputCollector<String, Integer> output) {
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            int sum = 0;
            while (values.hasNext()) {
                    sum += values.next();
            }
            output.collect(key, sum);
        }
    }

}
