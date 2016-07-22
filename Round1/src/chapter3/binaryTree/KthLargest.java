package chapter3.binaryTree;
/**
 * 215. Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
 * @author Lei
 *
 */
public class KthLargest {

	public static void main(String[] args) {
		KthLargest kl = new KthLargest();
		int[] nums = {1};
		int res = kl.findKthLargest(nums, 1);
	}
	
    public int findKthLargest(int[] nums, int k) {
        int t = nums.length - k;
        int res = find(nums, 0, nums.length - 1, t);
        return res;
    }
    
    int find(int[] nums, int left, int right, int t) {
            int r = partition(nums, left, right);
            if (r == t) {
                return nums[r];
            } else if (r > t) {
                return find(nums, left, r - 1, t);
            } else {
                return find(nums, r + 1, right, t);
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
