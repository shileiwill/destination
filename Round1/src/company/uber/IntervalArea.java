package company.uber;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// Interval has height, get the area
// 参考SweepingLineBuildingOutline 其实就是找建筑的面积
public class IntervalArea {

	public static void main(String[] args) {
		Interval in1 = new Interval(0, 2, 1);
		Interval in2 = new Interval(2, 3, 2);
		Interval in3 = new Interval(3, 8, 3);
		Interval in4 = new Interval(5, 6, 1);
		Interval in5 = new Interval(7, 9, 4);
		Interval in6 = new Interval(7, 11, 1);
		
		Interval[] arr = {in1, in2, in3, in4, in5, in6};
		List<Interval> list = Arrays.asList(arr);
		
		int area = getAreaHeap(list);
		System.out.println(area);
	}
	
	static int getArea(List<Interval> list) {
		Collections.sort(list);
		
		int area = 0;
		int start = list.get(0).start;
		int end = list.get(0).end;
		int height = list.get(0).height;
		
		for (int i = 1; i < list.size(); i++) {
			Interval now = list.get(i);
			
			if (now.start >= end) { // No connection
				area += (end - start) * height;
				start = now.start;
				end = now.end;
				height = now.height;
			} else if (now.end > end) { // 没有完全覆盖
				if (now.height >= height) {
					area += (now.start - start) * height;
					start = now.start;
					end = now.end;
					height = now.height;
				} else { // 有问题
					area += (end - start) * height;
					start = end;
					end = now.end;
					height = now.height;
				}
			} else { // 完全覆盖住了
				if (now.height > height) {
					area += (now.start - now.end) * (now.height - height); // 这里有问题
				} else {
					// Just ignore since it is fully covered
				}
			}
		}
		
		area += (end - start) * height;
		
		return area;
	}
	
	static int getAreaHeap(List<Interval> list) {
		PriorityQueue<Interval> heap = new PriorityQueue<Interval>(list.size());
		for (Interval in : list) {
			heap.offer(in);
		}
		
		int area = 0;
		Integer start = null;
		Integer end = null;
		Integer height = null;
		
		while (!heap.isEmpty()) {
			if (start == null) {
				Interval first = heap.poll();
				start = first.start;
				end = first.end;
				height = first.height;
				continue;
			}
			
			Interval now = heap.poll();
			
			if (now.start >= end) { // No connection
				area += (end - start) * height;
				start = now.start;
				end = now.end;
				height = now.height;
			} else if (now.end > end) { // 没有完全覆盖
				if (now.height >= height) {
					area += (now.start - start) * height;
					start = now.start;
					end = now.end;
					height = now.height;
				} else { // Doesnt work here
					area += (now.start - start) * height;
					
					Interval newInterval = new Interval(now.start, end, height);
					heap.offer(now);
					heap.offer(newInterval);
					
					start = null;
					end = null;
					height = null;
				}
			} else { // 完全覆盖住了
				if (now.height > height) {
					area += (now.start - start) * height;
					
					Interval newInterval = new Interval(now.end, end, height);
					heap.offer(newInterval);

					start = now.start;
					end = now.end;
					height = now.height;
				} else {
					// Just ignore since it is fully covered
				}
			}
		}
		
		area += (end - start) * height;
		return area;
	}

	static class Interval implements Comparable<Interval> {
		int start, end, height;
		
		Interval(int start, int end, int height) {
			this.start = start;
			this.end = end;
			this.height = height;
		}
		
		public int compareTo(Interval that) {
			if (this.start != that.start) {
				return this.start - that.start;
			}
			return this.end - that.end;
		}
	}
}
