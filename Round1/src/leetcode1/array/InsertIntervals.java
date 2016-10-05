package leetcode1.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their
 * start times.
 * 
 * Example 1: Given intervals [1,3],[6,9], insert and merge [2,5] in as
 * [1,5],[6,9].
 * 
 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in
 * as [1,2],[3,10],[12,16].
 * 
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertIntervals {

	// Program creek
	public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {

		List<Interval> res = new ArrayList<Interval>();

		if (intervals.size() == 0) {

			res.add(newInterval);

			return res;

		}

		// newInterval is moving

		for (int i = 0; i < intervals.size(); i++) {

			Interval cur = intervals.get(i);

			if (cur.end < newInterval.start) {

				res.add(cur);

			} else if (cur.start > newInterval.end) {

				res.add(newInterval);

				newInterval = cur;

			} else if (cur.start <= newInterval.end || cur.end >= newInterval.start) {

				newInterval = new Interval(Math.min(cur.start, newInterval.start), Math.max(cur.end, newInterval.end));

			}

		}

		res.add(newInterval);

		return res;

	}

	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

		List<Interval> res = new ArrayList<Interval>();

		int i = 0, size = intervals.size();

		// Add all intervals ending before newInterval starts

		while (i < size && intervals.get(i).end < newInterval.start) {

			res.add(intervals.get(i));

			i++;

		}

		while (i < size && intervals.get(i).start <= newInterval.end) { // Calculate
																		// overlapping
																		// intervals

			newInterval = new Interval(Math.min(newInterval.start, intervals.get(i).start),
					Math.max(newInterval.end, intervals.get(i).end)); // Just
																		// modify
																		// the
																		// newInterval
																		// variable,
																		// keep
																		// moving

			i++;

		}

		res.add(newInterval); // Add the union of intervals

		while (i < size) { // Add rest of them all

			res.add(intervals.get(i));

			i++;

		}

		return res;

	}

}