package leetcode18.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note:
You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]

Output: 1

Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:
Input: [ [1,2], [1,2], [1,2] ]

Output: 2

Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:
Input: [ [1,2], [2,3] ]

Output: 0

Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
public class NonOverlappingIntervals {

    /*
    Actually, the problem is the same as "Given a collection of intervals, find the maximum number of intervals that are non-overlapping." (the classic Greedy problem: Interval Scheduling).

    Sorting Interval.end in ascending order is O(nlogn), then traverse intervals array to get the maximum number of non-overlapping intervals is O(n). Total is O(nlogn).
    */
    public int eraseOverlapIntervals2(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.end - b.end; // Sort by end point
            }
        });
        
        int count = 1, end = intervals[0].end;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= end) {
                count++;
                end = intervals[i].end;
            }
        }
        
        return intervals.length - count;
    }
    
    // https://discuss.leetcode.com/topic/65828/java-solution-with-clear-explain
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                if (a.end != b.end) {
                    return a.end - b.end; // Sort by end point first
                }
                return a.start - b.start;
            }
        });
        
        int count = 0;
        int end = Integer.MIN_VALUE;
        
        for (Interval in : intervals) {
            if (in.start < end) {
                count++; // Overlap
            } else {
                end = in.end; // A new interval without lap
            }
        }
        
        return count;
    }

}
