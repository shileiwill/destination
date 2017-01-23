package leetcode15.bfs.dfs.unionfind;

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
    Map<String, PriorityQueue<String>> flights = new HashMap<String, PriorityQueue<String>>();
    LinkedList<String> res = new LinkedList<String>();
    
    public List<String> findItinerary(String[][] tickets) {
        for (String[] pair : tickets) {
            flights.putIfAbsent(pair[0], new PriorityQueue<String>());
            flights.get(pair[0]).add(pair[1]);
        }
        dfs("JFK");
        return res;
    }
    
    void dfs(String departure) {
        PriorityQueue<String> heap = flights.get(departure);
        while (heap != null && !heap.isEmpty()) { // why use while
            dfs(heap.poll());
        }
        res.addFirst(departure);
    }
}
