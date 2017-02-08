package amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ExistPath {

	public static void main(String[] args) {
		int[][] matrix = {  {0,1,1,1,0},
                			{0,0,0,0,0},
                			{1,0,0,0,1},
                			{0,0,1,0,0},
                			{0,0,1,1,0} };
		int[] from = {0, 0};
		int[] to = {4, 4};
		
		ExistPath ep = new ExistPath();
//		ep.bfs(matrix, from, to);
		ep.pathExist(matrix, from, to);
		
	}

	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	void bfs(int[][] matrix, int[] from, int[] to) {
		int m = matrix.length;
		int n = matrix[0].length;
		
		int idFrom = from[0] * n + from[1];
		int idTo = to[0] * n + to[1];
		
		Set<Integer> visited = new HashSet<Integer>();
		visited.add(idFrom);
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(idFrom);
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				int id = queue.poll();
				int x = id / n;
				int y = id % n;
				
				for (int[] dir : directions) {
					int newX = x + dir[0];
					int newY = y + dir[1];
					int newId = newX * n + newY;
					
					if (newId == idTo) {
						map.put(newId, id);
						break; // Found
					}
					
					if (newX >= 0 && newX < m && newY >= 0 && newY < n && matrix[newX][newY] == 0 && !visited.contains(newId)) {
						visited.add(newId);
						queue.offer(newId);
						map.put(newId, id);
					}
				}
			}
		}
		
		List<Integer> path = new ArrayList<Integer>();
		int start = idTo;
		int end = idFrom;
		if (!map.containsKey(start)) {
			System.out.println("No Path Exist, Return");
			return;
		}
		
		while (map.get(start) != end) {
			path.add(0, start);
			start = map.get(start);
		}
		path.add(0, start);
		path.add(0, end);
		
		for (int val : path) {
			System.out.println((val / n) + "==" + (val % n));
		}
	}
	
	void pathExist(int[][] matrix, int[] from, int[] to) {
		int m = matrix.length;
		int n = matrix[0].length;
		
		int idFrom = from[0] * n + from[1];
		int idTo = to[0] * n + to[1];
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		visited.add(idFrom);
		list.add(idFrom);
		
		dfs(matrix, idFrom, idTo, list, res, visited);
		
		for (List<Integer> l : res) {
			for (int val : l) {
				System.out.println((val / n) + "==" + (val % n));
			}
			System.out.println();
		}
	}
	
	void dfs(int[][] matrix, int idFrom, int idTo, List<Integer> list, List<List<Integer>> res, Set<Integer> visited) {
		if (idFrom == idTo) { // Found
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		int m = matrix.length;
		int n = matrix[0].length;
		int x = idFrom / n;
		int y = idFrom % n;
		
		for (int[] dir : directions) {
			int newX = x + dir[0];
			int newY = y + dir[1];
			int newId = newX * n + newY;
			
			if (newX >= 0 && newX < m && newY >= 0 && newY < n && matrix[newX][newY] == 0 && !visited.contains(newId)) {
				visited.add(newId);
				list.add(newId);
				dfs(matrix, newId, idTo, list, res, visited);
				list.remove(list.size() - 1);
				visited.remove(newId);
			}
		}
		
	}
}
