package array;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
// There is also an O(N) solution using buckets sort methodology
// https://discuss.leetcode.com/topic/15199/ac-o-n-solution-in-java-using-buckets-with-explanation
public class ContainsDuplicate3 {
	// NlogN using TreeSet
	// The floor(x) method returns the greatest value that is less than x. The ceiling(x) methods returns the least value that is greater than x
    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        if(nums==null || nums.length < 2 || k < 0 || t < 0) {
            return false;
        }
        
        TreeSet<Long> cache = new TreeSet<Long>();
        
        for (int i = 0; i < nums.length; i++) {
            long num = (long)nums[i];
            
            if ((cache.floor(num) != null && cache.floor(num) + t >= num) ||
                (cache.ceiling(num) != null && cache.ceiling(num) - t <= num)) {
                return true;
            }
            
            cache.add(num);
            
            if (cache.size() == k + 1) {
                cache.remove((long)nums[i - k]);
            }
        }
        
        return false;
    }
    
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if(nums==null || nums.length < 2 || k < 0 || t < 0) {
            return false;
        }
        
        TreeSet<Long> cache = new TreeSet<Long>();
        
        for (int i = 0; i < nums.length; i++) {
            long num = (long)nums[i];
            
            long lowerBound = (long)(num - t);
            long upperBound = (long)(num + t + 1); // Exclusive, so add 1
            
            SortedSet<Long> sub = cache.subSet(lowerBound, upperBound); // The 2 parameters are real values
            if (sub.size() > 0) {
                return true;
            }
            
            cache.add(num);
            
            if (cache.size() == k + 1) {
                cache.remove((long)nums[i - k]);
            }
        }
        
        return false;
    }
    
	// Time out exception
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Item[] items = new Item[nums.length];
        for (int i = 0; i < nums.length; i++) {
            Item item = new Item(i, nums[i]);
            items[i] = item;
        }
        Arrays.sort(items);
        
        for (int i = 1; i < items.length; i++) {
            int left = i - 1;
            int right = i + 1;
            
            int curValue = items[i].value;
            int curIndex = items[i].index;
            
            // Search left
            while (left >= 0 && Math.abs(Long.valueOf(curValue) - Long.valueOf(items[left].value)) <= t) {
                
                if (Math.abs(items[left].index - curIndex) <= k) {
                    return true;
                }
                left--;
            }
            
            // search right
            while (right < items.length && Long.valueOf(items[right].value) - Long.valueOf(curValue) <= t) {
                if (Math.abs(items[right].index - curIndex) <= k) {
                    return true;
                }
                right++;
            }
        }
        
        return false;
    }
    
    class Item implements Comparable<Item> {
        int index;
        int value;
        
        Item(int index, int value) {
            this.index = index;
            this.value = value;
        }
        
        public int compareTo(Item another) {
            return this.value - another.value;
        }
    }
}
