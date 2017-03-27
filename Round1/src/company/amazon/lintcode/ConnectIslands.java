package company.amazon.lintcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ConnectIslands {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        UnionFind uf = new UnionFind(A);
        PriorityQueue<ArrayList<Integer>> heap = new PriorityQueue<ArrayList<Integer>>(10, new Comparator<ArrayList<Integer>>(){
            public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
                return list1.get(2) - list2.get(2);
            }
        });

        int cost = 0; 
        for (int i = 0; i < B.size(); i++) {
            ArrayList<Integer> list = B.get(i);
            heap.offer(list);
        }
        
        while (!heap.isEmpty()) {
            ArrayList<Integer> list = heap.poll();
            
            int id1 = list.get(0) - 1;
            int id2 = list.get(1) - 1;
            
            if (!uf.connected(id1, id2)) {
                uf.union(id1, id2);
                cost += list.get(2);
            }
        }
        
        return cost;
    }
    
    class UnionFind {
        int[] parent = null;
        
        UnionFind(int n) {
            parent = new int[n];
            
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        void union(int from, int to) {
            int id1 = find(from);
            int id2 = find(to);
            
            if (id1 != id2) {
                parent[id1] = id2;
            }
        }
        
        int find(int id) {
            while (id != parent[id]) {
                parent[id] = parent[parent[id]];
                id = parent[id];
            }
            
            return id;
        }
        
        boolean connected(int id1, int id2) {
            return find(id1) == find(id2);
        }
    }

}
