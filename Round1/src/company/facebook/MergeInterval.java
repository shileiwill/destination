package company.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

class Interval {
	int start;
	int end;
	
	Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

/**
 * 第二题把一维intervals 拓展到2维，每个interval由起点和终点组成，问怎么merge。
 * 最后时间不够，就让写了一个comparator。 楼主是怎么写的 我的想法是先按照斜率排序
 */
class Interval2D implements Comparable<Interval2D> {
	Point start;
	Point end;
	int slope;
	
	public int compareTo(Interval2D that) {
		if (this.slope != that.slope) {
			return this.slope - that.slope;
		}
		
		if (this.start.x != that.start.x) {
			return this.start.x - that.start.x;
		}
		
		if (this.start.y != that.start.y) {
			return this.start.y - that.start.y;
		}
		
		return this.end.x - that.end.x;
	}
}

// This is a good question, including merge interval and insert interval
public class MergeInterval {
	public static void main(String[] args) {
		// [1,2],[3,5],[6,7],[8,10],[12,16]
		MergeInterval mi = new MergeInterval();
//		mi.add(1,2);
//		System.out.println("1 : " + mi.getLength());
//		mi.add(3,5);
//		System.out.println("2 : " + mi.getLength());
//		mi.add(6,7);
//		System.out.println("3 : " + mi.getLength());
//		mi.add(8,10);
//		System.out.println("4 : " + mi.getLength());
//		mi.add(1,16);
//		System.out.println("5 : " + mi.getLength());
		
		mi.insert(1,2);
		System.out.println("1 : " + mi.getWholeLength());
		mi.insert(3,5);
		System.out.println("2 : " + mi.getWholeLength());
		mi.insert(6,7);
		System.out.println("3 : " + mi.getWholeLength());
		mi.insert(8,10);
		System.out.println("4 : " + mi.getWholeLength());
		mi.insert(1,16);
		System.out.println("5 : " + mi.getWholeLength());
	}
	
	List<Interval> list = new ArrayList<Interval>();
	
	void add(int start, int end) {
		if (start > end) {
			return;
		}
		list.add(new Interval(start, end));
	}
	
	int getLength() {
		int totalLen = 0;
		
		Collections.sort(list, new Comparator<Interval>(){
			public int compare(Interval in1, Interval in2) {
				if (in1.start != in2.start) {
					return in1.start - in2.start;
				}
				return in1.end - in2.end;
			}
		});
		
		int curStart = list.get(0).start;
		int curEnd = list.get(0).end;
		
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).start > curEnd) {
				// We can change this place to get Square from API, using Interval(curStart, curEnd)
				totalLen += curEnd - curStart + 1;
				curStart = list.get(i).start;
				curEnd = list.get(i).end;
			} else {
				curEnd = Math.max(curEnd, list.get(i).end);
			}
		}
		
		totalLen += curEnd - curStart + 1;
		
		return totalLen;
	}
	
	int wholeLen = 0;
    public void insert(int start, int end) { // Insert的时候保证排好序
    	Interval newInterval = new Interval(start, end);
        List<Interval> res = new ArrayList<Interval>();
        
        wholeLen = 0;
        int pos = 0;
        int size = list.size();
        while (pos < size && list.get(pos).end < newInterval.start) {
            res.add(list.get(pos));
            wholeLen += list.get(pos).end - list.get(pos).start + 1;
            pos++;
        }
        
        while (pos < size && newInterval.end >= list.get(pos).start) {
            newInterval = new Interval(Math.min(list.get(pos).start, newInterval.start), Math.max(list.get(pos).end, newInterval.end));
            pos++;
        }
        
        res.add(newInterval);
        wholeLen += newInterval.end - newInterval.start + 1;
        
        while (pos < size) {
            res.add(list.get(pos++));
            wholeLen += list.get(pos).end - list.get(pos).start + 1;
        }
        
        list = res;
    }
    
    int getWholeLength() {
    	return wholeLen;
    }
}

// Above is based on call frequencey. what about using TreeSet, which could sort as well.
class IntervalTreeSet {
	// Here we can use TreeSet to sort based on start time/end time
	TreeSet<Interval> treeSet = new TreeSet<Interval>(new Comparator<Interval>(){
		public int compare(Interval in1, Interval in2) {
			if (in1.start != in2.start) {
				return in1.start - in2.start;
			}
			return in1.end - in2.end;
		}
	});
	
	
	
	void add(int start, int end) {
		if (start > end) {
			return;
		}
		treeSet.add(new Interval(start, end)); // O(NLog(N))
	}
	
	int getLength() { // O(N)
		int totalLen = 0;
		
		Iterator<Interval> it = treeSet.iterator();
		if (!it.hasNext()) {
			return 0;
		}
		
		Interval cur = it.next();
		
		int curStart = cur.start;
		int curEnd = cur.end;
		
		while (it.hasNext()) {
			Interval next = it.next();
			if (next.start > curEnd) {
				// We can change this place to get Square from API, using Interval(curStart, curEnd)
				totalLen += curEnd - curStart + 1;
				curStart = next.start;
				curEnd = next.end;
			} else {
				curEnd = Math.max(curEnd, next.end);
			}
		}
		
		totalLen += curEnd - curStart + 1;
		
		return totalLen;
	}
}

/*
Meeting Schedule
An easy way is to sort first, and then iterate. This approach is O(NLogN)

// This is like Bucket Sort. A good question may be if starttime and endtime are inclusive
要你找出一坨meeting是否overlap，meeting有starttime endtime, 
然后表示format自己定，然后要求O（n），meeting全部限制在一天内，并且时间都是用minute表示。就把一天分割成60*60*24个element就可以了 
*/