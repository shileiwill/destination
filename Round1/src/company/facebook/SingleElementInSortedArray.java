package company.facebook;
//重点
/**
 * 540. Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. 
 * Find this single element that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
Note: Your solution should run in O(log n) time and O(1) space.

An O(N) solution is to use ^ executive OR
一看log(N)就知道是binary search。 Sorted Array是一个关键点

New, Important
 */
public class SingleElementInSortedArray {
    public int singleNonDuplicate(int[] nums) { // 数组中肯定是2N+1个元素，奇数个
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) { // 这个操作挺危险，得保证每个section至少有三个元素
                return nums[mid];
            } else if (nums[mid] == nums[mid + 1] && mid % 2 == 0) { // 每次remove偶数个元素，所以剩下的section总是奇数，包含the single number
                left = mid + 1;
            } else if (nums[mid] == nums[mid - 1] && mid % 2 == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } // 最终肯定是left==right, 只剩一个元素，which is the single number
        
        return nums[right];
    }
}
