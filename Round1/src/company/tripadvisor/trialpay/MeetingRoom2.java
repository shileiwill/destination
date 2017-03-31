package company.tripadvisor.trialpay;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRoom2 {

	public static void main(String[] args) {
		
	}

	int minRooms(List<Interval> list) {
		Comparator<Interval> comByStart = new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				if (i1.start != i2.start) {
					return i1.start - i2.start;
				}
				
				return i1.end - i2.end;
			}
		};
		
		Collections.sort(list, comByStart);
		
		Comparator<Interval> comByEnd = new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				if (i1.end != i2.end) {
					return i1.end - i2.end;
				}
				
				return i1.start - i2.start;
			}
		};
		
		PriorityQueue<Interval> heap = new PriorityQueue<Interval>(list.size(), comByEnd);
		heap.offer(list.get(0));
		
		for (int i = 1; i < list.size(); i++) {
			Interval now = list.get(i);
			Interval prev = heap.poll();
			
			if (prev.end > now.start) {
				heap.offer(now);
			} else {
				prev.end = now.end;
			}
			
			heap.offer(prev);
		}
		
		return heap.size();
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
