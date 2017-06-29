package company.facebook;
//重要
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 15. Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 * @author Lei
 *
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums); // First sort
        // 先固定一个数，之后另外两个数用2 Sum的思想
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip all duplicate elements
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    
                    res.add(list);
                    
                    left++;
                    right--;
                    
                    // Skip all duplicate elements
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return res;
    }
    
    // What if sorting is not allowed
    /**
     * Since it does not allow to sort the array, I assume it only asks for one triplet as solution or returns an empty triplet if no such sum exists. 
     * Also, I don't think using Hash map to store value to index works since the given array could have duplicates. 
     * I would recommend to use Hash map to store value to frequency.
     * 
     * 关键是怎么避免重复，楼主可以尝试用map[nums[i], count]记录frequency，然后把map里所有的key放在一个vector里，重复的会连在一起，这样就可以避免重复了
     * 
     * 我想到一个解法  可以用string来编码三个点 比如"1#5#6" 从小到大. 所有的解对应的string 放进set 就可以避免重复了
     */
    public List<List<Integer>> threeSumWithoutSorting(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        for (int i = 0; i < nums.length; i++) {
        	Map<Integer, int[]> map = new HashMap<Integer, int[]>();

        	for (int j = i + 1; j < nums.length; j++) {
        		if (map.containsKey(nums[j])) {
        			List<Integer> list = new ArrayList<Integer>();
        			int[] arr = map.get(nums[j]);
        			list.add(arr[0]);
        			list.add(arr[1]);
        			list.add(nums[j]);
        			
        			res.add(list);
        		}
        		
        		map.put(-nums[i] - nums[j], new int[]{nums[i], nums[j]});
        	}
        }
        
        return res;
    }
    
    public List<List<Integer>> threeSumWithoutSorting2(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length; i++) {
        	map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        
        List<Integer> list = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        	for (int i = 0; i < entry.getValue(); i++) {
        		list.add(entry.getKey());
        	}
        } // Same elements will be together, not sorted though, but grouped
        
        Map<Integer, int[]> map2 = new HashMap<Integer, int[]>();
        for (int i = 0; i < list.size(); i++) {
        	if (i > 0 && list.get(i) == list.get(i - 1)) {
        		continue;
        	}
        	
        	map2.clear();
        	for (int j = i + 1; j < list.size(); j++) {
        		if (j != i + 1 && list.get(j) == list.get(j - 1)) {
        			continue;
        		}
        		
        		if (map2.containsKey(list.get(j))) { // 后边的一个数， 这个数等于之前的俩数的相反数
        			List<Integer> list2 = new ArrayList<Integer>();
        			int[] arr = map2.get(list.get(j));
        			list2.add(arr[0]);
        			list2.add(arr[1]);
        			list2.add(list.get(j));
        			
        			res.add(list2);
        		}
        		
        		map2.put(-list.get(i)-list.get(j), new int[]{list.get(i), list.get(j)}); // 前边的俩数的和
        	}
        }
        
        return res;
    }
    
    public static void main(String[] args) {
    	ThreeSum sum3 = new ThreeSum();
    	int[] nums= {-1, 0, 1, -1, 2, -4};
    	List<List<Integer>> res = sum3.threeSumWithoutSorting2(nums);
    	for (List<Integer> list : res) {
    		for (int val : list) {
    			System.out.print(val + "  ");
    		}
    		System.out.println();
    	}
	}
}
/**
simple hash won’t work: the idea is to use a hash table to store all the unique values in the input, 
each value appears only once in the hash table. This won’t work because it cannot distinguish 
cases like:  1 1 1 5 with target 3, 1 1 5 with target 3, the first has a triplet (1, 1, 1) 
but the second does not have any solutions.

need a separate array with duplicates removed as the new input: 
do not iterate through the hash table directly, for unordered hash table, 
to iterate all the elements in the table the time complexity would be O(M) where M is the size of the table and M could be much larger than N.

duplicates indeed is very very troublesome!  For example, because of the difficulty in 1), 
I would come up with the same technique given in ”Two Sum Problem Analysis 3: Handling Duplicates Input“, 
so each key in the hash table would uniquely identify a list of the index in the original input that contains the key value. 
Similar with the sort approach, we first pick a value say 4, and we need to find two sum pair with target -4 in the rest of array. 
When we perform two sum, if the target happen to be equal to the sum of two same values, 
they will be identified at the same key slot in the hash table, in this case, only when the list of index contains >= 2 elements, 
it is OK to form the pair. Say one entry in the hash table is (-2, 0 -> 3 -> 5) which means in the original input, A[0] = A[3] = A[5] = -2, 
and in our case the target for two sum is -4, then we should be able to produce this triplet.

**/