package array;
/**
 * 324. Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
import java.util.Arrays;

public class WiggleSort2 {

	public static void main(String[] args) {
		WiggleSort2 ws2 = new WiggleSort2();
//		int[] nums = {1, 8, 3, 6, 4};
//		int[] nums = {1, 3, 2, 2, 3, 1};
		int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		ws2.wiggleSortQuickSort(nums);
		
		for (int val : nums) {
			System.out.print(val + " - ");
		}
	}

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums); // O(NlgN)
        
        int len = nums.length, mid = len % 2 == 0 ? len / 2 - 1 : len / 2;
        // Copies the specified array, truncating or padding with zeros (if necessary) so the copy has the specified length.
        int[] temp = Arrays.copyOf(nums, len); // O(N) extra space.
        
        int left = mid;
        int right = len - 1;
        
        int index = 0;
        boolean isLeft = true;
        while (left >= 0 && right > mid) {
        	if (isLeft) {
        		nums[index] = temp[left];
        		left--;
        	} else {
        		nums[index] = temp[right];
        		right--;
        	}
        	index++;
        	isLeft = !isLeft;
        }
        // The last digit
        if (left >= 0) {
        	nums[index] = temp[left];
        } else if (right > mid) {
        	nums[index] = temp[right];
        }
    }
    
    /**
     * This doesnt work for duplicates.
     * the result after sort is, 5 ~ 3 ~ 1 ~ 2 ~ 5 ~ 5 ~ 6 ~ 7 ~ 8
     * It can't differentiate the duplicate, so wiggle doesnt work
     * @param nums
     */
    public void wiggleSortQuickSort(int[] nums) {
    	int len = nums.length, mid = len % 2 == 0 ? len / 2 - 1 : len / 2; // 非严格意义上的中位数
    	quickSort(nums, 0, nums.length - 1);
    	
		for (int val : nums) {
			System.out.print(val + " ~ ");
		}
		System.out.println();
		System.out.println(mid);
        // Copies the specified array, truncating or padding with zeros (if necessary) so the copy has the specified length.
        int[] temp = Arrays.copyOf(nums, len); // O(N) extra space.
        
        int left = mid;
        int right = len - 1;
        
        int index = 0;
        boolean isLeft = true;
        while (left >= 0 && right > mid) {
        	if (isLeft) {
        		nums[index] = temp[left];
        		left--;
        	} else {
        		nums[index] = temp[right];
        		right--;
        	}
        	index++;
        	isLeft = !isLeft;
        }
        // The last digit
        if (left >= 0) {
        	nums[index] = temp[left];
        } else if (right > mid) {
        	nums[index] = temp[right];
        }
    }
    
	public void quickSort(int[] N, int left, int right) {
		if (left < right) {
			int p = partition(N, left, right);
			if (N.length % 2 == 0) {
				if (p != (N.length / 2 - 1)) {
					quickSort(N, left, p - 1);
					quickSort(N, p + 1, right);
				}
			} else {
				if (p != (N.length / 2)) {
					quickSort(N, left, p - 1);
					quickSort(N, p + 1, right);
				}
			}
		}
	}
	
	private int partition(int[] N, int left, int right) {
		int pivot = left;
		// When will this stop? only when left == right? YES! Because it is step by step
		while (left < right) {
			while (left < right && N[left] <= N[pivot]) {
				left++;
			}
			while (left < right && N[right] > N[pivot]) {
				right--;
			}
			
			swap(N, left, right);
		}
		System.out.println("left : " + left + " right : " + right);
		// Put pivot to the correct place
		if (N[pivot] >= N[left]) { // Why using right/left, rather than left. BOTH are fine! Since left will always equals to right at the end.
			swap(N, pivot, left);
			return left;
		} else {
			swap(N, pivot, left - 1);
			return left - 1;
		}
	}
	
	private void swap(int[] N, int left, int right) {
		int temp = N[left];
		N[left] = N[right];
		N[right] = temp;
	}
    
}
