package leetcode7.binarysearch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * 436. Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

Note:
You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.
Example 1:
Input: [ [1,2] ]

Output: [-1]

Explanation: There is only one interval in the collection, so it outputs -1.
Example 2:
Input: [ [3,4], [2,3], [1,2] ]

Output: [-1, 0, 1]

Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.
Example 3:
Input: [ [1,4], [2,3], [3,4] ]

Output: [-1, 2, -1]

Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.
 */
public class FindRightInterval {
    public int[] findRightIntervalBinarySearch(Interval[] intervals) {
        Comparator<Interval> com = new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        };
        
        HashMap<Interval, Integer> map = new HashMap<Interval, Integer>();
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i], i);
        }
        
        Arrays.sort(intervals, com);
        int[] res = new int[intervals.length];
        
        for (int i = 0; i < intervals.length; i++) {
            int left = 0, right = intervals.length - 1;
            
            boolean perfectMatch = false;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (intervals[mid].start == intervals[i].end) {
                    perfectMatch = true;
                    res[map.get(intervals[i])] = map.get(intervals[mid]);
                    break;
                } else if (intervals[mid].start < intervals[i].end) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            
            if (!perfectMatch) {
                if (intervals[left].start >= intervals[i].end) {
                    res[map.get(intervals[i])] = map.get(intervals[left]);
                } else if (intervals[right].start >= intervals[i].end) {
                    res[map.get(intervals[i])] = map.get(intervals[right]);
                } else {
                    res[map.get(intervals[i])] = -1;
                }
            }
        }
        
        return res;
    }
    
    public int[] findRightInterval(Interval[] intervals) {
        TreeMap<Integer, Integer> tree = new TreeMap<Integer, Integer>();
        int[] res = new int[intervals.length];
        
        for (int i = 0; i < intervals.length; i++) {
            tree.put(intervals[i].start, i); // As said, start point is unique. Use start point to record the initial index
        }
        
        for (int i = 0; i < intervals.length; i++) {
            Integer ceilStart = tree.ceilingKey(intervals[i].end);
            if (ceilStart == null) {
                res[i] = -1;
            } else {
                res[i] = tree.get(ceilStart);
            }
        }
        
        return res;
    }
    
    private class Interval {
    	    int start;
    	    int end;
    	    Interval() { start = 0; end = 0; }
    	    Interval(int s, int e) { start = s; end = e; }
    }
}
