package leetcode3.linkedlist;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 379. Design a Phone Directory which supports the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.
Example:

// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
directory.get();

// Assume it returns 1.
directory.get();

// The number 2 is available, so return true.
directory.check(2);

// It returns 2, the only number that is left.
directory.get();

// The number 2 is no longer available, so return false.
directory.check(2);

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
directory.check(2);
 */
public class PhoneDirectory {

    HashSet<Integer> used = null;
    Queue<Integer> available = null;
    int capacity = 0;
    
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        capacity = maxNumbers;
        used = new HashSet<Integer>(capacity);
        available = new LinkedList<Integer>();
        
        for (int i = 0; i < capacity; i++) {
            available.offer(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (available.isEmpty()) {
            return -1;
        }
        int val = available.poll();
        used.add(val);
        
        return val;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        // This will timeout, Hashset is faster
        // return available.contains(number);
        return !used.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if (used.remove(number)) {
            available.offer(number);
        }
    }
}

class PhoneDirectorySlow {

    HashSet<Integer> set = null;
    int capacity = 0;
    
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectorySlow(int maxNumbers) {
        capacity = maxNumbers;
        set = new HashSet<Integer>(capacity);
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        int i = 0;
        while (i < capacity) {
            if (!set.contains(i)) {
                set.add(i);
                return i;
            }
            i++;
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !set.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        set.remove(number);
    }
}
