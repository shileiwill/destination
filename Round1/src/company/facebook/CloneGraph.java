package company.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 133
public class CloneGraph {

	Map<Node, Node> map = new HashMap<Node, Node>();

	Node cloneGraph(Node node) {
		return dfs(node);
	}

	Node dfs(Node node) {
		if (node == null) {
			return null;
		}

		if (map.containsKey(node)) {
			return map.get(node); // 如果有重复的元素，在这里就return了， 不会继续进入dfs
		}

		Node copy = new Node(node.val);
		map.put(node, copy);

		for (Node child : node.children) {
			copy.children.add(dfs(child));
		}

		return copy;
	}

	/**
	 * 有一个user class，实现method deep copy 一个 user。注意环， 例如 b 是 a friend, c 是 b friend, a 是 c friend. 代码具体应该如何实现？
	 * 
	 * 跟上边没任何区别， 一模一样，有环不影响
	 */
	static class User {
		String name;
		List<User> friends;

		User(String name) {
			this.name = name;
			this.friends = new ArrayList<User>();
		}
	}
	
	static Map<User, User> userMap = new HashMap<User, User>();
	static User deepCopy(User root) {
		if (root == null) {
			return null;
		}
		
		if (userMap.containsKey(root)) {
			return userMap.get(root);
		}
		
		User user = new User(root.name);
		userMap.put(root, user);
		
		for (User friend : root.friends) {
//			if (!userMap.containsKey(friend)) {
				user.friends.add(deepCopy(friend));
//			} 
//			else {
//				user.friends.add(userMap.get(friend));
//			}
		}
		
		return user;
	}
	
	public static void main(String[] args) {
		User user1 = new User("user1");
		User user2 = new User("user2");
		User user3 = new User("user3");
		User user4 = new User("user4");
		User user5 = new User("user5");
		User user6 = new User("user6");
		
		user1.friends.add(user2);
		user1.friends.add(user4);
		user2.friends.add(user3);
		user3.friends.add(user1);
		
		User copy = deepCopy(user1);
		System.out.println(copy.name);
	}
}
