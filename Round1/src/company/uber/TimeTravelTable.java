package company.uber;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 	面我的是一个西人大哥。这轮考数据结构设计，time travel table。接口呀，合法输入值啊，返回值啊什么的都得跟面试官讨论。 
 * 不过具体功能如下： 
 * 输入： "key1" time1 "val1" // 插入一条记录 
 * 输入： “key1“ time2 ”val2" // 插入一条记录 
 * 输入： “key1" time2 "val3" // 把key1的time2记录的值从val2改成val3 
 * 输入： “key1" time6 "val4" // 插入一条记录 输入： “key1” time1 // 取出记录，val1 
 * 输入： “key1” time2 // 取出记录，val3 
 * 输入： “key1” time4 // 取出记录，val3，取出第一个时间戳比现在时间小的值 
 * 输入： “key1” time0 // 取一个不存在时间戳小于等于输入值的，返回null 
 * 我用了hashmap+treeMap解决问题。 

 */
public class TimeTravelTable {

	public static void main(String[] args) {

	}

	// Key, Timestamp, Value
	Map<String, TreeMap<Integer, String>> map = new HashMap<String, TreeMap<Integer, String>>();
	
	void put(String key, int timestamp, String value) {
		if (map.containsKey(key)) {
			TreeMap<Integer, String> treeMap = map.get(key);
			treeMap.put(timestamp, value);
		} else {
			TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();
			treeMap.put(timestamp, value);
			map.put(key, treeMap);
		}
	}
	
	String get(String key, int timestamp) {
		if (!map.containsKey(key)) {
			return null;
		}
		
		TreeMap<Integer, String> treeMap = map.get(key);
		Entry<Integer, String> floorEntry = treeMap.floorEntry(timestamp);
		
		return floorEntry.getValue();
	}
}
