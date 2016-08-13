package chapter7.dataStructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElement {

	public static void main(String[] args) {
		int[] nums = {1,1,1,2,2,3};
		int k = 2;
		List<Integer> res = topKFrequent(nums, k);
	}

    public static List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Comparator<Map.Entry<Integer, Integer>> comp = new Comparator<Map.Entry<Integer, Integer>>(){
          public int compare(Map.Entry<Integer, Integer> m1, Map.Entry<Integer, Integer> m2) {
              return m2.getValue() - m1.getValue();
          }  
        };
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<Map.Entry<Integer, Integer>>(k, comp);
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        	System.out.println(entry.getKey() + " -- " + entry.getValue());
            heap.offer(entry);
        }
        
        int count = 0;
        List<Integer> res = new ArrayList<Integer>();
        while (count < k) {
        	int key = heap.poll().getKey();
        	System.out.println(key);
            res.add(key);
            count++;
        }
        
        return res;
    }
}
