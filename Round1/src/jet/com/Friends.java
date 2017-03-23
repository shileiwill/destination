package jet.com;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 给你string【】people in which department，string【】people friends people。
 * 让你output每个people有哪些friends。第二题是让你output每个department有几个人有friend是其他department的。
 * 面试官基本上不怎么提醒，60分钟15分钟聊简历，45分钟implement。要全部写完还是有点困难。 
 * 这题还有第三问，貌似是根据关系return是否他们都互相有联系，就是用unionfind解决的题。
 */
public class Friends {

	public static void main(String[] args) {
		
	}
	
	
	Map<Integer, String> personDept = new HashMap<Integer, String>();
	Map<String, Integer> personNameId = new HashMap<String, Integer>();
	Map<String, Set<Integer>> deptPersons = new HashMap<String, Set<Integer>>();
	Map<Integer, Set<Integer>> personFriends = new HashMap<Integer, Set<Integer>>(); 
	
	Map<String, Integer> findFriendsInOtherDept() {
		Map<String, Integer> res = new HashMap<String, Integer>();
		
		for (Map.Entry<String, Set<Integer>> entry : deptPersons.entrySet()) {
			String dept = entry.getKey();
			Set<Integer> persons = entry.getValue();
			
			res.put(dept, 0);
			
			for (int person : persons) {
				for (int friend : personFriends.get(person)) {
					if (!personDept.get(friend).equals(dept)) {
						res.put(dept, res.get(dept) + 1);
						break; // Next person in this department
					}
				}
			}
		}
		
		return res;
	}
	
	UnionFind uf = new UnionFind(personFriends);
	boolean isConnected(int person1, int person2) {
		int parent1 = uf.find(person1);
		int parent2 = uf.find(person2);
		
		return parent1 == parent2;
	}
	
	class UnionFind {
		int[] parent = null;
		
		UnionFind(Map<Integer, Set<Integer>> personFriends) {
			int size = personFriends.size();
			parent = new int[size];
			
			for (Integer key : personFriends.keySet()) {
				parent[key] = key;
			}
			
			for (Map.Entry<Integer, Set<Integer>> entry : personFriends.entrySet()) {
				int key = entry.getKey();
				Set<Integer> friends = entry.getValue();
				
				parent[key] = key;
				
				for (int friend : friends) {
					union(key, friend);
				}
			}
		}
		
		int find(int id) {
			while (id != parent[id]) {
				parent[id] = parent[parent[id]];
				id = parent[id];
			}
			return id;
		}
		
		void union(int id1, int id2) {
			int parent1 = find(id1);
			int parent2 = find(id2);
			
			if (parent1 != parent2) {
				parent[parent1] = parent2;
			}
		}
	}
}
