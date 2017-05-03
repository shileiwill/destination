package company.facebook;

import java.util.HashSet;
import java.util.Set;

/**
 * interval [startTime, stoptime)   ----integral  time stamps
给这样的一串区间 I1, I2......In  
找出 一个 time stamp  出现在interval的次数最多。
startTime <= t< stopTime 代表这个数在区间里面出现过。
example：  [1,3),  [2, 7),   [4,  8),   [5, 9)
5和6各出现了三次， 所以答案返回5，6。  (Hard)
 */
public class IntegralTimeStamp {

	public static void main(String[] args) {
		Interval in1 = new Interval(1, 3);
		Interval in2 = new Interval(2, 7);
		Interval in3 = new Interval(4, 8);
		Interval in4 = new Interval(5, 9);
		
		Interval[] intervals = {in1, in2, in3, in4};
		
		IntegralTimeStamp its = new IntegralTimeStamp();
		its.findMinMax(intervals);
		
		int count = its.count(intervals);
		System.out.println("count : " + count);
		System.out.println("maxCount : " + its.maxCount);
		
		for (int val : its.set) {
			System.out.println(val);
		}
	}

	int min = Integer.MAX_VALUE;
	int max = 0;
	void findMinMax(Interval[] intervals) {
		for (Interval in : intervals) {
			min = Math.min(min, in.start);
			max = Math.max(max, in.end - 1);
		}
	}
	
	int maxCount = 0;
	Set<Integer> set = new HashSet<Integer>();
	int count(Interval[] intervals) {
		if (max - min + 1 < 1) {
			return 0;
		}
		
		int[] hash = new int[max - min + 1];
		for (Interval in : intervals) {
			for (int i = in.start; i < in.end; i++) {
				hash[i - min]++;
			}
		}
		
		int count = 0;
		for (int i = min; i <= max; i++) {
			if (hash[i - min] != 0) {
				count++;
			}
			
			if (hash[i - min] == maxCount) {
				set.add(i);
			} else if (hash[i - min] > maxCount) {
				maxCount = hash[i - min];
				set = new HashSet<Integer>();
				set.add(i);
			}
		}
		
		return count;
	}
}
class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
}