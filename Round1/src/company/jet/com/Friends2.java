package company.jet.com;

import java.util.*;
import java.util.Map;
/**
 * 1st Question: 输出所有的employee的friendlist -> 就是用一个map存起来然后打印就好了（这个是无向图，e.g: 1和2是朋友，2的列表里也要有1）
2nd Question: 输出每个department里有多少人的朋友是其他部门的 ->也就是遍历一遍就好了
3rd Question: 输出是否所有employee都在一个社交圈 -> 我当时想的就是随便找一个点，用DFS遍历一遍，如果所有点都被遍历到就return true，不然就是false
 */
public class Friends2 {

	public static void main(String[] args) {
		String[] employeeInput = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,Art", "4,Tom,Engineer","5,Jeff,CS","6,Lei,CS"};
		String[] friendsInput = {"1,2","1,3","2,4", "4,3", "4,6"};
		
		Friends2 f = new Friends2();
		f.buildMaps(employeeInput, friendsInput);
		f.printFriends();
		
		f.otherDepts();
		System.out.println(f.isAllConnected());
		
		f.dfs();
	}

	Map<Integer, String> idName = new HashMap<Integer, String>();
	Map<String, Integer> nameId = new HashMap<String, Integer>();
	Map<Integer, String> idDept = new HashMap<Integer, String>();
	Map<String, Set<Integer>> deptPersons = new HashMap<String, Set<Integer>>();
	Map<Integer, Set<Integer>> idFriends = new HashMap<Integer, Set<Integer>>();
	
	Set<Integer> dfs() {
		Set<Integer> res = new HashSet<Integer>();
		
		int id = idFriends.keySet().iterator().next();
		dfs(res, id);
		
		System.out.println("Is all connected using DFS : " + (res.size() == idName.size()));
		return res;
	}
	
	void dfs(Set<Integer> res, int id) {
		for (int fri : idFriends.get(id)) {
			if (!res.contains(fri)) {
				res.add(fri);
				dfs(res, fri);
			}
		}
	}
	
	Map<String, Integer> otherDepts() {
		Map<String, Integer> res = new HashMap<String, Integer>();
		
		for (Map.Entry<String, Set<Integer>> entry : deptPersons.entrySet()) {
			String dept = entry.getKey();
			Set<Integer> persons = entry.getValue();
			
			int count = 0;
			for (int id : persons) {
				if (idFriends.containsKey(id)) {
					for (int friend : idFriends.get(id)) {
						String anotherDept = idDept.get(friend);
						if (!anotherDept.equals(dept)) {
							count++;
							break; // Done with this person
						}
					}
				}
			}
			
			res.put(dept, count);
		}
		
		for (Map.Entry<String, Integer> entry : res.entrySet()) {
			System.out.println(entry.getKey() + " has " + entry.getValue());
		}
		
		return res;
	}
	
	void buildMaps(String[] employeeInput, String[] friendsInput) {
		for (String str : employeeInput) {
			String[] idNameDept = str.split(",");
			int id = Integer.valueOf(idNameDept[0]);
			String name = idNameDept[1];
			String dept = idNameDept[2];
			
			idName.put(id, name);
			nameId.put(name, id);
			idDept.put(id, dept);
			
			if (!deptPersons.containsKey(dept)) {
				deptPersons.put(dept, new HashSet<Integer>());
			}
			deptPersons.get(dept).add(id);
		}
		
		for (String str : friendsInput) {
			String[] arr = str.split(",");
			int id1 = Integer.valueOf(arr[0]);
			int id2 = Integer.valueOf(arr[1]);
			
			if (!idFriends.containsKey(id1)) {
				idFriends.put(id1, new HashSet<Integer>());
			}
			idFriends.get(id1).add(id2);
			
			if (!idFriends.containsKey(id2)) {
				idFriends.put(id2, new HashSet<Integer>());
			}
			idFriends.get(id2).add(id1);
		}
	}
	
	void printFriends() {
//		for (Map.Entry<Integer, Set<Integer>> entry : idFriends.entrySet()) {
//			int id1 = entry.getKey();
//			Set<Integer> friends = entry.getValue();
//			
//			System.out.print(idName.get(id1) + " has friends: ");
//			
//			for (int fri : friends) {
//				System.out.print(idName.get(fri) + ", ");
//			}
//			System.out.println();
//		}
		
		for (Map.Entry<Integer, String> entry : idName.entrySet()) { // All the people, including the one who doesnt have friends
			int id = entry.getKey();
			String name = entry.getValue();
			
			if (!idFriends.containsKey(id)) {
				System.out.println(name + " has no friends");
			} else {
				Set<Integer> friends = idFriends.get(id);
				System.out.print(name + " has friends : ");
				
				for (int fri : friends) {
					System.out.print(idName.get(fri) + ", ");
				}
				System.out.println();
			}
		}
		
	}
	
	boolean isAllConnected() {
		if (idFriends.size() != idName.size()) {
			return false;
		}
		UnionFind2 uf = new UnionFind2(idFriends);
		return uf.isAllConnected();
	}
}

class UnionFind2 {
	int[] parent = null;
	
	UnionFind2(Map<Integer, Set<Integer>> idFriends) {
		int size = idFriends.size();
		parent = new int[size + 1];
		
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
		
		for (Map.Entry<Integer, Set<Integer>> entry : idFriends.entrySet()) {
			int id = entry.getKey();
			
			for (int fri : entry.getValue()) {
				union(id, fri);
			}
		}
	}
	
	void union(int id, int fri) {
		int p1 = find(id);
		int p2 = find(fri);
		
		if (p1 != p2) {
			parent[p2] = p1;
		}
	}
	
	int find(int id) {
		while (id != parent[id]) {
			parent[id] = parent[parent[id]];
			id = parent[id];
		}
		
		return id;
	}
	
	boolean isAllConnected() {
		int p1 = find(1);
		
		for (int i = 2; i < parent.length; i++) {
			if (p1 != find(i)) {
				return false;
			}
		}
		
		return true;
	}
}