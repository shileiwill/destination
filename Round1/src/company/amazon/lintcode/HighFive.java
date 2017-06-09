package company.amazon.lintcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.sun.prism.impl.Disposer.Record;

/**
 * There are two properties in the node student id and scores, to ensure that each student will have at least 5 points, 
 * find the average of 5 highest scores for each person.
Given results = [[1,91],[1,92],[2,93],[2,99],[2,98],[2,97],[1,60],[1,58],[2,100],[1,61]]
 */
public class HighFive {
	static class Record {
		public int id, score;
		public Record(int id, int score){
			this.id = id;
			this.score = score;
		}
	}   
    /**
     * @param results a list of <student_id, score>
     * @return find the average of 5 highest scores for each person
     * Map<Integer, Double> (student_id, average_score)
     */
    public Map<Integer, Double> highFive(Record[] results) {
        // Write your code here
        Map<Integer, Double> res = new HashMap<Integer, Double>();
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();
       
        for (Record record : results) {
            int id = record.id;
            int score = record.score;
           
            if (!map.containsKey(id)) {
                map.put(id, new PriorityQueue<Integer>(100, Collections.reverseOrder()));
            }
            map.get(id).offer(score);
        }
       
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            PriorityQueue<Integer> heap = entry.getValue();
           
            int sum = 0;
            int count = 0;
            while (count < 5) {
                sum += heap.poll();
                count++;
            }
           
            res.put(key, sum / 5.0);
        }
       
        return res;
    }
}