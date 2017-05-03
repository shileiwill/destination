package company.facebook;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class NearestKPoints {

	public static void main(String[] args) {
		Point p0 = new Point(0, 0);
		
		Point p1 = new Point(1, 1);
		Point p2 = new Point(2, 2);
		Point p3 = new Point(-1, 2);
		Point p4 = new Point(1, -2);
		Point p5 = new Point(5, 2);
		Point p6 = new Point(1, 3);
		Point p7 = new Point(3, 1);
		Point p8 = new Point(-2, 1);
		
		Point[] points = {p1, p2, p3, p4, p5, p6, p7, p8};
		
		NearestKPoints nkp = new NearestKPoints();
//		List<Point> res = nkp.findNearestPoints(p0, 3, points);
		
//		for (Point p : res) {
//			System.out.println(p.x + "==" + p.y);
//		}
		
		Point p = nkp.findNearestKthPoint(points, p0, 2);
		System.out.println(p.x + "==" + p.y);
	}

	public List<Point> findNearestPoints(Point p, int k, Point[] points) {
		PriorityQueue<Point> heap = new PriorityQueue<Point>(k + 1, new Comparator<Point>(){
			public int compare(Point p1, Point p2) {
				return getDistance(p, p2) - getDistance(p, p1); // Pop out the farthest first
			}
		});
		
		for (Point point : points) {
			heap.offer(point); // Always add
			if (heap.size() == k + 1) {
				heap.poll(); // Throw the farthest
			}
		}
		
		List<Point> res = new ArrayList<Point>();
		while (!heap.isEmpty()) {
			res.add(heap.poll());
		}
		
		return res;
	}
	
	public Point findNearestKthPoint(Point[] points, Point p, int k) {
		return helper(points, p, k - 1, 0, points.length - 1);
	}
	
	Point helper(Point[] points, Point p, int k, int left, int right) {
		int pos = partition(points, p, k, left, right);
		
		if (pos == k) {
			return points[k];
		} else if (pos > k) {
			return helper(points, p, k, left, pos - 1);
		} else {
			return helper(points, p, k, pos + 1, right);
		}
	}
	
	int partition(Point[] points, Point p, int k, int left, int right) {
		int pivot = left;
		
		while (left < right) {
			while (left < right && getDistance(points[left], p) <= getDistance(points[pivot], p)) {
				left++;
			}
			
			while (left < right && getDistance(points[right], p) > getDistance(points[pivot], p)) {
				right--;
			}
			
			swap(points, left, right);
		}
		
		if (getDistance(points[left], p) <= getDistance(points[pivot], p)) {
			swap(points, left, pivot);
			return left;
		} else {
			swap(points, pivot, left - 1);
			return left - 1;
		}
	}
	
	void swap(Point[] points, int left, int right) {
		Point tmp = points[left];
		points[left] = points[right];
		points[right] = tmp;
	}
	
	int getDistance(Point p1, Point p2) {
		return (int)(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}
}

class Point {
	int x, y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}