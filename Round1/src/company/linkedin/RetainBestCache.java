package company.linkedin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class RetainBestCache<K, V> {

	public static void main(String[] args) {

	}

	private Map<K, V> cache; 
	private TreeMap<Integer, Set<K>> rank; 
	private DataSource<K, V> dataSource; 
	private int capacity; 

	/* Constructor with a data source (assumed to be slow) and a cache size */ 
	public RetainBestCache(DataSource<K, V> ds, int capacity) {
		this.dataSource = ds;
		this.capacity = capacity;
		this.cache = new HashMap<K, V>();
		this.rank = new TreeMap<Integer, Set<K>>();
	}
	
	/* Gets some data. 
	 * If possible, retrieves it from cache to be fast. If the data is not cached, 
	 * retrieves it from the data source. If the cache is full, attempt to cache the returned data, 
	 * evicting the T with lowest rank among the ones that it has available 
	 * If there is a tie, the cache may choose any T with lowest rank to evict. 
	 * */ 
	public V get(K key) {
		V res = null;
		if (cache.containsKey(key)) {
			res = cache.get(key);
		} else {
			res = dataSource.get(key);
			int curRank = dataSource.getRank(key);
			// Add to cache
			cache.put(key, res);
			if (!rank.containsKey(curRank)) {
				rank.put(curRank, new HashSet<K>());
			}
			rank.get(curRank).add(key);
			
			if (cache.size() > capacity) {
				removeLowestRank();
			}
		}
		
		return res;
	}

	private void removeLowestRank() {
		Entry<Integer, Set<K>> firstEntry = rank.firstEntry();
		int firstKey = firstEntry.getKey();
		Set<K> firstSet = firstEntry.getValue();
		
		K toRemove = firstSet.iterator().next();
		
		// Remove from rank TreeMap
		firstSet.remove(toRemove);
		if (firstSet.isEmpty()) {
			rank.remove(firstKey);
		}
		
		// Remove from Cache Map
		cache.remove(toRemove);
	} 
}

class DataSource<K, V> {
	V get(K key) {
		return (V)new Object();
	}
	
	int getRank(K key) {
		return 1;
	}
}