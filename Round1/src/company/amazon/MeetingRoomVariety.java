package company.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有关 schedule meeting ，得到所有人都有空的时间段
 * 
 * 假设 有 A B C 三个人，每个人的 schedule 不一样，找出可以把他们三个人凑一块的时间段。这是一个例子哈，问题没这么具体，得靠你自己一点点把边界和要求问清楚
 */
public class MeetingRoomVariety {

	public static void main(String[] args) {
		Interval m1 = new Interval(1, 2);
		Interval m2 = new Interval(1, 4);
		Interval m3 = new Interval(3, 5);
		Interval m4 = new Interval(6, 7);
		Interval m5 = new Interval(8, 10);
		Interval m6 = new Interval(2, 3);
//		Interval m7 = new Interval(3, 5);
		Interval m8 = new Interval(5, 6);
		
		Interval[] meetings = {m1, m2, m3, m4, m5, m6, m8};
		List<Interval> list = Arrays.asList(meetings);
		
		MeetingRoomVariety mrv = new MeetingRoomVariety();
		List<Interval> res = mrv.getAvailableIntervals(list);
		
		for (Interval in : res) {
			System.out.println(in.start + "====" + in.end);
		}
	}

	List<Interval> getAvailableIntervals(List<Interval> list) {
		int start = Integer.MAX_VALUE;
		int end = Integer.MIN_VALUE;
		
		for (Interval in : list) {
			start = Math.min(start, in.start);
			end = Math.max(end, in.end);
		}
		
		List<Interval> res = new ArrayList<Interval>();
		boolean[] occupied = new boolean[end - start + 1];
		
		for (Interval in : list) {
			for (int i = in.start; i < in.end; i++) { // Not include end point
				occupied[i - start] = true;
			}
		}
		
		int left = -1;
		for (int i = start; i < end;) { // Not include end point
			if (!occupied[i - start]) { // 发现起点 就顺着找终点
				left = i;
				
				int right = left + 1;
				while (right <= end && !occupied[right - start]) {
					right++;
				}
				
				res.add(new Interval(left, right));
				
				i = right;
			} else {
				i++;
			}
		}
		
		return res;
	}
	
	// Time complexity of above solution is N^2.
	// NLogN: Sort the list, Merge List to make sure there is no overlap. Then count
}
