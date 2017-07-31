package company.facebook;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/**
 * 设计一个数据结构实现get(key), put(key, value), delete(key)和getLast()来存放k-v对。
 * 这个getLast返回的是上一次操作的k-v对，但是删除的操作不算，因为删除的k－v已经不在数据结构里了
 */
public class RandomizedSet<K, V> {

	Map<K, V> map = new HashMap<K, V>();
	Stack<K> stack = new Stack<K>();
	
	V get(K key) {
		if (key == null) {
			return null;
		}
		
		if (map.containsKey(key)) {
			V value = map.get(key);
			stack.push(key);
			return value;
		}
		
		return null;
	}
	
	void put(K key, V value) {
		map.put(key, value);
		stack.push(key);
	}
	
	void delete(K key) {
		if (map.containsKey(key)) {
			map.remove(key);
		}
	}
	
	V getLast() {
		while (!stack.isEmpty()) {
			K key = stack.pop();
			if (map.containsKey(key)) {
				stack.push(key);
				return map.get(key);
			}
		}
		
		return null;
	}
}