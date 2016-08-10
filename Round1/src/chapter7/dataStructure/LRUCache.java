package chapter7.dataStructure;

import java.util.HashMap;
import java.util.Map;
/**
 * 146. Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * @author Lei
 *
 */
public class LRUCache {
    
	public static void main(String[] args) {
		LRUCache lru = new LRUCache(2);
		
		lru.set(2,1);
		lru.set(1,1);
		int res1 = lru.get(2);
		System.out.println(res1);
		lru.set(4,1);
		int res2 = lru.get(1);
		System.out.println(res2);
		int res3 = lru.get(2);
		
		System.out.println(res3);
		
//		2,[lru.set(2,1),lru.set(1,1),lru.get(2),lru.set(4,1),lru.get(1),lru.get(2)]
	}
	
    ListNode first = null;
    ListNode last = null;
    int capacity = 0;
    Map<Integer, ListNode> map = new HashMap<Integer, ListNode>();
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            removeElement(node);
            
            setLastUsed(node);
            return node.value;
        }
        
        return -1;
    }
    
    void removeElement(ListNode node) {
        ListNode pre = node.pre;
        ListNode next = node.next;
        
        // It is the first node
        if (pre == null) {
            first = node.next;
        } else {
            pre.next = next;
        }
        
        // It is the last node
        if (next == null) {
            last = node.pre;
        } else {
            next.pre = pre;
        }
    }
    
    void setLastUsed(ListNode node) {
        // Clear
        node.next = null;
        node.pre = null;
        
        if (last == null) { // Everything empty
            first = node;
            last = node;
        } else {
            last.next = node;
            node.pre = last;
            
            last = node;
        }
    }
    
    public void set(int key, int value) {
        if (!map.containsKey(key)) {
            ListNode node = new ListNode(key, value);
            if (map.size() >= capacity) { // Need to remove first
            	// First is changing, so remove from map first
            	map.remove(first.key);
                removeElement(first);
                setLastUsed(node);
            } else {
                // Just add node and update last
                setLastUsed(node);
            }
            map.put(key, node);
        } else {
            // Just reset value and update last
            ListNode existing = map.get(key);
            removeElement(existing);
            
            existing.value = value;
            setLastUsed(existing);
        }
    }
}
class ListNode {
	int key;
	int value;
	ListNode pre;
	ListNode next;
	
	ListNode(int key, int value) {
	    this.key = key;
	    this.value = value;
	}
}