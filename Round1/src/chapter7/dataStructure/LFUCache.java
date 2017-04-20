package chapter7.dataStructure;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * 460. Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

 */

//LFUCache cache = new LFUCache( 2 /* capacity */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // returns 1
//cache.put(3, 3);    // evicts key 2
//cache.get(2);       // returns -1 (not found)
//cache.get(3);       // returns 3.
//cache.put(4, 4);    // evicts key 1.
//cache.get(1);       // returns -1 (not found)
//cache.get(3);       // returns 3
//cache.get(4);       // returns 4

//https://discuss.leetcode.com/topic/69137/java-o-1-accept-solution-using-hashmap-doublelinkedlist-and-linkedhashset/6
public class LFUCache {

 Node head = null;
 int capacity = 0;
 Map<Integer, Integer> valueMap = null;
 Map<Integer, Node> nodeMap = null;
 
 public LFUCache(int capacity) {
     this.capacity = capacity;  
     valueMap = new HashMap<Integer, Integer>(capacity);
     nodeMap = new HashMap<Integer, Node>(capacity);
 }
 
 public int get(int key) {
     if (valueMap.containsKey(key)) {
         increaseCount(key);
         return valueMap.get(key);
     }
     
     return -1;
 }
 
 void increaseCount(int key) {
     Node node = nodeMap.get(key);
     node.keys.remove(key); // Remove from LinkedHashSet - node.keys
     
     if (node.next == null) {
         node.next = new Node(node, null, node.count + 1, key);
     } else if (node.next.count == node.count + 1) {
         node.next.keys.add(key);
     } else {
         Node newNode = new Node(node, node.next, node.count + 1, key);
         node.next.prev = newNode;
         node.next = newNode;
     }
     nodeMap.put(key, node.next); // Replace in nodeMap
     
     if (node.keys.isEmpty()) {
         remove(node); // remove after all above operations
     }
 }
 
 void remove(Node node) {
     Node prev = node.prev;
     Node next = node.next;
     
     if (prev == null) {
         head = node.next;
     } else {
         prev.next = node.next;
     }
     
     if (next == null) {
         
     } else {
         next.prev = node.prev;
     }
 }
 
 public void put(int key, int value) {
     if (this.capacity == 0) {
         return;
     }
     
     if (valueMap.containsKey(key)) {
         valueMap.put(key, value);
         increaseCount(key); // Put will also increase count
     } else { // A new member/key
         // Add to 3 places
         valueMap.put(key, value);
         if (nodeMap.size() == capacity) {
             remove(); // By default, it is remove the least frequency, which is the head
         }
         add(key);
     }
 }
 
 // Remove from 3 places, valueMap, nodeMap, and LinkedList
 void remove() {
     if (head == null) {
         return; // Nothing to remove
     }
     int oldest = head.keys.iterator().next();
     head.keys.remove(oldest);
     
     if (head.keys.isEmpty()) {
         remove(head);
     }
     
     nodeMap.remove(oldest);
     valueMap.remove(oldest);
 }
 
 // This is to add to nodeMap and doubly linked list, valueMap is already done, easy
 void add(int key) {
     if (head == null) {
         head = new Node(null, null, 1, key);
     } else if (head.count == 1) { // The least frequent is 1, just join it
         head.keys.add(key);
     } else {
         Node newNode = new Node(null, head, 1, key);
         head.prev = newNode;
         head = newNode;
     }
     
     nodeMap.put(key, head);
 }
 
 class Node {
     Node prev, next;
     int count;
     LinkedHashSet<Integer> keys = new LinkedHashSet<Integer>();
     
     Node(Node prev, Node next, int count, int key) {
         this.prev = prev;
         this.next = next;
         this.count = count;
         keys.add(key);
     }
 }
}

/**
* Your LFUCache object will be instantiated and called as such:
* LFUCache obj = new LFUCache(capacity);
* int param_1 = obj.get(key);
* obj.put(key,value);
*/