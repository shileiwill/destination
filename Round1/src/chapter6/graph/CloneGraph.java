package chapter6.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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