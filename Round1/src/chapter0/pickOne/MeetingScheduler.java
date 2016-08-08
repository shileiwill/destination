package chapter0.pickOne;

/**
 * 253. Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
 Given [[0, 30],[5, 10],[15, 20]],
 return 2. 

 * @author Lei
 *
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingScheduler {
	public int minRoom(Meeting[] meetings) {
		if (meetings == null || meetings.length == 0) {
			return 0;
		}
		
		// Sort by start time, from small to big
		Comparator<Meeting> compareByStartTime = new Comparator<Meeting>() {
			public int compare(Meeting m1, Meeting m2) {
				return m1.startTime - m2.startTime;
			}
		};
		
		Arrays.sort(meetings, compareByStartTime);
		
		Comparator<Meeting> compareByEndTime = new Comparator<Meeting>() {
			public int compare(Meeting m1, Meeting m2) {
				return m1.endTime - m2.endTime;
			}
		};
		
		// Build a heap, sorted by end time, from small to big. Always get the smallest/earliest ended
		PriorityQueue<Meeting> heap = new PriorityQueue<Meeting>(meetings.length, compareByEndTime);
		heap.offer(meetings[0]); // Put first earliest meeting in heap
		
		for (int i = 1; i < meetings.length; i++) {
			Meeting m = heap.poll(); // This is the earliest finished meeting
			if (meetings[i].startTime >= m.endTime) {
				// Can use the same meeting room, just update the end time, and put back to heap
				m.endTime = meetings[i].endTime;
			} else {
				// There is conflict even with the earliest finished meeting, so have to open a new room
				heap.offer(meetings[i]);
			}
			// Put back the "original" one
			heap.offer(m);
		}

		return heap.size();
	}
	
	public static void main(String[] args) {
		MeetingScheduler ms = new MeetingScheduler();
		
		Meeting m1 = new Meeting(1, 2);
		Meeting m2 = new Meeting(1, 4);
		Meeting m3 = new Meeting(3, 5);
		Meeting m4 = new Meeting(6, 7);
		Meeting m5 = new Meeting(8, 10);
		Meeting m6 = new Meeting(2, 3);
		Meeting m7 = new Meeting(3, 5);
		Meeting m8 = new Meeting(1, 2);
		
		Meeting[] meetings = {m1, m2, m3, m4, m5, m6, m7, m8};
		
		int res = ms.minRoom(meetings);
		System.out.println(res);
	}
}

//https://hellosmallworld123.wordpress.com/category/interview-questions/facebook/
class Meeting {
	int startTime;
	int endTime;

	public Meeting(int s, int e) {
		startTime = s;
		endTime = e;
	}
}