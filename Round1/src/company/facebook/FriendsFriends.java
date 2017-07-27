package company.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/**
 * 已知一个函数，输入用户ID，可以返回该用户的所有友好（degree 1 friends），按好友ID从小到大排序。
要求实现函数来输出返回一个用户的所有好友的好友(degree 2 friends), 以及 degree 3 friends

给你一个函数 可以返回当前的指定的id的friends，然后利用这个函数 找出给你的id（input） 的 两个以外（相距两层）的有最多mutual friends 的 id有哪些
是 我的这个ID与friends' friend的共同好友最多的，因为friend是双向的 所以只要判断 我的friend有多少也是第三层的人的friend就行了， 也就是in-degree
但是也有可能， A-B-C. D不是A的好友，但是D是C的好友，这样也是indegree啊

我的思路： bfs 然后计算第二层的每层的入度， 入度最多的就是有mutual friends最多的 

https://discuss.leetcode.com/topic/54969/fb-08-2016-phone-interview-find-2nd-degree-connections/3

Find 2nd degree connections ( friends’ friends), output these 2nd degree connections ranked by number of common friends (i.e 1st degree connections) 
with you, (example: if 2nd degree connection A has 10 common friends (1st degree connections) with you but 2nd degree connection B has 8 common friends 
(1st degree connections)with you, then A should be ranked first) Input is your connection graph represented by undirected graph nodes, 
output is list of 2nd degree connections represented by graph nodes

public List<UndirectedGraphNode> findSecDegreeConnections(UndirectedGraphNode myself){

}

you can just apply BFS for two layers and for the node of second layer(2nd degree friends), count the number of 1st degree friends who expand to it.
Then just sort the 2nd layer.
 */
public class FriendsFriends {

	public static void main(String[] args) {

	}

	public void findSecDegreeConnections(UndirectedGraphNode myself){
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
		queue.offer(myself);
		visited.add(myself);
		
		Set<UndirectedGraphNode> myFriends = new HashSet<UndirectedGraphNode>();
		for (UndirectedGraphNode next : myself.friends) {
			myFriends.add(next);
		}
		
		int level = 0;
		
		while (!queue.isEmpty() && level < 2) {
			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				UndirectedGraphNode now = queue.poll();
				for (UndirectedGraphNode next : now.friends) {
					if (!visited.contains(next)) {
						queue.offer(next);
						visited.add(next);
					}
				}
			}
			
			level++;
		}
		
		Map<UndirectedGraphNode, Integer> map = new HashMap<UndirectedGraphNode, Integer>();
		for (UndirectedGraphNode fof : queue) {
			int count = 0;
			for (UndirectedGraphNode fofof : fof.friends) {
				if (myFriends.contains(fofof)) {
					count++;
				}
			}
			map.put(fof, count); // This person has how many common friend with me
		}
		
		Map<Integer, Set<UndirectedGraphNode>> res = new HashMap<Integer, Set<UndirectedGraphNode>>();
		for (Map.Entry<UndirectedGraphNode, Integer> entry : map.entrySet()) {
			UndirectedGraphNode node = entry.getKey();
			int count = entry.getValue();
			
			if (!res.containsKey(count)) {
				res.put(count, new HashSet<UndirectedGraphNode>());
			}
			res.get(count).add(node);
		}
		
		// Sort res and find the persons which share most common friends
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
	
	class UndirectedGraphNode {
		String name;
	    List<UndirectedGraphNode> friends;
	    UndirectedGraphNode(String x) { name = x; friends = new ArrayList<UndirectedGraphNode>(); }
	}
}
