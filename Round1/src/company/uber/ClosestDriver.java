package company.uber;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 一个abc面的。题目是design an algo to find out the closest driver to a passenger。 
 * 大意是，assume a city has a lot of intersections, a passenger can call a car at any intersection. 
 * 假设每段intersection之间的距离都是500米，求找出离passenger最近的driver。如果5000米内没有driver，就return 0. 所有class 都自己define的。 
 */
public class ClosestDriver {

	public static void main(String[] args) {

	}

	int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	// Matrix[x][y] == 1 means there is a driver. Passenger is the passenger's position
	int findClosestDriver(int[][] matrix, int[] passenger) {
		// Do a BFS to find the closest
		int m = matrix.length;
		int n = matrix[0].length;
		Queue<Integer> queue = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		int start = passenger[0] * n + passenger[1];
		queue.offer(start);
		visited.add(start);
		
		if (matrix[passenger[0]][passenger[1]] == 1) {
			return -1; // The distance is 0. right there
		}
		
		int level = 1;
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
					
					if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited.contains(newId)) {
						if (matrix[newX][newY] == 1) {
							return level;
						}
						
						queue.offer(newId);
						visited.add(newId);
					}
				}
			}
			
			level++;
			
			if (level > 10) {
				return 0; // > 5000 m
			}
		}
		
		return -1;
	}
}
