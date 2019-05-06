package company.snapchat;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 332. Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 */
public class ReconstructItinerary {
    // https://discuss.leetcode.com/topic/36383/share-my-solution
    Map<String, PriorityQueue<String>> flights = new HashMap<String, PriorityQueue<String>>(); //从这个机场能到哪些机场，到达机场根据首字母排序
    LinkedList<String> res = new LinkedList<String>();
    
    public List<String> findItinerary(String[][] tickets) {
        for (String[] pair : tickets) {
            flights.putIfAbsent(pair[0], new PriorityQueue<String>());
            flights.get(pair[0]).add(pair[1]);
        }
        dfs("JFK");
        return res;
    }
    
    // Assumption: You may assume all tickets form at least one valid itinerary.
    // 如果没有solution，这个会return半个itinerary
    // 因为用了heap，这个solution会优先找到最优解
    void dfs(String departure) {
    	// When to stop? we are polling from heap, so eventually the heap will be empty
        PriorityQueue<String> heap = flights.get(departure);
        while (heap != null && !heap.isEmpty()) { // why use while
            dfs(heap.poll()); // Since departure is fixed, let's try all the destinations
        }
        res.addFirst(departure);
    }
    
    /**
     * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=500584&highlight=snapchat
     * My understanding: 给一堆飞行的schedule，打印出所有能到的城市来
     *  input = [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
		output = ['JFK', 'NRT', 'JFK', 'KUL']
		
		
		input = [["JFK","SFO"],["JFK","ATL"],["ATL","NRT"],["ATL","SFO"]]
		
		output = ['JFK', 'SFO', 'ATL', 'SFO', 'NRT']
		
		第一个输出了2遍JFK，因为NRT自己也能飞到JFK
		第二个只输出了一遍JFK
     */
    public List<String> findItinerary2(String[][] tickets) {
        for (String[] pair : tickets) {
            flights.putIfAbsent(pair[0], new PriorityQueue<String>());
            flights.get(pair[0]).add(pair[1]);
        }
        dfs2("JFK");
        return res;
    }
    
    void dfs2(String departure) {
    	// When to stop? we are polling from heap, so eventually the heap will be empty
        PriorityQueue<String> heap = flights.get(departure);
        while (heap != null && !heap.isEmpty()) { // why use while
            dfs(heap.poll()); // Since departure is fixed, let's try all the destinations
        }
        res.addFirst(departure);
    }
    
    public static void main(String[] args) {
    	ReconstructItinerary ri = new ReconstructItinerary();
    	//String[][] arr = {{"JFK", "KUL"}, {"JFK","NRT"}, {"NRT","JFK"}};
    	String[][] arr = {{"JFK","SFO"},{"JFK","ATL"},{"ATL","NRT"},{"ATL","SFO"}};
    	List<String> res = ri.findItinerary2(arr);
    	
    	for (String s : res) {
    		System.out.println(s);
    	}
    }
}
