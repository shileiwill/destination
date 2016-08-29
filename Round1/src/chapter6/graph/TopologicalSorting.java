package chapter6.graph;
/**
 * Given an directed graph, a topological order of the graph nodes is defined as follow:

For each directed edge A -> B in graph, A must before B in the order list.
The first node in the order can be any node in the graph with no nodes direct to it.
Find any topological order for the given graph.

 Notice

You can assume that there is at least one topological order in the graph.
 * @author Lei
 *
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TopologicalSorting {
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        ArrayList<DirectedGraphNode> res = new  ArrayList<DirectedGraphNode>();
        Map<DirectedGraphNode, Integer> map = new HashMap<DirectedGraphNode, Integer>();
        
        // Calculate in-degree. The node with in-degree 0, never comes in
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);
                } else {
                    map.put(neighbor, 1);
                }
            }    
        }
        
        Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
        // Find out in-degree == 0 and add to res, queue
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                queue.offer(node);
                res.add(node);
            }
        }
        
        // Iterate all other nodes, add sequentially by in-degree
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            for (DirectedGraphNode neighbor : node.neighbors) {
                int in_degree = map.get(neighbor) - 1;
                map.put(neighbor, in_degree);
                if (in_degree == 0) {
                    queue.offer(neighbor);
                    res.add(neighbor);
                }
            }
        }
        
        return res;
    }
}

class DirectedGraphNode {
     int label;
     ArrayList<DirectedGraphNode> neighbors;
     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
}