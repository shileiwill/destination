package company.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * Task那道题，很多面经都提到过。就是比如给你一串task，再给一个cooldown，执行每个task需要时间1，两个相同task之间必须至少相距cooldown的时间，
 * 问执行所有task总共需要多少时间。比如执行如下task：12323，假设cooldown是3。总共需要的时间应该是 1 2 3 _ _ 2 3，也就是7个单位的时间。
 * 再比如 1242353，假设cool down是4，那总共时间就是 1 2 4 _ _ _ 2 3 5 _ _ _ 3，也就是13个单位的时间
	基于1，给出最优的排列，使得字符串最短。
	
	这个题的follow up是Rearrange array K distance apart. 也就是可以打乱每个char的顺序
 */
public class TaskCoolDown {

	public static void main(String[] args) {
		TaskCoolDown tcd = new TaskCoolDown();
		String res = tcd.minLength2("1242353", 4);
		System.out.println(res);
	}
	
	// Second version could use sb.lastIndexOf()
	String minLength2(String s, int coolDown) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		StringBuilder sb = new StringBuilder();
		
		for (char c : s.toCharArray()) {
			if (map.containsKey(c)) {
				int oldIndex = map.get(c);
				int newIndex = sb.length();
				
				for (int i = newIndex; i <= coolDown + oldIndex; i++) {
					sb.append("*");
				}
			}
			map.put(c, sb.length());
			sb.append(c);
		}
		
		return sb.toString();
	}
}
