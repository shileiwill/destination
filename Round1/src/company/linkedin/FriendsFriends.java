package company.linkedin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * 已知一个函数，输入用户ID，可以返回该用户的所有友好（degree 1 friends），按好友ID从小到大排序。
要求实现函数来输出返回一个用户的所有好友的好友(degree 2 friends), 以及 degree 3 friends。
 */
public class FriendsFriends {

	public static void main(String[] args) {

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
	
	Set<String> get2DegreeFriend(String host) {
		Set<String> degree1 = getFriends(host);
		
		Set<String> res = new HashSet<String>();
		for (String friend : degree1) {
			Set<String> degree2 = getFriend(friend);
			res.addAll(degree2);
		}
		
		// Remove himself
		if (res.contains(host)) {
			res.remove(host);
		}
		
		// Degree2 is degree2, remove degree1
		for (String name : degree1) {
			res.remove(name);
		}
		
		return res;
	}
	
	// Better way
	Set<String> get2DegreeFriend(String host) {
		Set<String> degree1 = getFriends(host);
		
		Set<String> res = new HashSet<String>();
		for (String friend : degree1) {
			Set<String> degree2 = getFriend(friend);

			for (String name : degree2) {
				if (name.equals(host) || degree1.contains(name)) {
					continue;
				}
				degree2.add(name);
			}
		}
		return res;
	}
}
