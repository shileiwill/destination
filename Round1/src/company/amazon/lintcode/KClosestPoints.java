package company.amazon.lintcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given some points and a point origin in two dimensional space, find k points out of the some points which are nearest to origin.
Return these points sorted by distance, if they are same with distance, sorted by x-axis, otherwise sorted by y-axis.
Have you met this question in a real interview? 
Yes
Example
Given points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3
return [[1,1],[2,5],[4,4]]
 */
public class KClosestPoints {

	static class Point {
		     int x;
		     int y;
		     Point() { x = 0; y = 0; }
		     Point(int a, int b) { x = a; y = b; }
		}
	
	static class Wrapper {
	    Point p;
	    int distance;
	   
	    Wrapper(Point p, int distance) {
	        this.p = p;
	        this.distance = distance;
	    }
	}
    /**
     * @param points a list of points
     * @param origin a point
     * @param k an integer
     * @return the k closest points
     */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        // Write your code here
        if (points == null || points.length == 0 || k > points.length) {
            return new Point[]{};
        }
       
        Comparator<Wrapper> com = new Comparator<Wrapper>() {
            public int compare(Wrapper w1, Wrapper w2) {
                if (w1.distance == w2.distance) {
                    if (w1.p.x == w2.p.x) {
                        return w1.p.y - w2.p.y;
                    }
                    return w1.p.x - w2.p.x;
                } else {
                    return w1.distance - w2.distance;
                }
            }
        };
       
        PriorityQueue<Wrapper> heap = new PriorityQueue<Wrapper>(points.length, com);
        for (Point p : points) {
            int dis = (int)(Math.pow((p.x - origin.x), 2) + Math.pow((p.y - origin.y), 2));
           
            Wrapper w = new Wrapper(p, dis);
           
            heap.offer(w);
            // If heap.size() > k, we can poll here. This will guarantee only k elements there
        }
       
        Point[] res = new Point[k];
        int i = 0;
        while (i < k) {
            res[i++] = heap.poll().p;
        }
       
        return res;
    }
}
