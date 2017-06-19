package company.facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * 给一组Interval的二维数组，每一组代表一个接收方，每一个接收方有一组区间。然后给一个整数，要求找出所有区间包含这个整数的接收方
 * 
 * A想要接收1到4、7到9、12到15
B想要接收2到8、10到12
C想要接收5到6
如果给一个数字8，应该返回A和B
如果给个数字5，应该返回B和C

 * 每个人的intervals 都是排好序的，所以可以binary search，这样比brute force好点
 */
public class IntervalsSearch {

	public static void main(String[] args) {
		IntervalsSearch is = new IntervalsSearch();
		
	}

	List<Integer> match(List<List<Interval>> source, int point) {
		List<Integer> res = new ArrayList<Integer>();
		
		for (int i = 0; i < source.size(); i++) {
			List<Interval> list = source.get(i);
			
			int left = 0, right = list.size() - 1;
			while (left < right) {
				int mid = (left + right) / 2;
				Interval in = list.get(mid);
				
				if (point >= in.start && point <= in.end) {
					res.add(i);
					break;
				} else if (point < in.start) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
		}
		
		return res;
	}
}
