package company.facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Interval List A：<1,3> <5,7>
				 B: <4,6>.
输出交集 <5,6>
输出并集 <1,7> 要求是如果前一个interval的end是后一个的start-1要合并

维护双指针，但是最重要的原则是，比较两个指针but每次只移动一个指针
 */
public class MergeAndIntersectIntervals {

	public static void main(String[] args) {
		MergeAndIntersectIntervals i = new MergeAndIntersectIntervals();
		
		Interval in1 = new Interval(1, 3);
		Interval in2 = new Interval(5, 7);
		Interval in3 = new Interval(1, 2);
		Interval in4 = new Interval(2, 3);
		
		List<Interval> A = new ArrayList<Interval>();
		A.add(in1);
		A.add(in2);
		
		List<Interval> B = new ArrayList<Interval>();
		B.add(in3);
		B.add(in4);
		
		i.intersection(A, B);
	}

	List<Interval> intersection(List<Interval> A, List<Interval> B) {
		List<Interval> res = new ArrayList<Interval>();
		int pos1 = 0;
		int pos2 = 0;
		
		while (pos1 < A.size() && pos2 < B.size()) {
			Interval in1 = A.get(pos1);
			Interval in2 = B.get(pos2);
			
			if (in1.end < in2.start) {
				pos1++;
			} else if (in2.end < in1.start) {
				pos2++;
			} else {
				Interval in = new Interval(Math.max(in1.start, in2.start), Math.min(in1.end, in2.end));
				res.add(in);
				
				// Move the one which ends earlier. Great!!! 还是有问题啊
				if (in1.end < in2.end) {
					pos1++;
				} else {
					pos2++;
				}
			}
		}
		
		for (Interval in : res) {
			System.out.println(in.start + "==" + in.end);
		}
		
		return res;
	}
	
	List<Interval> union(List<Interval> A, List<Interval> B) {
		List<Interval> res = new ArrayList<Interval>();
		int pos1 = 1;
		int pos2 = 0;
		
		res.add(A.get(0));
		while (pos1 < A.size() && pos2 < B.size()) {
			Interval in1 = A.get(pos1);
			Interval in2 = B.get(pos2);
			Interval prev = res.get(res.size() - 1);
			
			// Use only 1 each time
			if (in1.start < in2.start) {
				if (prev.end + 1 < in1.start) {
					res.add(in1);
					pos1++;
				} else {
					prev.end = Math.max(prev.end, in1.end);
					pos1++;
				}
			} else {
				if (prev.end + 1 < in2.start) {
					res.add(in2);
					pos2++;
				} else {
					prev.end = Math.max(prev.end, in2.end);
					pos2++;
				}
			}
		}
		
		// Merge res and whichever left, A or B
		while (pos1 < A.size()) {
			Interval prev = res.get(res.size() - 1);
			Interval now = A.get(pos1);
			
			if (prev.end + 1 >= now.start) {
				prev.end = now.end;
			} else {
				res.add(now);
			}
			pos1++;
		}
		
		while (pos2 < B.size()) {
			Interval prev = res.get(res.size() - 1);
			Interval now = B.get(pos2);
			
			if (prev.end + 1 >= now.start) {
				prev.end = now.end;
			} else {
				res.add(now);
			}
			pos2++;
		}
		
		for (Interval in : res) {
			System.out.println(in.start + "==" + in.end);
		}
		
		return res;
	}
	
	/**
	 * 163. Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
	For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
	
	两个input：
1）desired time range to arrange a meeting -- TimeRange desired_time 
2）a list of busy intervals -- List<TimeRange> busy_intervals
. From 1point 3acres bbs
output: 
list of time ranges where a meeting can be scheduled, 会议没有时长限制 -- List<TimeRange>

class TimeRange {
    double start,
    double end
}

1. input: [9 - 14.5], [[8 - 10], [9 - 12.5], [14 - 15]] 
   output: [[12.5 - 14]]
2. input: [10 - 12], [[8 - 9], [13 - 14]]
    output: [[10 - 12]].
	 */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        
        long prev = lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            long cur = (i == nums.length) ? upper + 1 : nums[i];
            if (cur - prev >= 2) {
                res.add(getRange(prev + 1, cur - 1));
            }
            
            prev = cur;
        }
        
        return res;
    }
    
    String getRange(long start, long end) {
        return start == end ? start + "" : start + "->" + end;
    }
}
