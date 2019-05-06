package company.snapchat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 56. Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
 */
public class MergeIntervals {

	public List<Interval> merge(List<Interval> intervals) {

		List<Interval> res = new ArrayList<Interval>();

		if (intervals.size() == 0) {

			return res;

		}

		PriorityQueue<Interval> heap = new PriorityQueue<Interval>(new IntervalComparator());

		for (int i = 0; i < intervals.size(); i++) {

			heap.offer(intervals.get(i));

		}

		Interval cur = heap.poll();

		while (!heap.isEmpty()) {

			Interval next = heap.poll();

			if (next.start <= cur.end) { // Overlap
				// No need to offer back
				cur = new Interval(cur.start, Math.max(cur.end, next.end)); 

			} else { // Gap
				res.add(cur);
				cur = next;
			}

		}

		res.add(cur);

		return res;

	}

	class IntervalComparator implements Comparator<Interval> {

		public int compare(Interval i1, Interval i2) {

			if (i1.start == i2.start) {

				return i1.end - i2.end;

			}

			return i1.start - i2.start;

		}

	}
}

class Interval {

	int start;

	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}