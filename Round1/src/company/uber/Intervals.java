package company.uber;

import java.util.ArrayList;
import java.util.List;

/**
 * Interval List A：<1,3> <5,7>
				 B: <4,6>.
输出交集 <5,6>
输出并集 <1,7> 要求是如果前一个interval的end是后一个的start-1要合并。多谢中国大哥放水。

电面二
给List<List<Integer>> 找出所有在每一个里都取一个integer的组合。recursive写完要求写了iterative没写完
 */
public class Intervals {

	public static void main(String[] args) {
		Intervals i = new Intervals();
		
		Interval in1 = new Interval(1, 3);
		Interval in2 = new Interval(5, 7);
		Interval in3 = new Interval(4, 6);
		
		List<Interval> A = new ArrayList<Interval>();
		A.add(in1);
		A.add(in2);
		
		List<Interval> B = new ArrayList<Interval>();
		B.add(in3);
		
		i.together(A, B);
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
				pos1++;
				pos2++;
			}
		}
		
		for (Interval in : res) {
			System.out.println(in.start + "==" + in.end);
		}
		
		return res;
	}
	
	List<Interval> together(List<Interval> A, List<Interval> B) {
		List<Interval> res = new ArrayList<Interval>();
		int pos1 = 0;
		int pos2 = 0;
		
		while (pos1 < A.size() && pos2 < B.size()) {
			Interval in1 = A.get(pos1);
			Interval in2 = B.get(pos2);
			
			if (in1.end < in2.start) {
				if (in1.end + 1 == in2.start) {
					Interval in = new Interval(in1.start, in2.end);
					res.add(in);
					pos1++;
					pos2++;
				} else {
					res.add(in1);
					pos1++;
				}
			} else if (in2.end < in1.start) {
				if (in2.end + 1 == in1.start) {
					Interval in = new Interval(in2.start, in1.end);
					res.add(in);
					pos1++;
					pos2++;
				} else {
					res.add(in2);
					pos2++;
				}
			} else { // 有交集
				Interval in = new Interval(Math.min(in1.start, in2.start), Math.max(in1.end, in2.end));
				res.add(in);
				pos1++;
				pos2++;
			}
		}
		
		// Merge res and whichever left, A or B
		
		for (Interval in : res) {
			System.out.println(in.start + "==" + in.end);
		}
		
		return res;
	}
}
