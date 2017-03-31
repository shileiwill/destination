package company.tripadvisor.trialpay;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 给你一个大file，每行左边是名字右边是值，像"foo 25"，然后他想要最后按值分类求次数，1~50的有多少个，50~100...
 * lz感觉有点像mapReduce然后和他说了一下思路，他说你能写个最简单的实现吗？然后lz想了想就写了个HashMap计数的，用"1~50","50~100"这些当key，count当value遍历一遍，
 * 老头不置可否，然后问了一下时间复杂度
 */
public class TreeMapSortedMap {

	public static void main(String[] args) {
		TreeMap<Integer, List<String>> map = new TreeMap<Integer, List<String>>();
		
		String[] arr1 = {"hello", "world"};
		List<String> list1 = Arrays.asList(arr1);
		
		String[] arr2 = {"java", "javascript"};
		List<String> list2 = Arrays.asList(arr2);
		
		String[] arr3 = {"cat"};
		List<String> list3 = Arrays.asList(arr3);
		
		map.put(1, list1);
		map.put(5, list2);
		map.put(7, list3);
		
		// floorEntry(inclusive), ceilingEntry, lowerEntry, higherEntry() will match 1 Entry. If there is nothing matched, null Entry is returned.
		Entry<Integer, List<String>> floorEntry = map.ceilingEntry(5);
		int floorKey = floorEntry.getKey();
		List<String> floorList = floorEntry.getValue();
		
		System.out.print(floorKey + " : ");
		for (String str : floorList) {
			System.out.print(str + ", ");
		}
		System.out.println();
		
		System.out.println("======================");
		// Use subMap(start, end) to get a section of original map
		SortedMap<Integer, List<String>> subMap = map.subMap(3, 8); // start inclusive, end exclusive
		for(Map.Entry<Integer, List<String>> entry : subMap.entrySet()) {
			int key = entry.getKey();
			List<String> list = entry.getValue();
			
			System.out.print(key + " : ");
			for (String str : list) {
				System.out.print(str + ",");
			}
			System.out.println();
		}
	}
}
