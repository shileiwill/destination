package leetcode1.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 380. Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 */
public class InsertDeleteRandom {
    
    ArrayList<Integer> list = new ArrayList<Integer>();
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    Random rand = new Random();
    
    /** Initialize your data structure here. */
    public InsertDeleteRandom() {
        
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        
        int pos = list.size();

        list.add(val); // Position -> Value
        map.put(val, pos); // Value -> Position
        
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        
        // Move the element to the end first, because list is ordered
        int originalPos = map.get(val);
        if (originalPos != list.size() - 1) {
            int lastElement = list.get(list.size() - 1);
            list.set(originalPos, lastElement); // There are 2 lastElements now, but will remove the last one later
            map.put(lastElement, originalPos); // Update position in Map
        }
        
        list.remove(list.size() - 1); // Remove last element, always
        map.remove(val); // Remove from Map
        
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));        
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
