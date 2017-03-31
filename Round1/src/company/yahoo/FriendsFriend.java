package company.yahoo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FriendsFriend {

	public static void main(String[] args) {
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		
		Set<String> set1 = new HashSet<String>();
		set1.add("B");
		set1.add("C");
		
		Set<String> set2 = new HashSet<String>();
		set2.add("D");
		set2.add("E");
		
		Set<String> set3 = new HashSet<String>();
		set3.add("D");
		set3.add("F");
		
		Set<String> set4 = new HashSet<String>();
		set4.add("A");
		set4.add("C");
		
		Set<String> set5 = new HashSet<String>();
		set5.add("A");
		set5.add("G");
		
		map.put("A", set1);
		map.put("B", set2);
		map.put("C", set3);
		map.put("D", set4);
		map.put("E", set5);
		
		FriendsFriend ff = new FriendsFriend();
		ff.getFriendsFriend(map);
		
		// URL Parsing
		String str = "Hello+G%C3%BCnter";
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("%C3", "*");
		map2.put("%BC", "?");
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length();) {
			if (str.charAt(i) == '%') {
				String sec = str.substring(i, i + 3);
				sb.append(map2.get(sec));
				i = i + 3;
			} else {
				sb.append(str.charAt(i));
				i++;
			}
		}
		
		System.out.println(sb.toString());
	}

	Map<String, Set<String>> getFriendsFriend(Map<String, Set<String>> map) {
		Map<String, Set<String>> res = new HashMap<String, Set<String>>();
		
		for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
			String name = entry.getKey();
			Set<String> friends = entry.getValue();
			
			res.put(name, new HashSet<String>());
			
			for (String friend : friends) {
				if (map.containsKey(friend)) {
					res.get(name).addAll(map.get(friend));
				}
			}
			
			System.out.print(name + " has friends' friend: ");
			for (String friend : res.get(name)) {
				System.out.print(friend + "--");
			}
			System.out.println();
		}
		
		return res;
	}
}
