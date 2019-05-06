package company.snapchat;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 有一个博物馆的map是N*N的二位矩阵，给了security guard坐标，还给了wall的坐标，问map的每个位置到最近guard的距离。
用BFS做。先把所有guard位置放到current positions里，然后针对每个position，上下左右移动，如果不是墙，也放到positions里，直到没有position为止
 * @author lei2017
 *
 */
public class SecurityGuardGraphBFS {

	public static void main(String[] args) {
		int[][] M = {
				{-2, 0, -2, 0},
				{0, -1, 0, 0},
				{0, -2, 0, 0},
				{0, 0, -1, 0},
		};
		
		SecurityGuardGraphBFS sg = new SecurityGuardGraphBFS();
		sg.guardWall(M);
	}

	// default is 0. wall is -1. guard is -2. Find out the closest distance to any guard from default(non-wall) 
	void guardWall(int[][] M) {
		int m = M.length;
		int n = M[0].length;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(M[i][j] == -2) {
					queue.offer(i * n + j); // Put all guards into queue
				}
			}
		}
		
		int level = 1;
		int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			for(int i = 0; i < size; i++) {
				int now = queue.poll();
				int x = now / n;
				int y = now % n;
				
				for(int[] dir : dirs) {
					int nextX = x + dir[0];
					int nextY = y + dir[1];
					
					if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && M[nextX][nextY] == 0) {
						M[nextX][nextY] = level;
						queue.offer(nextX * n + nextY);
					}
				}
			}
			
			level++;
		}
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(M[i][j] + ",");
			}
			System.out.println();
		}
	}
}
