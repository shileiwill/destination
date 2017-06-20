package company.facebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
// 261   3 ways: Topological Sort, DFS/BFS, Union Find
public class GraphValidTree {

	public static void main(String[] args) {

	}

	static class Node {
		int val;
		Set<Node> friends;
	}
	
	// Use topological sort
	public boolean isValid(List<Node> nodes) {
		if (nodes == null || nodes.size() == 0) {
			return true;
		}
		
		Map<Node, Integer> inDegree = new HashMap<Node, Integer>();
		for (Node node : nodes) {
			for (Node friend : node.friends) {
				inDegree.put(friend, inDegree.getOrDefault(friend, 0) + 1);
			}
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		for (Node node : nodes) {
			if (!inDegree.containsKey(node)) {
				queue.offer(node); // The root node, should be only one
			} else {
				if (inDegree.get(node) >= 2) { // More than 2 nodes coming to this node, circle!
					return false;
				}
			}
		}
		
		if (queue.size() != 1) { // Root can't be 0 or > 1
			return false;
		}
		
		// Do we still need to BFS the Tree and use a visted set? Or must be true here?
		return true;
	}

    public boolean validTree(int n, int[][] edges) {
        if (edges.length == 0) {
            return n == 1;
        }
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        Set<Integer> visited = new HashSet<Integer>();
        
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            
            if (!map.containsKey(x)) {
                map.put(x, new HashSet<Integer>());
            }
            map.get(x).add(y);
            
            if (!map.containsKey(y)) {
                map.put(y, new HashSet<Integer>());
            }
            map.get(y).add(x);
        }
        // Change Queue to Stack, it will be dfs
        Queue<Integer> queue = new LinkedList<Integer>();
        int id = map.keySet().iterator().next();
        queue.offer(id);
        visited.add(id);
        
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            for (int next : map.get(now)) {
                if (visited.contains(next)) {
                    return false;
                }
                
                map.get(next).remove(now);
                visited.add(next);
                queue.offer(next);
            }
            
            // Can remove now from map? map.remove(now)
        }
        
        return n == visited.size();
    }
    public boolean validTreeUF(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        
        for (int[] edge : edges) {
            int p1 = uf.find(edge[0]);
            int p2 = uf.find(edge[1]);
            
            if (p1 == p2) {
                return false;
            }
            
            uf.union(p1, p2);
        }
        
        return n - 1 == edges.length;
    }
}

class UnionFind {
    int[] parent = null; // As a tree is connected, all nodes should point to a single parent eventually
    
    UnionFind(int n) {
        this.parent = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    int find(int id) {
        while (id != parent[id]) {
            id = parent[id];
        }
        return id;
    }
    
    void union(int id1, int id2) {
        int p1 = find(id1);
        int p2 = find(id2);
        
        if (p1 != p2) {
            parent[p1] = p2;
        }
    }
}
