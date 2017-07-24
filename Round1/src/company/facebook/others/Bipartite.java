package Facebook;

import java.util.*;

/**
 * Created by weixwu on 7/2/2017.
 */
public class Bipartite {
	// G is a 2D matrix, G[i][j] == 1 meaning if i and j are in war
	public boolean isBipartite(int[][] G, int src) { 
		int n = G.length;
		int[] colors = new int[n];
		for (int i = 0; i < n; i++) {
			colors[i] = -1;
		}
		colors[src] = 1;
		Queue<Integer> q = new LinkedList<>();
		q.add(src);
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int nei = 0; nei < n; nei++) {
				if (G[cur][nei] == 0) { // These 2 points have no relationship
					continue;
				}
				
				if (colors[nei] == -1) { // Not initialized yet, will do
					colors[nei] = 1 - colors[cur]; // Set color
					q.offer(nei);
				} else if (colors[nei] == colors[cur]) {
					return false;
				}
			}
		}
		return true;
	}
}
