package leetcode15.bfs.unionfind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 323. Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {
    // https://discuss.leetcode.com/topic/32752/easiest-2ms-java-solution
    public int countComponentsUnionFind(int n, int[][] edges) {
        int[] nodes = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = i;
        }
        
        for (int[] edge : edges) {
            int p1 = find(nodes, edge[0]);
            int p2 = find(nodes, edge[1]);
            
            if (p1 != p2) {
                nodes[p1] = p2;
                n--;
            }
        }
        
        return n;
    }
    
    int find(int[] nodes, int id) {
        while (nodes[id] != id) {
            nodes[id] = nodes[nodes[id]]; // Optional, path compression
            id = nodes[id];
        }
        return nodes[id];
    }
    
    public int countComponentsDFS(int n, int[][] edges) {
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
        
        Set<Integer> visited = new HashSet<Integer>();
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                visited.add(i);
                dfs(i, visited, connections);
                count++;
            }
        }
        
        return count;
    }
    
    void dfs(int i, Set<Integer> visited, List<Set<Integer>> conns) {
        Set<Integer> set = conns.get(i);
        for (int s : set) {
            if (!visited.contains(s)) {
                visited.add(s);
                dfs(s, visited, conns);
            }
        }
    }
    
    public int countComponents(int n, int[][] edges) {
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
        
        boolean[] visited = new boolean[n];
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Queue<Integer> queue = new LinkedList<Integer>();
                queue.offer(i);
                visited[i] = true;
                count++;
                
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    Set<Integer> set = connections.get(cur);
                    
                    for (int s : set) {
                        if (!visited[s]) {
                            queue.offer(s);
                            visited[s] = true;
                        }
                    }
                }
            }
        }
        
        return count;
    }
}