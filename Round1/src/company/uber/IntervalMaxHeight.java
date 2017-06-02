package company.uber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IntervalMaxHeight {

    static class Interval {
        long height;
        Integer start; // 0 based index
        Integer end; // 0 based index

        public Interval(int start, int end, long heigh) {

            this.height = heigh;
            this.start = start;
            this.end = end;
        }
    }

    // Pass all tests on hackerrank https://www.hackerrank.com/challenges/crush
    public static void main(String[] args) {
    	new IntervalMaxHeight().findHighestInterval();
    }
    
    void findHighestInterval() {
    	List<Interval> sortedByStart = new ArrayList<Interval>();
    	List<Interval> sortedByEnd = new ArrayList<Interval>();
    	
    	Interval in1 = new Interval(1, 4, 1);
    	Interval in2 = new Interval(3, 5, 1);
    	Interval in3 = new Interval(3, 6, 1);
    	sortedByStart.add(in1);
    	sortedByStart.add(in2);
    	sortedByStart.add(in3);
    	sortedByEnd.add(in1);
    	sortedByEnd.add(in2);
    	sortedByEnd.add(in3);
    	
    	int len = sortedByStart.size();
    	
    	Comparator<Interval> comStart = new Comparator<Interval>() {
    		public int compare(Interval in1, Interval in2) {
    			if (in1.start != in2.start) {
    				return in1.start - in2.start;
    			}
    			return in1.end - in2.end;
    		}
    	};
    	
    	Comparator<Interval> comEnd = new Comparator<Interval>() {
    		public int compare(Interval in1, Interval in2) {
    			if (in1.end != in2.end) {
    				return in1.end - in2.end;
    			}
    			return in1.start - in2.start;
    		}
    	};
        
        Collections.sort(sortedByStart, comStart); // NlogN
        Collections.sort(sortedByEnd, comEnd);

        long curSum = sortedByStart.get(0).height;
        long max = sortedByStart.get(0).height;
        int start = sortedByStart.get(0).start;
        int end = sortedByStart.get(0).end;

        int curEnd = 0;

        for (int i = 1; i < len; i++) {
            Interval bi = sortedByStart.get(i);
            while (sortedByEnd.get(curEnd).end < bi.start) { // No overlap any more, 
                curSum -= sortedByEnd.get(curEnd).height; // Remove this height
                curEnd++; // move "end" forward
            }
            
            curSum += bi.height;
            if (curSum > max) {
            	max = curSum;
            	start = Math.max(bi.start, sortedByEnd.get(curEnd).start);
            	end = Math.min(bi.end, sortedByEnd.get(curEnd).end);
            }
        }
        System.out.println(max);
        System.out.println(start);
        System.out.println(end);
    }
}