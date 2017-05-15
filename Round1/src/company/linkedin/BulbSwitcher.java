package company.linkedin;

import java.util.ArrayList;
import java.util.List;

/**
 * 319. 有n个学生（编号为sid = [1..n]）依次走进有n个锁柜的房间（锁的编号为lockid = [1..n])，
该学生将会打开或锁上lockid可以被sid整除的锁，写一个打印所有在第n个学生操作后处于打开的状态的lockid。
 */
public class BulbSwitcher {

	public static void main(String[] args) {

	}

	List<Integer> switcher(int N) {
		List<Integer> res = new ArrayList<Integer>();
		
		for (int i = 1; i <= N; i++) {
			int sqrt = (int)Math.sqrt(i);
			
			if (sqrt * sqrt == i) { // 是否是整除, if yes, 会落单
				res.add(i); // This guy will be on, as there is odd number of turn on/off
			}
		}
		
		return res;
	}
}
