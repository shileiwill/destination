package amazon;

import java.util.ArrayList;
import java.util.List;
/**
 * 把一串连续的整数表示为一个range, range包含begin和end.比如3,4,5,6就表示为 begin=3,end=6的range

一个排好序的整数数组,返回所有的range. 比如1,2,3,5,7,8,10返回[1,3],[5,5],[7,8],[10,10] follow up是用迭代器实现
 */
public class ArrayToInterval {

	public static void main(String[] args) {
		int[] N = {1, 2, 3, 5, 7, 8, 10, 11};
		List<Interval> res = arrayToInterval(N);
		for (Interval in : res) {
			System.out.println(in.start + "==" + in.end);
		}
	}
	
	static List<Interval> arrayToInterval(int[] N) {
		List<Interval> res = new ArrayList<Interval>();
		int start = N[0];
		
		for (int i = 1; i < N.length; i++) { // 
			if (N[i] == N[i - 1] + 1) {
				continue;
			}
			Interval in = new Interval(start, N[i - 1]);
			res.add(in);
			start = N[i];
		}
		
		res.add(new Interval(start, N[N.length - 1]));
		return res;
	}
}

class Interval {
	int start;
	int end;

	public Interval(int s, int e) {
		start = s;
		end = e;
	}
}