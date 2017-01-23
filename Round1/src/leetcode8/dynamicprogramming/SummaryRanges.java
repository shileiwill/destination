package leetcode8.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 352. Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]
Follow up:
What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
 */
public class SummaryRanges {
    // http://www.programcreek.com/2014/08/leetcode-data-stream-as-disjoint-intervals-java/
    // https://discuss.leetcode.com/topic/46887/java-solution-using-treemap-real-o-logn-per-adding
    // Key is the start of Interval
    TreeMap<Integer, Interval> tree = null;
    
    /** Initialize your data structure here. */
    // public SummaryRanges() {
    //     tree = new TreeMap<Integer, Interval>();
    // }
    
    public void addNum2(int val) {
        if (tree.containsKey(val)) {
            return;
        }
        
        // lowKey is either null or must be lower than val
        // highKey is either null or must be higher than val
        Integer lowKey = tree.lowerKey(val);
        Integer highKey = tree.higherKey(val);
    
        if ((lowKey != null) && (highKey != null) && (tree.get(lowKey).end + 1 == val) && (highKey == val + 1)) {
            tree.get(lowKey).end = tree.get(highKey).end;
            tree.remove(highKey);
        } else if (lowKey != null && tree.get(lowKey).end + 1 >= val) {
            tree.get(lowKey).end = Math.max(tree.get(lowKey).end, val);
        } else if (highKey != null && highKey == val + 1) {
            tree.put(val, new Interval(val, tree.get(highKey).end));
            tree.remove(highKey);
        } else {
            tree.put(val, new Interval(val, val));
        }
    }
    
    public List<Interval> getIntervals2() {
        return new ArrayList<Interval>(tree.values());
    }
    
    // Using TreeSet
    TreeSet<Interval> set = null;
    
    public SummaryRanges() {
        set = new TreeSet<Interval>(new IntervalComp());
    }
    
    class IntervalComp implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
        }
    }
    public void addNum(int val) {
        if (set.contains(val)) {
            return;
        }
        
        Interval in = new Interval(val, val);
        
        Interval floor = set.floor(in);
        if (floor != null) {
            if (val <= floor.end) {
                return;
            }
            if (val == floor.end + 1) {
                in.start = floor.start;
                set.remove(floor);
            }
        }
        
        // Dont use ceiling here, as ceiling, floor will include the equal value if it exists
        Interval higher = set.higher(in);
        if (higher != null) {
            if (val + 1 == higher.start) {
                in.end = higher.end;
                set.remove(higher);
            }
        }
        
        set.add(in);
    }
    
    public List<Interval> getIntervals() {
        return Arrays.asList(set.toArray(new Interval[0]));
    }
    
    // Definition for an interval.
    class Interval {
         int start;
         int end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */