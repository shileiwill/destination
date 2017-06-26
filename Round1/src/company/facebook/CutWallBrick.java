package company.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试官是个白人，听口音像东欧来的。问题是，有一个wall，然后宽度和长度都固定。这个wall呢，用一个vector<vector<int>> wall表示，
 * 每一行代表一行砖，每块砖的宽度不固定。然后wall代表砖的宽度。
然后在墙上画线的话会cross这些砖，问你怎么画线可以cross的砖数最少(如果画的线正好在砖的一条边上不算cross)，
返回这个最少的砖数就行，还问了时间和空间复杂度。
follow up是这面墙的高度很小，但是宽度很大，每一行可能有特别特别多的砖，问你怎么办。

只需要有墙高度个数的pointer就可以了，比较当前指向的值有没有overlap，然后increment最小的那个

第三轮那道题就是从上到下画一条直线，其实就是要找到最多的砖块的边是上下连通在一起的，沿着这样的边画线不算cross砖块。
 */
public class CutWallBrick {

	public static void main(String[] args) {

	}

	int minCut(int[][] matrix) {
		// Cutting point, from left start point, Count
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int minCut = matrix.length;
		for (int i = 0; i < matrix.length; i++) {
			int sum = 0;
			for (int j = 0; j < matrix[i].length; j++) {
				sum += matrix[i][j];
				
				if (!map.containsKey(sum)) {
					map.put(sum, i); //之前不存在这个切口，如果从这里切，上边的所有层都得经过
				}
			}
		}
		
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			minCut = Math.min(minCut, entry.getValue());
		}
		
		return minCut;
	}
	
	// 这个是不是更好理解？
	int minCutBetter(int[][] matrix) {
		// Cutting point, from left start point, Count
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int max = 0;
		for (int i = 0; i < matrix.length; i++) {
			int sum = 0;
			for (int j = 0; j < matrix[i].length; j++) {
				sum += matrix[i][j];
				
				map.put(sum, map.getOrDefault(sum, 0) + 1);
				max = Math.max(max, map.get(sum));
			}
		}
		
		return matrix.length - max;
	}
}
