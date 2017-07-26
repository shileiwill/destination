package company.facebook.others;

import java.util.*;

/**
 * Created by weixwu on 7/2/2017.
 * How to do a BFS in 2D matrix
 */
public class Bipartite {
	// G is a 2D matrix, G[i][j] == 1 meaning if i and j are in war 二维矩阵表示N个人相互之间的关系
	public boolean isBipartite(int[][] matrix, int src) {
		int len = matrix.length;
		Boolean[] hash = new Boolean[len];
		hash[src] = true; // 颜色是true, false
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(src);
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int nei = 0; nei < len; nei++) { // 跟所有人比较
				if (matrix[cur][nei] == 0) { // These 2 points have no relationship
					continue;
				}
				
				if (hash[nei] == null) { // Not initialized yet, will do
					hash[nei] = !hash[cur]; // Set color
					queue.offer(nei);
				} else if (hash[nei] == hash[cur]) {
					return false;
				} // If cur and neighbor are with 2 different colors, it is good
			}
		}
		
		return true;
	}
}
