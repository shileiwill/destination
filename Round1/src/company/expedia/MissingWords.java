package company.expedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissingWords {

	public static void main(String[] args) {
		String str1 = "I am using HackerRank to improve programming";
		String str2 = "am HackerRank to improve";
		
		MissingWords mw = new MissingWords();
		List<String> res = mw.missingWords(str1, str2);
		
		for (String str : res) {
			System.out.print(str + "--");
		}
	}

	List<String> missingWords(String str1, String str2) {
		String[] S = str1.split(" ");
		String[] T = str2.split(" "); // Must be smaller
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String str : T) {
			map.put(str, map.getOrDefault(str, 0) + 1);
		}
		
		List<String> res = new ArrayList<String>();
		for (String str : S) {
			if (!map.containsKey(str)) {
				res.add(str);
			} else {
				map.put(str, map.get(str) - 1);
				
				if (map.get(str) == 0) {
					map.remove(str);
				}
			}
		}
		
		return res;
	}
}
