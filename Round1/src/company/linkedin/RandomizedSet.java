package company.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet<T> {

	public static void main(String[] args) {
		RandomizedSet<Integer> rs = new RandomizedSet<Integer>();
		
		rs.insert(12);
		System.out.println(rs.getRandom());
		rs.insert(2);
		rs.remove(12);
//		rs.insert(1);
		System.out.println(rs.getRandom());
		rs.insert(13);
		rs.insert(15);
		rs.remove(13);
		System.out.println(rs.getRandom());
		rs.insert(10);
		rs.insert(14);
		rs.remove(14);
		System.out.println(rs.getRandom());
		
	}

	Random ran = new Random();
	Map<Integer, T> indexToValue = new HashMap<Integer, T>();
	Map<T, Integer> valueToIndex = new HashMap<T, Integer>();
	
	public void insert(T val) {
		int size = indexToValue.size();
		
		valueToIndex.put(val, size);
		indexToValue.put(size, val);
	}
	
	public void remove(T val) {
		int size = indexToValue.size();
		
		int index = valueToIndex.get(val);
		
		if (index == size - 1) { // Last element
			indexToValue.remove(index);
			valueToIndex.remove(val);
		} else {
			T last = indexToValue.get(size - 1);
			indexToValue.remove(size - 1);
			valueToIndex.remove(val);
			
			valueToIndex.put(last, index);
			indexToValue.put(index, last);
		}
	}
	
	public T getRandom() {
		int size = indexToValue.size();
		int index = ran.nextInt(size);
		return indexToValue.get(index);
	}
}

// With RemoveRandom
class RandomizedSet2 {

    Random ran = new Random();
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    List<Integer> list = new ArrayList<Integer>();
    
    /** Initialize your data structure here. */
    public RandomizedSet2() {
        
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            int size = list.size();
            list.add(val);
            map.put(val, size);
            
            return true;
        }
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        } else {
            int index = map.get(val);
            map.remove(val);
            
            if (index == list.size() - 1) { // Last element
                
            } else {
                int lastElement = list.get(list.size() - 1);
                map.put(lastElement, index);
                list.set(index, lastElement);
            }
            
            list.remove(list.size() - 1);
            
            return true;
        }
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int index = ran.nextInt(list.size());
        return list.get(index);
    }
    
    public int removeRandom() {
        int index = ran.nextInt(list.size());
        int val = list.get(index);
        
        remove(val);
        
        return val;
    }
}
