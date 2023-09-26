package chapter6.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
/**
 * 133. Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
 * @author Lei
 *
 */
public class CloneGraph {
    // Solution 1: using Traditional Queue
    public UndirectedGraphNode cloneGraphQueue(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        // write your code here
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        
        queue.offer(node);
        map.put(node, new UndirectedGraphNode(node.label));
        
        // Clone Nodes
        while (!queue.isEmpty()) {
            UndirectedGraphNode n = queue.poll();
            
            for (UndirectedGraphNode nei : n.neighbors) {
                if (!map.containsKey(nei)) {
                    queue.offer(nei);
                    map.put(nei, new UndirectedGraphNode(nei.label));
                }
            }
        }
        
        // Clone Edges
        for (Map.Entry<UndirectedGraphNode, UndirectedGraphNode> entry : map.entrySet()) {
            UndirectedGraphNode key = entry.getKey();
            UndirectedGraphNode cloned = entry.getValue();
            
            for (UndirectedGraphNode nei : key.neighbors) {
                cloned.neighbors.add(map.get(nei));
            }
        }
        
        return map.get(node);
    }
    
    // Solution 2: DFS
    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    public UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode node) {
        return dfs(node);
    }
    
    UndirectedGraphNode dfs(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        
        if (map.containsKey(node)) {
            return map.get(node);
        }
        
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(node, clone);
        
        for (UndirectedGraphNode nei : node.neighbors) {
            clone.neighbors.add(dfs(nei));
        }
        
        return clone;
    }


    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // This is breadth first search
        if (node == null) {
            return null;
        }
        
        ArrayList<UndirectedGraphNode> list = new ArrayList<UndirectedGraphNode>(); 
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>(); // old -> new 
        
        list.add(node);
        map.put(node, new UndirectedGraphNode(node.label));
        
        int start = 0;
        // Clone nodes
        while (start < list.size()) {
            UndirectedGraphNode aNode = list.get(start++);
            List<UndirectedGraphNode> neighbors = aNode.neighbors;
            
            for (UndirectedGraphNode neighbor : neighbors) {
                if (!map.containsKey(neighbor)) { // We can also use queue.contains()
                    UndirectedGraphNode cloneNei = new UndirectedGraphNode(neighbor.label);
                    map.put(neighbor, cloneNei);
                    list.add(neighbor);
                }
            }
        }
        
        // Clone edges
        for (UndirectedGraphNode n : list) {
            UndirectedGraphNode clonedNode = map.get(n);
            for (UndirectedGraphNode nei : n.neighbors) {
                clonedNode.neighbors.add(map.get(nei));
            }
        }
        
        return map.get(list.get(0));
    }
}

class UndirectedGraphNode {
	int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}