package chapter7.dataStructure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * 128. Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 * @author Lei
 *
 */
public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		int[] nums = {1, 3, 2, 2, 4, 34, 35, 2, 23};
		int[] nums2 = {1 , -1, 0};
	}
	// Brute force
    public int longestConsecutiveBrute(int[] nums) {
        int count = 1;
        Arrays.sort(nums);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                count++;
                max = Math.max(max, count);
            } else if (nums[i] == nums[i - 1]) {
                continue;
            } else {
                count = 1;
            }
        }
        
        return max;
    }
    
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        
        int max = 0;
        for (int num : nums) {
            
            int down = num - 1; // Going down
            while (set.contains(down)) {
                set.remove(down); // This will speed up
                down--;
            }
            
            int up = num + 1; // Going up
            while (set.contains(up)) {
                set.remove(up);
                up++;
            }
            
            max = Math.max(max, up - down -1);
        }
        
        return max;
    }
    
    public int longestConsecutive2(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        
        int max = Integer.MIN_VALUE;
        
        // How to use set to iterator. 
        while (!set.isEmpty()) {
            int origin = set.iterator().next(); // We cant use get() in Set. but this is how to get an element
            int num = origin;
            
            int count = 1;
            set.remove(num);
            
            while (set.contains(--num)) {
                count++;
                set.remove(num);
            }
            
            while (set.contains(++origin)) {
                count++;
                set.remove(origin);
            }
            
            max = Math.max(max, count);
        }
        
        // Here is iterating array
//        for (int num : nums) {
//            if (!set.contains(num)) {
//                continue;
//            }
//            
//            int count = 1;
//            set.remove(num);
//            
//            int origin = num; // Keep a note of original state
//            
//            while (set.contains(--num)) {
//                count++;
//                set.remove(num);
//            }
//            
//            while (set.contains(++origin)) {
//                count++;
//                set.remove(origin);
//            }
//            
//            max = Math.max(max, count);
//        }
        
        return max;
    }
    
    public int longestConsecutiveUnionFind(int[] nums) {
        UnionFind uf = new UnionFind(nums.length);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                continue;
            }
            
            map.put(nums[i], i); // value, index
            
            if (map.containsKey(nums[i] + 1)) {
                uf.union(i, map.get(nums[i] + 1));
            }
            
            if (map.containsKey(nums[i] - 1)) {
                uf.union(i, map.get(nums[i] - 1));
            }
        }
        
        return uf.maxUnion();
    }
    
    class UnionFind {
        int[] parent = null;
        
        UnionFind(int totalNodes) {
            parent = new int[totalNodes];
            for (int i = 0; i < totalNodes; i++) {
                parent[i] = i;
            }
        }
        
        void union(int id1, int id2) {
            int parent1 = find(id1);
            int parent2 = find(id2);
            
            if (parent1 != parent2) {
                parent[parent1] = parent2; // Share the same ancestor now
            }
        }
        
        int find(int id) {
            while (parent[id] != id) {
                parent[id] = parent[parent[id]];
                id = parent[id]; // id goes up 1 level
            }
            
            return parent[id]; // return the ancestor
        }
        
        int findRecursion(int id) {
            if (parent[id] == id) {
                return id;
            }
            
            // This will make it faster
            parent[id] = findRecursion(parent[id]); // Recursion to find the ancestor
            return parent[id]; // return the ancestor
        }
        
        boolean isConnected(int id1, int id2) {
            return find(id1) == find(id2);
        }
        
        int maxUnion() {
            int max = 0;
            int[] count = new int[parent.length];
            for (int i = 0; i < parent.length; i++) {
                count[find(i)]++;
                max = Math.max(max, count[find(i)]);
            }
            
            return max;
        }
    }
}
