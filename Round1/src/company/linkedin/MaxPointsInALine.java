package company.linkedin;

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
/**
 * 149. Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * 
 * new
 */
public class MaxPointsInALine {
    public int maxPoints(Point[] points) {
        if (points.length <= 2) {
            return points.length;
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
            int overlap = 0, max = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[i].x - points[j].x;
                int y = points[i].y - points[j].y;
                
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }
                // 计算最大公因子， X, Y 分别除以最大公因子 实际上是找出了唯一的表示斜率的分数， 小数不准确，如果不约分的话，也没法唯一表示
                int gcd = getGreatestCommonDivisor(x, y);
                
                // gcd can't be 0
                x = x / gcd;
                y = y / gcd;
                
                // Check map history
                if (map.containsKey(x)) {
                    Map<Integer, Integer> yMap = map.get(x);
                    yMap.put(y, yMap.getOrDefault(y, 0) + 1);
                    map.put(x, yMap);
                } else {
                    Map<Integer, Integer> yMap = new HashMap<Integer, Integer>();
                    yMap.put(y, 1);
                    map.put(x, yMap);
                }
                
                max = Math.max(max, map.get(x).get(y));
            }
            
            // overlap must be used outside of the inner loop
            res = Math.max(res, max + overlap + 1);
        }
        
        return res;
    }
    
    private int getGreatestCommonDivisor(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return getGreatestCommonDivisor(b, a % b);
        }
    }
}