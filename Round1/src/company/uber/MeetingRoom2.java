package company.uber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
/**
 * 
半小时前的电面，面试官直接上题。类似LC里的meeting room II那题，但更难一点要求返回需要的room数量以及相应的时间段(频率最高的时间段)。应该也是道面经题。
follow up是考虑time period的inclusive和exclusive两种不同的情况（其实是我一开始问的，结果面试官就说这个问题好，就作为follow up吧），
也就是inclusive的情况[1,2]和[2,3]要两个room，exclusive的情况只需要一个room。test了一下相应的corner case。
面得很惊险，中间有个bug卡了一下一度以为自己肯定挂了，靠print才找出bug来，还好最后跑出了面试官想要的结果。

PS：当时板上看到这题的时候觉得跟meeting room II差不多就没太仔细想，结果今天面的时候发现比meeting room II那题要难不少，建议要面uber的人自己尝试写一下。
 */
public class MeetingRoom2 {

	public static void main(String[] args) {
		// [0, 30],[5, 10],[15, 20]
		Interval in1 = new Interval(0, 30);
		Interval in2 = new Interval(5, 10);
		Interval in3 = new Interval(15, 20);
		
		List<Interval> list = new ArrayList<Interval>();
		list.add(in1);
		list.add(in2);
		list.add(in3);
		
		MeetingRoom2 mr = new MeetingRoom2();
		mr.find(list);
	}

	List<Interval> res = new ArrayList<Interval>();
	
	// Original version, which is just to find out the number of room required
	void find(List<Interval> list) {
		Comparator<Interval> comByStart = new Comparator<Interval>() {
			public int compare(Interval in1, Interval in2) {
				if (in1.start != in2.start) {
					return in1.start - in2.start;
				}
				
				return in1.end - in2.end;
			}
		};
		
		Collections.sort(list, comByStart);
		
		Comparator<Interval> comByEnd = new Comparator<Interval>() {
			public int compare(Interval in1, Interval in2) {
				if (in1.end != in2.end) {
					return in1.end - in2.end;
				}
				
				return in1.start - in2.start;
			}
		};
		
		PriorityQueue<Interval> heap = new PriorityQueue<Interval>(list.size(), comByEnd);
		heap.offer(list.get(0));
		
		for (int i = 1; i < list.size(); i++) {
			Interval pre = heap.poll(); // Earliest end
			Interval now = list.get(i); // Earliest start
			
			if (pre.end <= now.start) { // No Overlap, just use the same room
				pre.end = now.end;
			} else { // There is overlap, need 2 rooms, add both to heap
				heap.offer(now);
			}
			
			heap.offer(pre);
		}
		
		System.out.println(heap.size());
	}
	
	void find2(List<Interval> list) {
		Comparator<Interval> comByStart = new Comparator<Interval>() {
			public int compare(Interval in1, Interval in2) {
				if (in1.start != in2.start) {
					return in1.start - in2.start;
				}
				
				return in1.end - in2.end;
			}
		};
		
		Collections.sort(list, comByStart);
		
		Comparator<Interval> comByEnd = new Comparator<Interval>() {
			public int compare(Interval in1, Interval in2) {
				if (in1.end != in2.end) {
					return in1.end - in2.end;
				}
				
				return in1.start - in2.start;
			}
		};
		
		PriorityQueue<Interval> heap = new PriorityQueue<Interval>(list.size(), comByEnd);
		heap.offer(list.get(0));
		
		for (int i = 1; i < list.size(); i++) {
			Interval pre = heap.poll(); // Earliest end
			Interval now = list.get(i); // Earliest start
			
			if (pre.end <= now.start) { // No Overlap, just use the same room
				pre.end = now.end;
			} else { // There is overlap, need 2 rooms, add both to heap
				heap.offer(now);
			}
			
			heap.offer(pre);
		}
		
		System.out.println(heap.size());
	}
}

class Interval {
	int start;
	int end;
	
	Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
}