package company.snapchat;

import java.util.PriorityQueue;

/**
407. Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.

This is very very similar to KthSmallestSum..in 2 sorted arrays
https://github.com/shawnfan/LintCode/blob/master/Java/Trapping%20Rain%20Water%20II.java
1. 从matrix四周开始考虑，发现matrix能Hold住的水，取决于height低的block。
2. 必须从外围开始考虑，因为水是被包裹在里面，外面至少需要现有一层。

以上两点就促使我们用min-heap: 也就是natural order的PriorityQueue<Cell>.
 *
 */
public class TrappingRainWater2 {
    class Cell implements Comparable<Cell>{
    	int x, y, h;
    	Cell(int x, int y, int h) {
    		this.x = x;
    		this.y = y;
    		this.h = h;
    	}
    	
    	public int compareTo(Cell another) {
    		return this.h - another.h;
    	}
    }
	public int trapRainWater(int[][] heightMap) {
		int rows = heightMap.length;
		int cols = heightMap[0].length;
		
        PriorityQueue<Cell> heap = new PriorityQueue<Cell>();
        boolean[][] visited = new boolean[rows][cols];
        
        // Left and Right
        for (int i = 0; i < rows; i++) {
        	visited[i][0] = true;
        	visited[i][cols - 1] = true;
        	heap.offer(new Cell(i, 0, heightMap[i][0]));
        	heap.offer(new Cell(i, cols - 1, heightMap[i][cols - 1]));
        }
        
        // Top and Bottom
        for (int i = 0; i < cols; i++) {
        	visited[0][i] = true;
        	visited[rows - 1][i] = true;
        	heap.offer(new Cell(0, i, heightMap[0][i]));
        	heap.offer(new Cell(rows - 1, i, heightMap[rows - 1][i]));
        }
        
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int sum = 0;
        while (!heap.isEmpty()) {
        	Cell now = heap.poll();
        	
        	for (int i = 0; i < 4; i++) {
        		int nextX = now.x + dx[i];
        		int nextY = now.y + dy[i];
        		
        		if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols && !visited[nextX][nextY]) {
        			visited[nextX][nextY] = true;
        			sum += Math.max(0, now.h - heightMap[nextX][nextY]);
        			heap.offer(new Cell(nextX, nextY, Math.max(now.h, heightMap[nextX][nextY])));
        		}
        	}
        }
        
        return sum;
    }
}
