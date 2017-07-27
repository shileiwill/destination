package company.linkedin;
/**
 * 然后开始做题。第一题三哥出的，给了一堆server，然后让找出first available server，我想这不是first missing positive吗，leetcode原题，但L家面试题里从来没见过，
但方法是记得的，但开始还是假装说最intuitive的办法就说sort，但肯定有更好的办法，容我想想！三哥很急的说，ok ok let me give you a hint, 
我当时就急了心中大喊我不要hint!我这演技已经是影后的水平了吗！大概三哥听到我心里的呐喊，就说我再等你想想。我哪还敢等，马上开始写。
写完过了test case，这时已经33分钟了。. 

给出一个大小为n的无序数组。这些元素与1～n的全排列相比缺少了一个数字，并有一个数字出现了两次。找出这两个数。
 */
public class FirstMissingPositiveNumber {

	public static void main(String[] args) {

	}

	// Thoughts of Bucket Sort
	public int firstMissingPositive(int[] nums) {
		// Numbers will be 1 to N always
		int N = nums.length;
		
		for (int i = 0; i < N; i++) {
			while (nums[i] != i + 1) { // swap all the time
				if (nums[i] <= 0 || nums[i] > nums.length) { // Out of scope, illegal
					break; // 继续换别的，可能后边的能填上这个block
				}
				
				if (nums[i] == nums[nums[i] - 1]) { // Duplicate elements
					break;
				}
				
				swap(nums, i, nums[i] - 1);
			}
		}
		
		for (int i = 0; i < N; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}
		
		return N + 1;
	}
}
