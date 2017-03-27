package company.jet.com;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给你string【】people in which department，string【】people friends people。
 * 让你output每个people有哪些friends。第二题是让你output每个department有几个人有friend是其他department的。
 * 面试官基本上不怎么提醒，60分钟15分钟聊简历，45分钟implement。要全部写完还是有点困难。 
 * 这题还有第三问，貌似是根据关系return是否他们都互相有联系，就是用unionfind解决的题。
 * 
 * 是类似朋友圈的问题，一个公司给你两个String[] employeeInput = {"1,Alice,HR", "2,Bob,Engineer".....}, String[] friendsInput = {"1,2","1,3","2,4"}
1st Question: 输出所有的employee的friendlist -> 就是用一个map存起来然后打印就好了（这个是无向图，e.g: 1和2是朋友，2的列表里也要有1）
2nd Question: 输出每个department里有多少人的朋友是其他部门的 ->也就是遍历一遍就好了
3rd Question: 输出是否所有employee都在一个社交圈 -> 我当时想的就是随便找一个点，用DFS遍历一遍，如果所有点都被遍历到就return true，不然就是false
 */
public class Friends {
	
	public static void main(String[] args) {
		String[] employeeInput = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,CS", "4,Tom,Engineer","5,Jeff,CS","6,Lei,CS"};
		String[] friendsInput = {"1,2","1,3","2,4", "4,3", "5,6"};
		
		Friends f = new Friends();
		f.buildMaps(employeeInput, friendsInput);
		f.printFriendList1();
		
		Map<String, Integer> count = f.otherDepts();
		for (Map.Entry<String, Integer> entry : count.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		f.uf = new UnionFind(f.personFriends);
		System.out.println(f.isAllConnected());
	}
	
	
	Map<Integer, String> idDept = new HashMap<Integer, String>();
	Map<String, Integer> nameId = new HashMap<String, Integer>();
	Map<Integer, String> idName = new HashMap<Integer, String>();
	Map<String, Set<Integer>> deptPersons = new HashMap<String, Set<Integer>>();
	Map<Integer, Set<Integer>> personFriends = new HashMap<Integer, Set<Integer>>(); 
	UnionFind uf = null;
	
	void buildMaps(String[] employeeInput, String[] friendsInput) {
		for (String employee : employeeInput) {
			String[] idNameDept = employee.split(",");
			
			int id = Integer.valueOf(idNameDept[0]);
			String name = idNameDept[1];
			String dept = idNameDept[2];
			
			idDept.put(id, dept);
			nameId.put(name, id);
			idName.put(id, name);
			
			if (!deptPersons.containsKey(dept)) {
				deptPersons.put(dept, new HashSet<Integer>());
			}
			deptPersons.get(dept).add(id);
		}
		
		for (String friends : friendsInput) {
			String[] pair = friends.split(",");
			
			int p1 = Integer.valueOf(pair[0]);
			int p2 = Integer.valueOf(pair[1]);
			
			if (!personFriends.containsKey(p1)) {
				personFriends.put(p1, new HashSet<Integer>());
			}
			personFriends.get(p1).add(p2);
			
			if (!personFriends.containsKey(p2)) {
				personFriends.put(p2, new HashSet<Integer>());
			}
			personFriends.get(p2).add(p1);
		}
	}
	
	void printFriendList1() {
		for (Map.Entry<Integer, Set<Integer>> entry : personFriends.entrySet()) {
			int id = entry.getKey();
			String name = idName.get(id);
			
			Set<Integer> friends = entry.getValue();
			
			System.out.print(name + " has friends: ");
			for (int friendId : friends) {
				System.out.print(idName.get(friendId) + " , ");
			}
			System.out.println();
		}
	}
	
	Map<String, Integer> otherDepts() {
		Map<String, Integer> res = new HashMap<String, Integer>();
		
		for (Map.Entry<String, Set<Integer>> entry : deptPersons.entrySet()) {
			String dept = entry.getKey();
			Set<Integer> persons = entry.getValue();
			
			int count = 0;
			for (int p : persons) {
				if (personFriends.containsKey(p)) {
					for (int friend : personFriends.get(p)) {
						if (!idDept.get(friend).equals(dept)) {
							count++;
							break;
						}
					}
				}
			}
			
			res.put(dept, count);
		}
		
		return res;
	}
	
	Map<String, Integer> findFriendsInOtherDept() {
		Map<String, Integer> res = new HashMap<String, Integer>();
		
		for (Map.Entry<String, Set<Integer>> entry : deptPersons.entrySet()) {
			String dept = entry.getKey();
			Set<Integer> persons = entry.getValue();
			
			res.put(dept, 0);
			
			for (int person : persons) {
				for (int friend : personFriends.get(person)) {
					if (!idDept.get(friend).equals(dept)) {
						res.put(dept, res.get(dept) + 1);
						break; // Next person in this department
					}
				}
			}
		}
		
		return res;
	}
	
	boolean isAllConnected() {
		if (personFriends.size() != idName.size()) { // Some people don't have friends
			return false;
		}
		return uf.isAllConnected();
	}
}

class UnionFind {
	int[] parent = null;
	
	UnionFind(Map<Integer, Set<Integer>> personFriends) {
		int size = personFriends.size();
		parent = new int[size + 1];
		
		for (Integer key : personFriends.keySet()) {
			parent[key] = key;
		}
		
		for (Map.Entry<Integer, Set<Integer>> entry : personFriends.entrySet()) {
			int key = entry.getKey();
			Set<Integer> friends = entry.getValue();
			
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
			parent[parent2] = parent1;
		}
	}
	
	boolean isAllConnected() {
		int p = parent[1];
		
		for (int i = 1; i < parent.length; i++) {
			if (p != parent[i]) {
				return false;
			}
		}
		
		return true;
	}
}
