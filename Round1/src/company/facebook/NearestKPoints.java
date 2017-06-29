package company.facebook;
//重要
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
Solution 1 make heap of size K and collect points by minimal distance O(NLogK) complexity.

Solution 2: Take an array of size N and Sort by distance. Should be used QuickSort (Hoare modification). 
As answer take first K points. This is also NlogN complexity but it is possible optimize to approximate O(N). 
If skip sorting of unnecessary sub arrays. When you split array by 2 sub arrays you should take only array where Kth index located. 
complexity will be : N +N/2 +N/4 + ... = O(N).

Solution 3: search Kth element in result array and takes all point lesser then founded. Exists O(N) alghoritm, similar to search of median.

Notes: better use sqr of distance to avoid of sqrt operations, it will be greater faster if point has integer coordinates.

Solution 3 is quickselect algorithm. Store all the distances in an array. Find the index which gives the kth smallest element using method similar to quicksort (Chapter 9 CLRS), then element from index 0 to k-1 will give all the required k points
O(N) in solution 2 & 3 is average case complexity. Worst case is still O(NLogK) 
 */
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

	// Using Heap: N * KlogK
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
	
	// Another O(N) approach is using bucket sort
	public List<Point> findNearestPointsBucketSort(Point p0, int k, Point[] points) {
		Map<Point, Integer> map = new HashMap<Point, Integer>();
		
		int longest = 0;
		for (Point p : points) {
			map.put(p, getDistance(p, p0));
			longest = Math.max(longest, map.get(p));
		}
		
		List<Point>[] bucket = new List[longest + 1];
		
		for (Map.Entry<Point, Integer> entry : map.entrySet()) {
			Point p = entry.getKey();
			int distance = entry.getValue();
			
			if (bucket[distance] == null) {
				bucket[distance] = new ArrayList<Point>();
			}
			
			bucket[distance].add(p);
		}
		
		List<Point> res = new ArrayList<Point>();
		for (int i = 0; i < bucket.length && res.size() < k; i++) {
			if (bucket[i] != null) {
				res.addAll(bucket[i]); // 有可能会超了K. 输出所有大于等于top k频率的元素呢，还是只要取top k个就可以了
			}
		}
		
		return res;
	}
	
	/**
	 * Quickselect uses the same overall approach as quicksort, 
	 * choosing one element as a pivot and partitioning the data in two based on the pivot, 
	 * accordingly as less than or greater than the pivot. However, instead of recursing into both sides, 
	 * as in quicksort, quickselect only recurses into one side – the side with the element it is searching for. 
	 * This reduces the average complexity from O(n log n) to O(n), with a worst case of O(n2).
	 * Time complexity of quick sort, worst case is O(n2). Merge sort worst case is O(nlogn)
	 */
	public Point findNearestKthPoint(Point[] points, Point p, int k) {
		return helper(points, p, k - 1, 0, points.length - 1);
		
		// If want to find the k points, just use a for loop, searching for index 0 to k - 1
		// Above statement is wrong, rather than get one by one, why not just make first K sorted in one shot
	}
	
	public List<Point> findNearestAllKPoints(Point[] points, Point p, int k) {
		quickSelect(points, p, k);
		
		List<Point> res = new ArrayList<Point>();
		for (int i = 0; i < k; i++) {
			res.add(points[i]);
		}
		
		return res;
	}
	
	// 貌似helper跟quickSelect没啥区别， 一个用的是递归，一个用的迭代， 只要找到了K,左边的都是小于，右边都大于，没差
	Point helper(Point[] points, Point p, int k, int left, int right) {
		int pos = partition(points, p, left, right);
		
		if (pos == k) {
			return points[k];
		} else if (pos > k) {
			return helper(points, p, k, left, pos - 1);
		} else {
			return helper(points, p, k, pos + 1, right);
		}
	}
	
	private void quickSelect(Point[] points, Point p, int k) {
		int left = 0, right = points.length - 1;
		
		while (left < right) {
			int pos = partition(points, p, left, right);
			
			if (pos == k) { // 当是k的时候， 左边的全都是小于points[k]的
				return;
			} else if (pos < k) {
				left = pos + 1;
			} else {
				right = pos - 1;
			}
		}
	}
	
	int partition(Point[] points, Point p, int left, int right) {
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