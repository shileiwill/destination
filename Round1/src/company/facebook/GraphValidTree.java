package company.facebook;
/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
 * write a function to check whether these edges make up a valid tree.

 Notice

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and 
thus will not appear together in edges.

Example
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
// 261   3 ways: Topological Sort, DFS/BFS, Union Find
public class GraphValidTree {

    // Solution 1: BFS. Remember this BFS　approach from Jiuzhang 因为是无向图，所以不care环？
    public boolean validTree(int n, int[][] edges) {
        if (edges.length == 0) {
            return n == 1;
        }
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>(); // With Map, I can easily get its children
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
        int id = map.keySet().iterator().next(); // 随便提溜出一个来都能当root
        queue.offer(id);
        visited.add(id);
        
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            for (int next : map.get(now)) {
                if (visited.contains(next)) {
                    return false; // 保证无环
                }
                
                map.get(next).remove(now); // 一定要remove！
                visited.add(next);
                queue.offer(next);
            }
            
            map.remove(now);
        }
        
        return n == visited.size(); // It should cover all, eventually， 保证不落单
    }

	static class Node {
		int val;
		Set<Node> friends;
	}
	
	// Use topological sort, not sure if this solves the problem
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
		// Yes, still need to deal with circle
		return true;
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
