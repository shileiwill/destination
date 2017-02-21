package leetcode15.bfs.dfs.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 261. Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
public class GraphValidTree {
	// https://discuss.leetcode.com/topic/22486/ac-java-solutions-union-find-bfs-dfs
    public boolean validTreeBFS(int n, int[][] edges) {
        // Can also use Map<Integer, Set<Integer>>
        List<Set<Integer>> connections = new ArrayList<Set<Integer>>();
        for (int i = 0; i < n; i++) {
            connections.add(new HashSet<Integer>());
        }
        
        for (int[] edge : edges) {
            int p1 = edge[0];
            int p2 = edge[1];
            
            // Put both
            connections.get(p1).add(p2);
            connections.get(p2).add(p1);
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            Set<Integer> con = connections.get(cur);
            for (int next : con) {
                if (visited[next]) {
                    return false;
                }
                connections.get(next).remove(cur);
                visited[next] = true;
                queue.offer(next);
            }
        }
        
        // check all are connected
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean validTreeDFS(int n, int[][] edges) {
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
        
        // Just change from Queue to Stack
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            
            Set<Integer> con = connections.get(cur);
            for (int next : con) {
                if (visited[next]) {
                    return false;
                }
                connections.get(next).remove(cur);
                visited[next] = true;
                stack.push(next);
            }
        }
        
        // check all are connected
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        
        return true;
    }
    
    // https://discuss.leetcode.com/topic/21712/ac-java-union-find-solution
    public boolean validTree(int n, int[][] edges) {

        UnionFind uf = new UnionFind(n);
        
        for (int[] conn : edges) {
            int p1 = uf.find(conn[0]);
            int p2 = uf.find(conn[1]);
            
            // If p1 and p2 are connected and they share the same parent, circle found
            if (p1 == p2) {
                return false;
            }
            
            // Union
            uf.union(p1, p2);
        }
        // Why? Make sure no isolated nodes
        return edges.length == n - 1;
    }
    
    class UnionFind {
        int[] parent = null;
        
        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        void union(int id1, int id2) {
            parent[id1] = id2;
        }
        
        int find(int id) {
            while (parent[id] != id) {
                parent[id] = parent[parent[id]];
                id = parent[id];
            }
            return id;
        }
    }
}