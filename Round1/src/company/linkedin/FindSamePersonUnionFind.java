package company.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 如果两个用户id有同样的邮箱说明是同一个人，input有共同邮箱的人们和n个id，输出有多少人，bfs或者unionfind
 */
public class FindSamePersonUnionFind {
	public static void main(String[] args) {
		FindSamePersonUnionFind ff = new FindSamePersonUnionFind();
		
		HashMap<Integer, List<String>> contacts = new HashMap<Integer, List<String>>();
		String[] arr1 = {"a", "b", "c"};
		List<String> list1 = Arrays.asList(arr1);
		
		String[] arr2 = {"d", "d", "f"};
		List<String> list2 = Arrays.asList(arr2);
		
		String[] arr3 = {"h", "i", "j"};
		List<String> list3 = Arrays.asList(arr3);
		
		String[] arr4 = {"k", "o", "m"};
		List<String> list4 = Arrays.asList(arr4);
		
		String[] arr5 = {"n", "o", "p"};
		List<String> list5 = Arrays.asList(arr5);
		
		String[] arr6 = {"q", "r", "c"};
		List<String> list6 = Arrays.asList(arr6);
		
		contacts.put(1, list1);
		contacts.put(2, list2);
		contacts.put(3, list3);
		contacts.put(4, list4);
		contacts.put(5, list5);
		contacts.put(6, list6);
		
		Map<Integer, List<Integer>> res = ff.find(contacts);
		for (Map.Entry<Integer, List<Integer>> entry : res.entrySet()) {
			int personId = entry.getKey();
			System.out.print(personId + " : ");
			for (int next : entry.getValue()) {
				System.out.print(next + " ");
			}
			
			System.out.println();
		}
	}
	/**
	 * 
	 * @param contacts, Person -> Emails that person use
	 * @return Group same person together
	 */
	Map<Integer, Integer> personToRoot = new HashMap<Integer, Integer>();
	public Map<Integer, List<Integer>> find(HashMap<Integer, List<String>> contacts) {
		Map<String, Integer> emailToPerson = new HashMap<String, Integer>();
		
		for (Map.Entry<Integer, List<String>> entry : contacts.entrySet()) {
			int personId = entry.getKey();
			List<String> emails = entry.getValue();
			
			// Default root is itself
			personToRoot.put(personId, personId);
			
			for (String email : emails) {
				if (!emailToPerson.containsKey(email)) {// A new email
					emailToPerson.put(email, personId);
				} else {
					int newRoot = findRoot(emailToPerson.get(email));
					personToRoot.put(personId, newRoot);
				}
			}
		}
		
		Map<Integer, List<Integer>> res = new HashMap<Integer, List<Integer>>();
		for (Map.Entry<Integer, Integer> entry : personToRoot.entrySet()) {
			int personId = entry.getKey();
			int rootId = findRoot(entry.getValue());
			
			if (!res.containsKey(rootId)) {
				res.put(rootId, new ArrayList<Integer>());
			}
			res.get(rootId).add(personId);
		}
		
		return res;
	}
	private int findRoot(Integer id) {
		while (personToRoot.get(id) != id) {
			id = personToRoot.get(id);
		}
		
		return id;
	}
}
