package leetcode1.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Random;

/**
 * 381. Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
 */
public class InsertDeleteRandom2 {

    // LinkedHashSet has a constant time of getting the first element because the order of all elements is maintained during each operation
    ArrayList<Integer> list = new ArrayList<Integer>();
    HashMap<Integer, LinkedHashSet<Integer>> map = new HashMap<Integer, LinkedHashSet<Integer>>();
    Random rand = new Random();
    
    /** Initialize your data structure here. */
    public InsertDeleteRandom2() {
        
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contains = map.containsKey(val);
        if (!contains) {
            map.put(val, new LinkedHashSet<Integer>());
        }
        
        int pos = list.size();

        list.add(val); // Position -> Value
        map.get(val).add(pos); // Value -> Position Collection
        
        return !contains;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        boolean contains = map.containsKey(val);
        if (!contains) {
            return false;
        }
        
        int pos = map.get(val).iterator().next(); // Get 1 position, not a specific one
        map.get(val).remove(pos); // Remove this position from Set
        
        if (pos != list.size() - 1) {
            int lastVal = list.get(list.size() - 1);
            list.set(pos, lastVal);
            
            map.get(lastVal).remove(list.size() - 1); // This is the real value, not index
            map.get(lastVal).add(pos);
        }
        
        list.remove(list.size() - 1);
        if (map.get(val).isEmpty()) {
            map.remove(val);
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));   
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */