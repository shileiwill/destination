package leetcode5.twopointers;
/**
 * 360. Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example:
nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

Result: [3, 9, 15, 33]

nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

Result: [-23, -5, 1, 7]

For a parabola,
if a >= 0, the min value is at its vertex. So our our sorting should goes from two end points towards the vertex, high to low.
if a < 0, the max value is at its vertex. So our sort goes the opposite way.
 */
public class SortTransformedArray {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        int i = (a < 0) ? 0 : nums.length - 1; // Either from the beginning or from the end
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int leftVal = calculate(nums[left], a, b, c);
            int rightVal = calculate(nums[right], a, b, c);
            
            if (a < 0) {
                if (leftVal < rightVal) {
                    res[i++] = leftVal;
                    left++;
                } else {
                    res[i++] = rightVal;
                    right--;
                }        
            } else { // 如果a == 0, 或者单调递增或者单调递减，取决于b， 但是我们只管比较两头就行，结果是总是取得一头的
                if (leftVal > rightVal) {
                    res[i--] = leftVal;
                    left++;
                } else {
                    res[i--] = rightVal;
                    right--;
                }  
            }
        }
        
        return res;
    }
    
    int calculate(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
