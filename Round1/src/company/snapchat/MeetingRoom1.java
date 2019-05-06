package company.snapchat;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 252. Given an array of Interval time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
 Given [[0, 30],[5, 10],[15, 20]],
 return false. 

 * @author Lei
 *
 */
public class MeetingRoom1 {

	public static void main(String[] args) {
		MeetingRoom1 ms = new MeetingRoom1();
		
		Interval m1 = new Interval(1, 2);
		Interval m2 = new Interval(1, 4);
		Interval m3 = new Interval(3, 5);
		Interval m4 = new Interval(6, 7);
		Interval m5 = new Interval(8, 10);
		Interval m6 = new Interval(2, 3);
		Interval m7 = new Interval(3, 5);
		Interval m8 = new Interval(5, 6);
		
		Interval[] meetings = {m1, m2, m3, m4, m5, m6, m7, m8};
		
		int res = ms.maxMeetings(meetings);
		System.out.println(res);
	}
	
	// Given only 1 Interval room, arrange the most meetings
    public int maxMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        Comparator<Interval> sortByEnd = new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2) {
                return i1.end - i2.end;
            }
        };
        
        Arrays.sort(intervals, sortByEnd);
        
        int count = 1;
        int lastEnd = intervals[0].end;
        for (int i = 1; i < intervals.length; i++) {
            Interval i2 = intervals[i];
            if (lastEnd <= i2.start) {
                count++;
                lastEnd = i2.end;
            } 
        }
        
        return count;
    }
    
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        
        Comparator<Interval> sortByStart = new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        };
        
        Arrays.sort(intervals, sortByStart);
        
        for (int i = 1; i < intervals.length; i++) {
            Interval i1 = intervals[i - 1];
            Interval i2 = intervals[i];
            if (i1.end > i2.start) {
                return false;
            }
        }
        
        return true;
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