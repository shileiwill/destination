package leetcode4.hashtable;

import java.util.HashSet;
import java.util.Set;
/**
 * 356. Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

Hint:

Find the smallest and largest x-value for all points.
If there is a line then it should be at y = (minX + maxX) / 2.
For each point, make sure that it has a reflected point in the opposite side.
 */
public class LineReflection {
    public boolean isReflected2(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        Set<String> set = new HashSet<String>();
        
        for (int i = 0; i < points.length; i++) {
            max = Math.max(max, points[i][0]);
            min = Math.min(min, points[i][0]);
            
            String xy = points[i][0] + ":" + points[i][1];
            set.add(xy);
        }
        
        // 关于Y轴对称，X的和总相等
        int sum = max + min;
        for (int i = 0; i < points.length; i++) {
            int anotherX = sum - points[i][0];
            String anotherXy = anotherX + ":" + points[i][1];
            if (!set.contains(anotherXy)) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        Set<Point> set = new HashSet<Point>();
        
        for (int i = 0; i < points.length; i++) {
            max = Math.max(max, points[i][0]);
            min = Math.min(min, points[i][0]);
            
            Point p = new Point(points[i][0], points[i][1]);
            set.add(p);
        }
        
        int sum = max + min;
        for (int i = 0; i < points.length; i++) {
            int anotherX = sum - points[i][0];
            Point p = new Point(anotherX, points[i][1]);
            set.remove(p);
        }
        
        return set.isEmpty();
    }
}

class Point {
    int x, y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object o) {
        Point p = (Point)o;
        return p.x == x && p.y == y;
    }
    
    @Override
    public int hashCode() {
        return x * 31 + y * 17;
    }
}