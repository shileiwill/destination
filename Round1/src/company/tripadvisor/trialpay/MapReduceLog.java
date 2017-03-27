package company.tripadvisor.trialpay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapReduceLog {

	public static void main(String[] args) {
		String s1 = "Hour1 Minute1 Second1 Error";
		String s2 = "Hour1 Minute1 Second1 Pass";
		String s3 = "Hour1 Minute1 Second2 Error";
		String s4 = "Hour1 Minute1 Second3 Pass";
		String s5 = "Hour1 Minute2 Second1 Error";
		String s6 = "Hour1 Minute3 Second1 Error";
		String s7 = "Hour1 Minute3 Second2 Error";
		String s8 = "Hour1 Minute3 Second2 Error";
		String s9 = "Hour1 Minute3 Second3 Error";
		String s10 = "Hour2 Minute3 Second3 Error";
		
		List<String> list = new ArrayList<String>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
		list.add(s6);
		list.add(s7);
		list.add(s8);
		list.add(s9);
		list.add(s10);
		
		MapReduceLog mr = new MapReduceLog();
		Map<String, Map<String, List<String>>> map = mr.map(list);
		
		Map<String, Map<String, Integer>> reduce = mr.reduce(map);
		System.out.println(reduce);
	}

	Map<String, Map<String, Integer>> reduce(Map<String, Map<String, List<String>>> map) {
		Map<String, Map<String, Integer>> reduce = new HashMap<String, Map<String, Integer>>();
		
		for (Map.Entry<String, Map<String, List<String>>> entry : map.entrySet()) {
			String hour = entry.getKey();
			Map<String, List<String>> statusByMinute = entry.getValue();
			
			if (!reduce.containsKey(hour)) {
				reduce.put(hour, new HashMap<String, Integer>());
			}
			
			Map<String, Integer> byHour = reduce.get(hour);
			for (Map.Entry<String, List<String>> en : statusByMinute.entrySet()) {
				String minute = en.getKey();
				List<String> statuses = en.getValue();
				
				int count = 0;
				for (String status : statuses) {
					if (status.equals("Error")) {
						count++;
					}
				}
				
				byHour.put(minute, count);
			}
		}
		
		return reduce;
	}
	
	Map<String, Map<String, List<String>>> map(List<String> list) {
		Map<String, Map<String, List<String>>> map = new HashMap<String, Map<String, List<String>>>();
		
		for (String str : list) {
			String[] arr = str.split(" ");
			
			String hour = arr[0];
			String min = arr[1];
			String sec = arr[2]; // We dont care which second.
			String status = arr[3];
			
			if (!map.containsKey(hour)) {
				map.put(hour, new HashMap<String, List<String>>());
			}
			
			Map<String, List<String>> map1 = map.get(hour); // Minute
			if (!map1.containsKey(min)) {
				map1.put(min, new ArrayList<String>());
			}
			
			List<String> list1 = map1.get(min);
			list1.add(status);
		}
		
		return map;
	}
}
