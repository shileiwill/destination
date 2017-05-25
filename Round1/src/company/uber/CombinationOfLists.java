package company.uber;

import java.util.*;
import java.util.Set;

/**
给List<List<Integer>> 找出所有在每一个里都取一个integer的组合。recursive写完要求写了iterative没写完
 */
public class CombinationOfLists {

	public static void main(String[] args) {
		List<List<Integer>> source = new ArrayList<List<Integer>>();
		
		Integer[] arr1 = {41, 42, 44, 45, 47};
		List<Integer> list1 = Arrays.asList(arr1);
		
		Integer[] arr2 = {51, 52, 54, 55, 57};
		List<Integer> list2 = Arrays.asList(arr2);
		
		Integer[] arr3 = {11, 12, 14, 15, 17};
		List<Integer> list3 = Arrays.asList(arr3);
		
		Integer[] arr4 = {21, 22, 24, 25, 27};
		List<Integer> list4 = Arrays.asList(arr4);
		
		Integer[] arr5 = {31, 32, 34, 35, 37};
		List<Integer> list5 = Arrays.asList(arr5);
		
		source.add(list1);
		source.add(list2);
		source.add(list3);
		source.add(list4);
		source.add(list5);
		
		CombinationOfLists col = new CombinationOfLists();
		col.combinations(source);
	}

	List<List<Integer>> combinations(List<List<Integer>> source) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		
		List<Set<Integer>> visitedList = new ArrayList<Set<Integer>>();
		for (int i = 0; i < source.size(); i++) {
			visitedList.add(new HashSet<Integer>());
		}
		
		helper(res, list, source, 0, visitedList);
		return res;
	}
	
	void helper(List<List<Integer>> res, List<Integer> list, List<List<Integer>> source, int machineId, List<Set<Integer>> visitedList) {
		if (machineId == source.size()) {
			for (int val : list) {
				System.out.print(val + " -- ");
			}
			System.out.println();
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		Set<Integer> visited = visitedList.get(machineId);
		List<Integer> machine = source.get(machineId);
		
		for (int i = 0; i < machine.size(); i++) {
			if (!visited.contains(i)) {
				list.add(machine.get(i));
				visited.add(i);
				helper(res, list, source, machineId + 1, visitedList);
				visited.remove(i);
				list.remove(list.size() - 1);
			}
		}
	}
}
