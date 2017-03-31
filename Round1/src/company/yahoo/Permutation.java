package company.yahoo;

import java.util.*;

public class Permutation {

	public static void main(String[] args) {
		int[] arr = {1, 2, 5, 4, 3, 1};
		
		Permutation p = new Permutation();
//		p.nextPermutationWrong(arr);
		p.nextPermutation(arr);
	}

	List<List<Integer>> getPermutation(int[] arr) {
		Set<List<Integer>> resSet = new HashSet<List<Integer>>();
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		
		Arrays.sort(arr); // If using Set, no need to sort
		helperWithDuplicates(res, list, arr, visited);
		
		for (List<Integer> aList : res) {
			for (int val : aList) {
				System.out.print(val + "--");
			}
			System.out.println();
		}
		
		return res;
	}
	
	void helper(List<List<Integer>> res, List<Integer> list, int[] arr) {
		if (list.size() == arr.length) {
			for (int val : list) {
				System.out.print(val + "--");
			}
			System.out.println();
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (list.contains(arr[i])) {
				continue;
			}
			list.add(arr[i]);
			helper(res, list, arr);
			list.remove(list.size() - 1);
		}
	}
	
	// What if there is duplicate?
	void helperWithDuplicates(Set<List<Integer>> res, List<Integer> list, int[] arr, Set<Integer> visited) {
		if (list.size() == arr.length) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (visited.contains(i)) {
				continue;
			}
			visited.add(i);
			list.add(arr[i]);
			helperWithDuplicates(res, list, arr, visited);
			list.remove(list.size() - 1);
			visited.remove(i);
		}
	}
	
	// What if Set is not allowed. You need to figure out the duplicates
	void helperWithDuplicates(List<List<Integer>> res, List<Integer> list, int[] arr, Set<Integer> visited) {
		if (list.size() == arr.length) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (visited.contains(i) || (i != 0 && arr[i] == arr[i - 1] && !visited.contains(i - 1))) {
				continue;
			}
			visited.add(i);
			list.add(arr[i]);
			helperWithDuplicates(res, list, arr, visited);
			list.remove(list.size() - 1);
			visited.remove(i);
		}
	}
	
	// This is wrong. 1 2 5 4 3 1 => 1 3 1 2 4 5. Rather than ...
	public void nextPermutationWrong(int[] nums) {
		int index = findFirstSmallerThanTail(nums);
		
		if (index == -1) {
			reverse(nums, 0, nums.length - 1);
		} else {
			reverse(nums, index, nums.length - 1);
		}
		
		for (int val : nums) {
			System.out.print(val + "==");
		}
	}
	
	void reverse(int[] nums, int left, int right) {
		while (left < right) {
			int tmp = nums[left];
			nums[left] = nums[right];
			nums[right] = tmp;
			
			left++;
			right--;
		}
	}
	
	int findFirstSmallerThanTail(int[] nums) {
		int tail = nums[nums.length - 1];
		
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] < tail) {
				return i;
			}
		}
		
		return -1;
	}
	
	public void nextPermutation(int[] nums) {
		// Search from the end, find the first pair which is increasing
		int index = nums.length - 1;
		for (; index > 0; index--) {
			if (nums[index - 1] < nums[index]) {
				break;
			}
		}
		
		if (index > 0) {
			findAndSwap(nums, index - 1);
		}
		
		reverse(nums, index, nums.length - 1);
		
		for (int val : nums) {
			System.out.print(val + "==");
		}
	}
	
	void findAndSwap(int[] nums, int index) {
		for (int i = nums.length - 1; i > index; i--) {
			if (nums[i] > nums[index]) {
				int tmp = nums[index];
				nums[index] = nums[i];
				nums[i] = tmp;
				return;
			}
		}
	}
}

class PreviousPermutation {
    public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
		int len = nums.size();
		
		int index = len - 1;
		for (; index > 0; index--) {
		    if (nums.get(index - 1) > nums.get(index)) {
		        break;
		    }
		}
		
		if (index > 0) {
		    findAndSwap(nums, index - 1);
		}
		
	    reverse(nums, index, len - 1);
    
        return nums;
    }
    
    void findAndSwap(ArrayList<Integer> nums, int index) {
        int val = nums.get(index);
        
        for (int i = nums.size() - 1; i > index; i--) {
            if (nums.get(i) < val) {
                // Swap
                int tmp = nums.get(i);
                nums.set(i, val);
                nums.set(index, tmp);
                return;
            }
        }
    }
    
    void reverse(ArrayList<Integer> nums, int left, int right) {
        while (left < right) {
            int tmp = nums.get(left);
            nums.set(left, nums.get(right));
            nums.set(right, tmp);
            
            left++;
            right--;
        }
    }
}
