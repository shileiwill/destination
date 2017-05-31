package company.uber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jdk.nashorn.internal.ir.Block;

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
    	List<Interval> sortedByStart = new ArrayList<Interval>();
    	List<Interval> sortedByEnd = new ArrayList<Interval>();
    	
    	Interval in1 = new Interval(1, 2, 100);
    	Interval in2 = new Interval(4, 5, 100);
    	Interval in3 = new Interval(3, 6, 100);
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

        int curEnd = 0;

        for (int i = 1; i < len; i++) {
            Interval bi = sortedByStart.get(i);
            while (sortedByEnd.get(curEnd).end < bi.start) { // No overlap any more, 
                curSum -= sortedByEnd.get(curEnd).height; // Remove this height
                curEnd++; // move "end" forward
            }
            
            curSum += bi.height;
            max = Math.max(max, curSum);
        }
        System.out.print(max);
    }
}