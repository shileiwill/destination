package company.uber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * 印度人coding， 一个文件每行是userID，logintime，logouttime，设计函数userNum（Time time） 
 * 感觉这道题是个综合题，分了两个大部分 第一部分是给一个排好序的不重合interval加入一个新的interval，类似利口57，
 * 其实linkedlist很好解决的(复杂度O(n))，但是我这里用了treemap(理论上复杂度O(ln))，纯给自己找不痛快 第二部分是给某一个区间加1，利口上好像有用array实现的题，
 * 不过这里不能用array，我还是用了treemap- - 如果不考虑动态加载新的记录，还可以简化一点，加记录的时候不用每次都返回一组新覆盖的interval 反正面试的时候我是绝逼写不出来的
 */
public class UserCountDesign {

	public static void main(String[] args) {

	}

	TreeMap<Integer, Integer> timeCount = new TreeMap<Integer, Integer>();
	Map<Integer, List<Interval>> userInterval = new HashMap<Integer, List<Interval>>();
	
	List<Interval> insert(int userId, int start, int end) {
		List<Interval> res = new ArrayList<Interval>();
		
		if (!userInterval.containsKey(userId)) {
			Interval in = new Interval(start, end);
			res.add(in);
			return res;
		} else {
			List<Interval> existing = userInterval.get(userId);
			
			int pos = 0;
			while (pos < existing.size() && existing.get(pos).end < start) {
				pos++;
			}
			
			if (existing.get(pos).start <= start && existing.get(pos).end >= end) { // 完全覆盖新的Interval
				return res;
			}
			
			if (existing.get(pos).start >= end) { // 完全独立
				res.add(new Interval(start, end));
				return res;
			}
			
			while (pos < existing.size() && existing.get(pos).start < end) {
				Interval now = existing.get(pos);
				
				// Not sure if this will work.....
				if (start < now.start) {
					res.add(new Interval(start, now.start));
				}
				
				if (end > now.end) {
					start = now.end;
				}
				pos++;
			}
		}
		
		return res;
	}
}

// https://instant.1point3acres.com/thread/256599