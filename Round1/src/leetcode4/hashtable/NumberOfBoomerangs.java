package leetcode4.hashtable;

import java.util.HashMap;
import java.util.Map;
/**
 * 447. Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 */
public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                
                int distance = getDistance(points[i], points[j]);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }
            
            for (int size : map.values()) { // map.keySet(), map.values()
                // Find 2 elements from size, combination, but sequenece matters, so mutiply 2. 
                res += size * (size - 1); // If there is only 1 element, which means size is only 1, then add 0.
            }
        }
        
        return res;
    }
    
    int getDistance(int[] p, int[] q) {
        int x = p[0] - q[0];
        int y = p[1] - q[1];
        
        // No need to sqrt
        return x * x + y * y;
    }
}