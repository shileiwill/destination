package leetcode4.hashtable;

/*
 * 
 put(K key, V value) Worst:O(n) Best:O(1)
get(Object key) O(n) O(1)

HashSet is internally using HashMap as storage. It just keeps 1 distinguished key, which is the value in Set, e.g. hashMapCustom.put(value, null);
 * 
 */
public class HashMapCustom<K, V> {
	class Entry<K, V> {
		K key;
		V val;
		Entry<K, V> next;
		
		Entry (K key, V val, Entry<K, V> next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
	Entry<K, V>[] table = null;
	int capacity = 10;
	
	HashMapCustom(int capacity) {
		table = new Entry[capacity];
		this.capacity = capacity;
	}
	
	// This hash function could be the hashcode in K.java
	int hash(K key) {
		return (Math.abs(key.hashCode())) % this.capacity;
	}
	
	void put(K newKey, V newVal) {
		if (newKey == null) {
			return; // Like hashtable, dont support null as of now
		}
		
		int hash = hash(newKey);
		Entry<K, V> newEntry = new Entry<K, V>(newKey, newVal, null);
		
		if (table[hash] == null) {
			table[hash] = newEntry;
		} else {
			Entry<K, V> prev = null;
			Entry<K, V> now = table[hash];
			
			while (now != null) {
				if (now.key.equals(newKey)) {
					if (prev == null) { // The first in bucket
						newEntry.next = now.next;
						table[hash] = newEntry;
						return;
					} else {
						prev.next = newEntry;
						newEntry.next = now.next;
						return;
					}
				}
				prev = now;
				now = now.next;
			}
			
			// Append at the end if no match
			prev.next = newEntry;
		}
	}
	
	V get(K key) {
		int hash = hash(key);
		
		if (table[hash] == null) {
			return null;
		}
		
		Entry<K, V> now = table[hash];
		while (now != null) {
			if (now.key.equals(key)) {
				return now.val;
			}
			now = now.next;
		}
		
		return null;
	}
	
	boolean remove(K key) {
		int hash = hash(key);
		
		if (table[hash] == null) {
			return false;
		} else {
			Entry<K, V> prev = null;
			Entry<K, V> now = table[hash];
			
			while (now != null) {
				if (now.key.equals(key)) {
					if (prev == null) {
						table[hash] = table[hash].next;
						return true;
					} else {
						prev.next = now.next;
						return true;
					}
				}
				
				prev = now;
				now = now.next;
			}
		}
		
		return false;
	}
}
