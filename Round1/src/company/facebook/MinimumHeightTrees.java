package company.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 310. For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. 
 * Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). 
 * Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. 
You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] 
and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Hint:

How many MHTs can a graph have at most?
Note:

(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. 
In other words, any connected graph without simple cycles is a tree.”

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */
public class MinimumHeightTrees {
    // https://discuss.leetcode.com/topic/30572/share-some-thoughts
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        
        List<Set<Integer>> connections = new ArrayList<Set<Integer>>();
        for (int i = 0; i < n; i++) {
            connections.add(new HashSet<Integer>());
        }
        
        for (int[] edge : edges) {
            int p1 = edge[0];
            int p2 = edge[1];
            
            connections.get(p1).add(p2);
            connections.get(p2).add(p1);
        }
        
        // 可以转成你熟悉的queue
        // Queue<Integer> queue = new LinkedList<Integer>();
        List<Integer> leaves = new ArrayList<Integer>();
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).size() == 1) {
                leaves.add(i);
            }
        }
        
        // The only possible results' sizes are 1 or 2. 
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<Integer>();
            for (int leaf : leaves) { // Move all leaves upward together
                int otherEnd = connections.get(leaf).iterator().next(); // Leaf has only 1 item in Set
                connections.get(otherEnd).remove(leaf); // 很像拓扑排序
                if (connections.get(otherEnd).size() == 1) {
                    newLeaves.add(otherEnd);
                }
            }
            leaves = newLeaves;
        }
        
        return leaves;
    }
    
    public List<Integer> findMinHeightTreesMyStyle(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashSet<Integer>());
        }
        
        for (int[] edge : edges) {
            int p1 = edge[0];
            int p2 = edge[1];
            
            map.get(p1).add(p2);
            map.get(p2).add(p1);
        }
        
        // 可以转成你熟悉的queue
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).size() == 1) {
                queue.offer(i);
            }
        }
        
        // 剪枝
        // The only possible results' sizes are 1 or 2. 
        while (n > 2) {
            int size = queue.size();
            n -= size;
            
            for (int i = 0; i < size; i++) {
            	int leaf = queue.poll();
            	
            	for (int otherEnd : map.get(leaf)) {
            		map.get(otherEnd).remove(leaf);
            		
            		if (map.get(otherEnd).size() == 1) {
            			queue.offer(otherEnd);
            		}
            	}
            	
            	map.remove(leaf);
            }
        }
        
        return queue;
    }
}
