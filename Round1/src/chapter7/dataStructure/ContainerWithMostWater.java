package chapter7.dataStructure;
/**
 * 11. Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

 Notice

You may not slant the container.

Have you met this question in a real interview? Yes
Example
Given [1,3,2], the max area of the container is 2.
 * @author Lei
 *
 */
public class ContainerWithMostWater {
    public int maxArea2(int[] heights) {
        // write your code here
        int max = 0;
        // 这个双重for循环的思路跟largest rectangle histogram是一样一样的
        for (int i = 0; i < heights.length - 1; i++) {
            for (int j = i + 1; j < heights.length; j++) {
                int height = Math.min(heights[i], heights[j]);
                max = Math.max(max, height * (j - i));
            }
        }
        
        return max;
    }
    
    public int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        
        int max = 0;
        while (left < right) {
            if (heights[left] <= heights[right]) {
                max = Math.max(max, (right - left) * heights[left]);
                left++;
            } else {
                max = Math.max(max, (right - left) * heights[right]);
                right--;
            }
        }
        
        return max;
    }
}

