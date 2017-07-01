package company.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
/**
 * Given N buildings in a x-axis，each building is a rectangle and can be represented by a triple (start, end, height)，where start is the start position on x-axis, end is the end position on x-axis and height is the height of the building. Buildings may overlap if you see them from far away，find the outline of them。

An outline can be represented by a triple, (start, end, height), where start is the start position on x-axis of the outline, end is the end position on x-axis and height is the height of the outline.

Building Outline

 Notice

Please merge the adjacent outlines if they have the same height and make sure different outlines cant overlap on x-axis.

Have you met this question in a real interview? Yes
Example
Given 3 buildings：

[
  [1, 3, 3],
  [2, 4, 4],
  [5, 6, 1]
]
The outlines are：

[
  [1, 2, 3],
  [2, 4, 4],
  [5, 6, 1]
]
 */
public class SweepingLineBuildingOutline {

	public static void main(String[] args) {
		int[][] buildings = {
				{1, 3, 3},
				{2, 4, 4},
				{3, 5, 5},
				{5, 6, 1},
		};
		
		SweepingLineBuildingOutline sl = new SweepingLineBuildingOutline();
		List<int[]> res = sl.getSkyline(buildings);
		
		for (int[] arr : res) {
			System.out.println(arr[0] + " : " + arr[1]);
		}
		
		System.out.println("=========================");
		
		List<int[]> res2 = new ArrayList<int[]>();
		// What if we want to get [start, end, height]
		for (int i = 0; i < res.size() - 1; i++) {
			int[] pair1 = res.get(i);
			int[] pair2 = res.get(i + 1);
			
			if (pair1[1] != 0) {
				res2.add(new int[]{pair1[0], pair2[0], pair1[1]});
			}
		}
		
		for (int[] arr : res2) {
			System.out.println(arr[0] + " : " + arr[1] + " : " + arr[2]);
		}
	}

	class PointHeight implements Comparable<PointHeight> {
		int x;
		int height;
		
		PointHeight(int x, int height) {
			this.x = x;
			this.height = height;
		}
		
		public int compareTo(PointHeight that) {
			if (this.x != that.x) {
				return this.x - that.x;
			}
			
			return this.height - that.height; // Start point will be first
		}
	}
	
	List<int[]> getSkyline(int[][] buildings) {
		List<int[]> res = new ArrayList<int[]>();
		
		List<PointHeight> list = new ArrayList<PointHeight>();
		for (int[] building : buildings) {
			int start = building[0];
			int end = building[1];
			int height = building[2];
			
			PointHeight point1 = new PointHeight(start, -height);
			PointHeight point2 = new PointHeight(end, height);
			
			list.add(point1);
			list.add(point2);
		}
		
		Collections.sort(list);
		
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(Collections.reverseOrder()); // Max Heap, get highest point
		heap.offer(0); // 最矮最矮就是0
		int prev = heap.peek();
		
		
		for (PointHeight point : list) {
			if (point.height < 0) {
				heap.offer(-point.height);
			} else {
				heap.remove(point.height);
			}
			
			int curPeak = heap.peek();
			if (curPeak != prev) {
				res.add(new int[]{point.x, curPeak});
				prev = curPeak;
			}
		}
		
		return res;
	}
}
