package chapter2.sortedArrays;
/**
80. Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 * @author Lei
 *
 */
public class RemoveDuplicates2 {

	public static void main(String[] args) {
		RemoveDuplicates2 r2 = new RemoveDuplicates2();
		int[] nums = {1, 1, 1, 2, 2, 3};
		
		r2.removeDuplicates(nums);
	}
	
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        
        int i, j;
        int cur = 0;
        for (i = 0; i < nums.length;) {
            int now = nums[i];
            for (j = i; j < nums.length; j++) { // Go through all the same elements
                if (nums[j] != now) {
                    break;
                }
                if (j - i < 2) {
                    nums[cur++] = nums[j];
                }
            }
            i = j;
        }
        
        return cur;
    }

}
