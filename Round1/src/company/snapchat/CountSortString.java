package company.snapchat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

// 统计并打印所有单词在一个文本里出现次数， 频率最高的打印在最前面
public class CountSortString {

	public static void main(String[] args) {
		String str = "Look to the skies above London and you’ll see the usual suspects rainclouds, plane and pigeons. But by the end of the year, you might just see something else.\n";
		
		// Replace all comma, semicolon etc to space
		str.replace(',', ' ');
		str.replace('.', ' ');
		
		// Split by space to get a list of words
		String[] arr = str.split("\\s+");
		
		// word, count
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(String s : arr) {
			if(map.containsKey(s)) {
				map.put(s, map.get(s) + 1);
			} else {
				map.put(s, 1);
			}
		}
		
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> en1, Map.Entry<String, Integer> en2) {
				return en2.getValue() - en1.getValue(); // bigger first
			}
		});
		
		for(Map.Entry<String, Integer> en : list) {
			System.out.println(en.getKey() + " : " + en.getValue());
		}
	}

}
