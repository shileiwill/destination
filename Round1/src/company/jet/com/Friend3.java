package company.jet.com;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Friend3 {

	public static void main(String[] args) {
		String[] employeeInput = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,Art", "4,Tom,Engineer","5,Jeff,CS","6,Lei,CS"};
		String[] friendsInput = {"1,2","1,3","2,4", "4,3", "5,6"};
		
		Friend3 f = new Friend3();
		f.buildMaps(employeeInput, friendsInput);
		f.printFriends();
		
		f.otherDepts();
//		System.out.println(f.isAllConnected());
//		
		f.isAllConnected();
	}
	
	Map<Integer, String> idName = new HashMap<Integer, String>();
	Map<Integer, String> idDept = new HashMap<Integer, String>();
	Map<Integer, Set<Integer>> idFriends = new HashMap<Integer, Set<Integer>>();
	Map<String, Set<Integer>> deptPersons = new HashMap<String, Set<Integer>>();

	Map<String, Integer> otherDepts() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (Map.Entry<String, Set<Integer>> entry : deptPersons.entrySet()) {
			String dept = entry.getKey();
			Set<Integer> persons = entry.getValue();
			
			int count = 0;
			for (int person : persons) {
				for (int friend : idFriends.get(person)) {
					if (!idDept.get(friend).equals(dept)) {
						count++;
						break;
					}
				}
			}
			
			map.put(dept, count);
		}
		
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " has " + entry.getValue());
		}
		
		return map;
	}
	
	boolean isAllConnected() {
		Set<Integer> res = new HashSet<Integer>();
		if (idFriends.size() == 0) {
			return false;
		}
		
		int id = idFriends.keySet().iterator().next();
		dfs(res, id);
		
		System.out.println("Are all connected : " + (res.size() == idName.size()));
		return res.size() == idName.size();
	}
	
	void dfs(Set<Integer> res, int id) {
		for (int friend : idFriends.get(id)) {
			if (!res.contains(friend)) {
				res.add(friend);
				dfs(res, friend);
			}
		}
	}
	
	void buildMaps(String[] employeeInput, String[] friendsInput) {
		for (String s : employeeInput) {
			String[] idNameDept = s.split(",");
			int id = Integer.parseInt(idNameDept[0]);
			String name = idNameDept[1];
			String dept = idNameDept[2];
			
			idName.put(id, name);
			idDept.put(id, dept);
			
			if (!deptPersons.containsKey(dept)) {
				deptPersons.put(dept, new HashSet<Integer>());
			}
			deptPersons.get(dept).add(id);
		}
		
		for (String s : friendsInput) {
			String[] pair = s.split(",");
			int id1 = Integer.valueOf(pair[0]);
			int id2 = Integer.valueOf(pair[1]);
			
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
		for (Map.Entry<Integer, String> entry : idName.entrySet()) {
			int id = entry.getKey();
			String name = entry.getValue();
			
			if (!idFriends.containsKey(id)) {
				System.out.println(name + " doesn't have any friend");
			} else {
				Set<Integer> friends = idFriends.get(id);
				System.out.print(name + " has friends : ");
				for (int fri : friends) {
					String friName = idName.get(fri);
					System.out.print(friName + ", ");
				}
				System.out.println();
			}
		}
	}
}
